package controladores.usuario;

/**
 *
 * @author Zatonio
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.AnuncioJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.Usuario;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import modelo.dao.ArmaFuegoJpaController;
import modelo.dao.ContratoCompraVentaJpaController;
import modelo.dao.EstadoAnuncioJpaController;
import modelo.dao.EstadoPedidoJpaController;
import modelo.dao.FacturaJpaController;
import modelo.dao.NotificacionJpaController;
import modelo.dao.PedidoJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.ArmaFuego;
import modelo.entidades.ContratoCompraVenta;
import modelo.entidades.EstadoAnuncio;
import modelo.entidades.EstadoPedido;
import modelo.entidades.Factura;
import modelo.entidades.Notificacion;
import modelo.entidades.Pedido;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "CompraCompletada", urlPatterns = {"/usuario/CompraCompletada"})
public class CompraCompletada extends HttpServlet {

    private static final String UPLOAD_DIR = "/img/pdfs";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vista = "/usuario/compraCompletada.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        Anuncio a = (Anuncio) request.getSession().getAttribute("anuncio");
        request.getSession().removeAttribute("anucio");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");

        Date fechaActual = new Date();

        EstadoPedidoJpaController epjc = new EstadoPedidoJpaController(emf);

        EstadoPedido estadoPedido = epjc.findEstadoPedido(1L);
        PedidoJpaController pjc = new PedidoJpaController(emf);
        Long numeroUltimoPedido = Long.valueOf(pjc.getPedidoCount());
        numeroUltimoPedido++;
        Pedido p = new Pedido();
        p.setId_estado_pedido(estadoPedido);
        p.setId_arma(a.getId_arma());
        p.setFecha_compra(fechaActual);
        p.setFecha_entrega(null);
        p.setId_usuario(usuario);
        p.setNumero_pedido(numeroUltimoPedido);

        try {
            
            pjc.create(p);
            Notificacion n=new Notificacion();
            n.setMensaje("El ususario "+usuario.getNickname()+" ha comprado un producto, revise el estado del pedido");
            UsuarioJpaController ujc=new UsuarioJpaController(emf);
            NotificacionJpaController njc=new NotificacionJpaController(emf);
            
            for(Usuario user :ujc.findUsuarioEntities()  ){
                     if(user.getRol().equals("admin")){
                        n.setId_usuario(user);
                        njc.create(n);
                     }
                 }
        } catch (Exception e) {

        }

        EstadoAnuncioJpaController eajc = new EstadoAnuncioJpaController(emf);
        EstadoAnuncio ea = eajc.findEstadoAnuncio(8L);
        a.setId_estado_anuncio(ea);
        AnuncioJpaController ajc = new AnuncioJpaController(emf);

        try {
            ajc.edit(a);
        } catch (Exception e) {

        }

        //TODO: GenerarFactura
        Factura f = new Factura();
        FacturaJpaController fjc = new FacturaJpaController(emf);
        Long numeroUltimaFactura = Long.valueOf(fjc.getFacturaCount());
        numeroUltimaFactura++;
        f.setId_pedido(p);
        f.setImporte_bruto(a.getPrecio());
        Double importeGastos = a.getPrecio() * 0.125 + a.getPrecio() * 0.05;
        f.setImporte_gastos(importeGastos);
        f.setImporte_descuento(0.0);
        f.setBase_imponible(f.getImporte_bruto() + f.getImporte_gastos() - f.getImporte_descuento());
        f.setDescripcion_descuento("");
        f.setDescripcion_gastos("De gestion: 12.5%, Transporte: " + a.getPrecio() * 0.05);
        f.setNum_factura(numeroUltimaFactura);
        f.setPrecio_total_factura((f.getBase_imponible() * 0.21) + f.getBase_imponible());
        String rutaServlet = request.getServletContext().getRealPath("");

        String rutaPdfFactura = rutaServlet + "/pdfs/ModeloFacturaEditable.pdf";

        String rutaNuevaFactura = rutaServlet + "/pdfs/Factura nº" + f.getNum_factura() + ".pdf";
        String rutaRelativaNuevaFactura = request.getContextPath() + "/pdfs/Factura nº" + f.getNum_factura() + ".pdf";

        f.setUrl_factura(rutaRelativaNuevaFactura);
        try {
            fjc.create(f);

        } catch (Exception e) {

        }

        try (PDDocument document = PDDocument.load(new File(rutaPdfFactura))) {
            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
            
            
            if (acroForm != null) {
                    for (PDField field : acroForm.getFieldTree()) {
                        if (field instanceof PDNonTerminalField) {
                            System.out.println("Subformulario encontrado: " + field.getFullyQualifiedName());

                            for (PDField child : ((PDNonTerminalField) field).getChildren()) {
                                System.out.println("Campo: " + child.getFullyQualifiedName());
                                System.out.println("Valor: " + child.getValueAsString());
                            }
                        } else {
                            System.out.println("Campo: " + field.getFullyQualifiedName());
                            System.out.println("Valor: " + field.getValueAsString());
                        }
                    }

                }
            
            
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            PDField campo = acroForm.getField("Text1");
            campo.setValue("" + f.getNum_factura());

            campo = acroForm.getField("Text2");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            campo.setValue(dateFormat.format(p.getFecha_compra()));

            campo = acroForm.getField("Text3");
            campo.setValue(normalizar(usuario.getId_municipio().getNombre()));

            campo = acroForm.getField("Text4");
            campo.setValue(normalizar(usuario.getId_usuario().toString()));

            campo = acroForm.getField("Text5");
            campo.setValue(normalizar(usuario.getNombre() + " " + usuario.getApellidos()));

            campo = acroForm.getField("Text6");
            campo.setValue(normalizar("DNI: " + usuario.getNif()));

            campo = acroForm.getField("Text7");
            campo.setValue(normalizar(usuario.getDireccion()));

            campo = acroForm.getField("Text8");
            campo.setValue(normalizar(usuario.getId_municipio().getProvincia().getNombre()));

            campo = acroForm.getField("Text9");
            campo.setValue(normalizar("ARM" + a.getId_arma().getId_arma()));

            campo = acroForm.getField("Text10");
            campo.setValue(normalizar(a.getId_arma().getMarca() + "-" + a.getId_arma().getId_arma()));

            campo = acroForm.getField("Text11");
            campo.setValue("1");

            campo = acroForm.getField("Text12");
            campo.setValue(decimalFormat.format(a.getPrecio()));

            campo = acroForm.getField("Text13");
            campo.setValue(decimalFormat.format(a.getPrecio()));

            campo = acroForm.getField("Text14");
            campo.setValue(decimalFormat.format(a.getPrecio()));

            campo = acroForm.getField("Text15");
            campo.setValue(normalizar(f.getDescripcion_descuento()));

            campo = acroForm.getField("Text16");
            campo.setValue(decimalFormat.format(f.getImporte_descuento()));

            campo = acroForm.getField("Text17");
            campo.setValue(normalizar(f.getDescripcion_gastos()));

            campo = acroForm.getField("Text18");
            campo.setValue(decimalFormat.format(f.getImporte_gastos()));

            campo = acroForm.getField("Text19");
            campo.setValue(decimalFormat.format(f.getBase_imponible()));

            campo = acroForm.getField("Text20");
            double iva = f.getBase_imponible() * 0.21;
            campo.setValue(decimalFormat.format(iva));

            campo = acroForm.getField("Text21");
            campo.setValue(decimalFormat.format(f.getPrecio_total_factura()));

            File destino = new File(rutaNuevaFactura);
            destino.getParentFile().mkdirs();

            document.save(destino);
            document.close();

        }

        ArmaFuegoJpaController afjc = new ArmaFuegoJpaController(emf);

        ArmaFuego af = afjc.findArmaFuego(a.getId_arma().getId_arma()); //Si el arma es un arma de Fuego entonces generamos el contrato de compraventa
        if (af != null) {

            String rutaPdf = rutaServlet + "/pdfs/TransferenciaArmas.pdf";
            String rutaNuevoPdf = rutaServlet + "/pdfs/TransferenciaArma" + a.getId_arma().getId_usuario().getId_usuario() + a.getId_arma().getId_arma() + ".pdf";
            String rutaRelativaNuevoPdf = request.getContextPath() + "/pdfs/TransferenciaArma" + a.getId_arma().getId_usuario().getId_usuario() + a.getId_arma().getId_arma() + ".pdf";
            try (PDDocument document2 = PDDocument.load(new File(rutaPdf))) {
                PDAcroForm acroForm2 = document2.getDocumentCatalog().getAcroForm();

                if (acroForm2 != null) {
                    for (PDField field : acroForm2.getFieldTree()) {
                        if (field instanceof PDNonTerminalField) {
                            System.out.println("Subformulario encontrado: " + field.getFullyQualifiedName());

                            for (PDField child : ((PDNonTerminalField) field).getChildren()) {
                                System.out.println("Campo: " + child.getFullyQualifiedName());
                                System.out.println("Valor: " + child.getValueAsString());
                            }
                        } else {
                            System.out.println("Campo: " + field.getFullyQualifiedName());
                            System.out.println("Valor: " + field.getValueAsString());
                        }
                    }

                }

                PDField campo2 = acroForm2.getField("F[0].P1[0].NOMBRE_FIRMA[0]");
                campo2.setValue(normalizar(a.getId_arma().getId_usuario().getNombre() + " " + a.getId_arma().getId_usuario().getApellidos()));

                // Obtener el día, mes y año
                Instant instant = fechaActual.toInstant();
                LocalDate fechaLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                int dia = fechaLocalDate.getDayOfMonth();
                int mes = fechaLocalDate.getMonthValue();
                int anio = fechaLocalDate.getYear();

                campo2 = acroForm2.getField("F[0].P1[0].LOCALIDAD_FIRMA[0]");
                campo2.setValue("Sevilla");

                campo2 = acroForm2.getField("F[0].P1[0].DIA_FIRMA[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].P1[0].MES_FIRMA[0]");
                campo2.setValue(String.valueOf(mes));

                campo2 = acroForm2.getField("F[0].P1[0].AÑO_FIRMA[0]");
                campo2.setValue(String.valueOf(anio));

                campo2 = acroForm2.getField("F[0].P1[0].CasillaVerificación1[0]");
                campo2.setValue("1");

                campo2 = acroForm2.getField("F[0].P1[0].CasillaVerificación2[0]");
                campo2.setValue("1");

                campo2 = acroForm2.getField("F[0].P1[0].TIPO_ARMA[0]");
                campo2.setValue(a.getId_arma().getId_tipo_arma().getTipo());

                campo2 = acroForm2.getField("F[0].P1[0].MARCA_ARMA[0]");
                campo2.setValue(normalizar(a.getId_arma().getMarca()));

                campo2 = acroForm2.getField("F[0].P1[0].CALIBRE_ARMA[0]");
                campo2.setValue(normalizar(af.getCalibre()));
                campo2 = acroForm2.getField("F[0].P1[0].NUMERO_ARMA[0]");
                campo2.setValue(normalizar(af.getNum_identificacion()));

                campo2 = acroForm2.getField("F[0].P1[0].domicilio_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getDireccion()));

                campo2 = acroForm2.getField("F[0].P1[0].localidad_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getId_municipio().getNombre()));

                campo2 = acroForm2.getField("F[0].P1[0].EMAIL_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getEmail()));

                campo2 = acroForm2.getField("F[0].P1[0].MOVIL_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getTelefono()));

                campo2 = acroForm2.getField("F[0].P1[0].telefono_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getTelefono()));

                campo2 = acroForm2.getField("F[0].P1[0].provincia_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getId_municipio().getProvincia().getNombre()));

                campo2 = acroForm2.getField("F[0].P1[0].calle_COMPRADOR[0]");
                campo2.setValue(normalizar("VIA"));

                campo2 = acroForm2.getField("F[0].P1[0].NOMBRE_FIRMA[1]");
                campo2.setValue(normalizar(usuario.getNombre() + " " + usuario.getApellidos()));

                campo2 = acroForm2.getField("F[0].P1[0].NOMBRE_FIRMA[1]");
                campo2.setValue(normalizar(usuario.getNombre() + " " + usuario.getApellidos()));

                campo2 = acroForm2.getField("F[0].P1[0].DNI_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNif()));

                campo2 = acroForm2.getField("F[0].P1[0].NOMBRE_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNombre() + " " + usuario.getApellidos()));

                campo2 = acroForm2.getField("F[0].P1[0].PADRE_COMPRDOR[0]");
                campo2.setValue(normalizar(usuario.getNombre_padre()));

                campo2 = acroForm2.getField("F[0].P1[0].MADRE_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNombre_madre()));

                instant = usuario.getFecha_nacimiento().toInstant();
                fechaLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                dia = fechaLocalDate.getDayOfMonth();
                mes = fechaLocalDate.getMonthValue();
                anio = fechaLocalDate.getYear();

                campo2 = acroForm2.getField("F[0].P1[0].F_NAC_1_COMPRADOR[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].P1[0].MADRE_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNombre_madre()));

                campo2 = acroForm2.getField("F[0].P1[0].LUGAR_NACIMIENTO_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNacido_en()));

                campo2 = acroForm2.getField("F[0].P1[0].PROVINCIA_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getProvincia_nacimiento()));

                campo2 = acroForm2.getField("F[0].P1[0].F_NAC_2_COMPRADOR[0]");
                campo2.setValue(String.valueOf(mes));

                campo2 = acroForm2.getField("F[0].P1[0].F_NAC_3_COMPRADOR[0]");
                campo2.setValue(String.valueOf(anio));

                instant = af.getFecha_expedicion_guia().toInstant();
                fechaLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                dia = fechaLocalDate.getDayOfMonth();
                mes = fechaLocalDate.getMonthValue();
                anio = fechaLocalDate.getYear();

                campo2 = acroForm2.getField("F[0].P1[0].GUIA_ARMA[0]");
                campo2.setValue(normalizar(af.getNum_guia()));

                campo2 = acroForm2.getField("F[0].P1[0].F_EXP_GUIA_DIA[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].P1[0].F_EXP_GUIA_MES[0]");
                campo2.setValue(String.valueOf(mes));

                campo2 = acroForm2.getField("F[0].P1[0].F_EXP_GUIA_AÑO[0]");
                campo2.setValue(String.valueOf(anio));

                campo2 = acroForm2.getField("F[0].P1[0].INTER_EXPEDICION[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getId_municipio().getProvincia().getNombre()));

                campo2 = acroForm2.getField("F[0].P1[0].DNI_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNif()));

                campo2 = acroForm2.getField("F[0].P1[0].NOMBRE_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNombre() + " " + af.getId_arma().getId_usuario().getApellidos()));

                campo2 = acroForm2.getField("F[0].P1[0].PADRE_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNombre_padre()));

                campo2 = acroForm2.getField("F[0].P1[0].MADRE_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNombre_madre()));

                instant = af.getId_arma().getId_usuario().getFecha_nacimiento().toInstant();
                fechaLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                dia = fechaLocalDate.getDayOfMonth();
                mes = fechaLocalDate.getMonthValue();
                anio = fechaLocalDate.getYear();

                campo2 = acroForm2.getField("F[0].P1[0].F_NAC_1_SOLICIT[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].P1[0].F_NAC_1_SOLICIT[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].P1[0].LUGAR_NACIMIENTO_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNacido_en()));

                campo2 = acroForm2.getField("F[0].P1[0].PROVINCIA_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getProvincia_nacimiento()));

                campo2 = acroForm2.getField("F[0].P1[0].F_NAC_2_SOLICIT[0]");
                campo2.setValue(String.valueOf(mes));

                campo2 = acroForm2.getField("F[0].P1[0].F_NAC_3_SOLICIT[0]");
                campo2.setValue(String.valueOf(anio));

                campo2 = acroForm2.getField("F[0].P1[0].domicilio_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getDireccion()));

                campo2 = acroForm2.getField("F[0].P1[0].localidad_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getId_municipio().getNombre()));

                campo2 = acroForm2.getField("F[0].P1[0].telefono_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getTelefono()));

                campo2 = acroForm2.getField("F[0].P1[0].MOVIL_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getTelefono()));

                campo2 = acroForm2.getField("F[0].P1[0].EMAIL_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getEmail()));

                campo2 = acroForm2.getField("F[0].P1[0].provincia_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getId_municipio().getProvincia().getNombre()));

                campo2 = acroForm2.getField("F[0].P1[0].calle_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getId_municipio().getProvincia().getNombre()));

                //PAG 2 
                campo2 = acroForm2.getField("F[0].#subform[1].NOMBRE_FIRMA[0]");
                campo2.setValue(normalizar(a.getId_arma().getId_usuario().getNombre() + " " + a.getId_arma().getId_usuario().getApellidos()));

                // Obtener el día, mes y año
                instant = fechaActual.toInstant();
                fechaLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                dia = fechaLocalDate.getDayOfMonth();
                mes = fechaLocalDate.getMonthValue();
                anio = fechaLocalDate.getYear();

                campo2 = acroForm2.getField("F[0].#subform[1].LOCALIDAD_FIRMA[0]");
                campo2.setValue("Sevilla");

                campo2 = acroForm2.getField("F[0].#subform[1].DIA_FIRMA[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].#subform[1].MES_FIRMA[0]");
                campo2.setValue(String.valueOf(mes));

                campo2 = acroForm2.getField("F[0].#subform[1].AÑO_FIRMA[0]");
                campo2.setValue(String.valueOf(anio));

                campo2 = acroForm2.getField("F[0].#subform[1].CasillaVerificación1[0]");
                campo2.setValue("1");

                campo2 = acroForm2.getField("F[0].#subform[1].CasillaVerificación2[0]");
                campo2.setValue("1");

                campo2 = acroForm2.getField("F[0].#subform[1].TIPO_ARMA[0]");
                campo2.setValue(a.getId_arma().getId_tipo_arma().getTipo());

                campo2 = acroForm2.getField("F[0].#subform[1].MARCA_ARMA[0]");
                campo2.setValue(normalizar(a.getId_arma().getMarca()));

                campo2 = acroForm2.getField("F[0].#subform[1].CALIBRE_ARMA[0]");
                campo2.setValue(normalizar(af.getCalibre()));
                campo2 = acroForm2.getField("F[0].#subform[1].NUMERO_ARMA[0]");
                campo2.setValue(normalizar(af.getNum_identificacion()));

                campo2 = acroForm2.getField("F[0].#subform[1].domicilio_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getDireccion()));

                campo2 = acroForm2.getField("F[0].#subform[1].localidad_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getId_municipio().getNombre()));

                campo2 = acroForm2.getField("F[0].#subform[1].EMAIL_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getEmail()));

                campo2 = acroForm2.getField("F[0].#subform[1].MOVIL_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getTelefono()));

                campo2 = acroForm2.getField("F[0].#subform[1].telefono_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getTelefono()));

                campo2 = acroForm2.getField("F[0].#subform[1].provincia_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getId_municipio().getProvincia().getNombre()));

                campo2 = acroForm2.getField("F[0].#subform[1].calle_COMPRADOR[0]");
                campo2.setValue(normalizar("VIA"));

                campo2 = acroForm2.getField("F[0].#subform[1].NOMBRE_FIRMA[1]");
                campo2.setValue(normalizar(usuario.getNombre() + " " + usuario.getApellidos()));

                campo2 = acroForm2.getField("F[0].#subform[1].NOMBRE_FIRMA[1]");
                campo2.setValue(normalizar(usuario.getNombre() + " " + usuario.getApellidos()));

                campo2 = acroForm2.getField("F[0].#subform[1].DNI_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNif()));

                campo2 = acroForm2.getField("F[0].#subform[1].NOMBRE_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNombre() + " " + usuario.getApellidos()));

                campo2 = acroForm2.getField("F[0].#subform[1].PADRE_COMPRDOR[0]");
                campo2.setValue(normalizar(usuario.getNombre_padre()));

                campo2 = acroForm2.getField("F[0].#subform[1].MADRE_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNombre_madre()));

                instant = usuario.getFecha_nacimiento().toInstant();
                fechaLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                dia = fechaLocalDate.getDayOfMonth();
                mes = fechaLocalDate.getMonthValue();
                anio = fechaLocalDate.getYear();

                campo2 = acroForm2.getField("F[0].#subform[1].F_NAC_1_COMPRADOR[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].#subform[1].MADRE_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNombre_madre()));

                campo2 = acroForm2.getField("F[0].#subform[1].LUGAR_NACIMIENTO_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getNacido_en()));

                campo2 = acroForm2.getField("F[0].#subform[1].PROVINCIA_COMPRADOR[0]");
                campo2.setValue(normalizar(usuario.getProvincia_nacimiento()));

                campo2 = acroForm2.getField("F[0].#subform[1].F_NAC_2_COMPRADOR[0]");
                campo2.setValue(String.valueOf(mes));

                campo2 = acroForm2.getField("F[0].#subform[1].F_NAC_3_COMPRADOR[0]");
                campo2.setValue(String.valueOf(anio));

                instant = af.getFecha_expedicion_guia().toInstant();
                fechaLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                dia = fechaLocalDate.getDayOfMonth();
                mes = fechaLocalDate.getMonthValue();
                anio = fechaLocalDate.getYear();

                campo2 = acroForm2.getField("F[0].#subform[1].GUIA_ARMA[0]");
                campo2.setValue(normalizar(af.getNum_guia()));

                campo2 = acroForm2.getField("F[0].#subform[1].F_EXP_GUIA_DIA[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].#subform[1].F_EXP_GUIA_MES[0]");
                campo2.setValue(String.valueOf(mes));

                campo2 = acroForm2.getField("F[0].#subform[1].F_EXP_GUIA_AÑO[0]");
                campo2.setValue(String.valueOf(anio));

                campo2 = acroForm2.getField("F[0].#subform[1].INTER_EXPEDICION[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getId_municipio().getProvincia().getNombre()));

                campo2 = acroForm2.getField("F[0].#subform[1].DNI_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNif()));

                campo2 = acroForm2.getField("F[0].#subform[1].NOMBRE_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNombre() + " " + af.getId_arma().getId_usuario().getApellidos()));

                campo2 = acroForm2.getField("F[0].#subform[1].PADRE_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNombre_padre()));

                campo2 = acroForm2.getField("F[0].#subform[1].MADRE_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNombre_madre()));

                instant = af.getId_arma().getId_usuario().getFecha_nacimiento().toInstant();
                fechaLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                dia = fechaLocalDate.getDayOfMonth();
                mes = fechaLocalDate.getMonthValue();
                anio = fechaLocalDate.getYear();

                campo2 = acroForm2.getField("F[0].#subform[1].F_NAC_1_SOLICIT[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].#subform[1].F_NAC_1_SOLICIT[0]");
                campo2.setValue(String.valueOf(dia));

                campo2 = acroForm2.getField("F[0].#subform[1].LUGAR_NACIMIENTO_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getNacido_en()));

                campo2 = acroForm2.getField("F[0].#subform[1].PROVINCIA_SOLICIT[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getProvincia_nacimiento()));

                campo2 = acroForm2.getField("F[0].#subform[1].F_NAC_2_SOLICIT[0]");
                campo2.setValue(String.valueOf(mes));

                campo2 = acroForm2.getField("F[0].#subform[1].F_NAC_3_SOLICIT[0]");
                campo2.setValue(String.valueOf(anio));

                campo2 = acroForm2.getField("F[0].#subform[1].domicilio_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getDireccion()));

                campo2 = acroForm2.getField("F[0].#subform[1].localidad_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getId_municipio().getNombre()));

                campo2 = acroForm2.getField("F[0].#subform[1].telefono_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getTelefono()));

                campo2 = acroForm2.getField("F[0].#subform[1].MOVIL_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getTelefono()));

                campo2 = acroForm2.getField("F[0].#subform[1].EMAIL_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getEmail()));

                campo2 = acroForm2.getField("F[0].#subform[1].provincia_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getId_municipio().getProvincia().getNombre()));

                campo2 = acroForm2.getField("F[0].#subform[1].calle_habitual[0]");
                campo2.setValue(normalizar(af.getId_arma().getId_usuario().getId_municipio().getProvincia().getNombre()));

                File destino = new File(rutaNuevoPdf);
                destino.getParentFile().mkdirs();

                document2.save(destino);
                document2.close();

                ContratoCompraVentaJpaController ccvjc = new ContratoCompraVentaJpaController(emf);

                ContratoCompraVenta ccv = new ContratoCompraVenta();
                ccv.setId_factura(f);
                ccv.setUrl_contrato_compra_venta(rutaRelativaNuevoPdf);

                try {
                    ccvjc.create(ccv);
                } catch (Exception e) {

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        getServletContext().getRequestDispatcher(vista).forward(request, response);
    }
    
    static String normalizar(String texto){
        
        texto=Normalizer.normalize(texto,Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return texto;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
