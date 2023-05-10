/*
 * Servlet Controlador MenuDepartamentos.
 */
package controladores.usuario;

/**
 *
 * @author Zatonio
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.dao.LicenciaJpaController;
import modelo.dao.TipoLicenciaJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Licencia;
import modelo.entidades.TipoLicencia;
import modelo.entidades.Usuario;
import org.apache.tika.Tika;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "AnadirImagenLicencia", urlPatterns = {"/usuario/AnadirImagenLicencia"})
public class AnadirImagenLicencia extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        String vista = "/usuario/anadirImagenAnversoLicencia.jsp";
        String siguienteControlador = "/usuario/AnadirImagenReversoLicencia";
        String finalControlador = "/usuario/FinalImagenenesLicencia";
        

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);

        Date valida_desde = new Date();
        Date valida_hasta = new Date();
        Date fecha_expedicion = new Date();
        String restricciones = "";
        String observaciones="";
        String tipo_licencia = "";
        String url_img_licencia_anverso = "";
        String url_img_licencia_reverso = "";
        if (request.getAttribute("valida_desde") != null && request.getAttribute("valida_hasta") != null && request.getAttribute("fecha_expedicion") != null
                && request.getAttribute("restricciones") != null && request.getAttribute("tipo_licencia") != null && request.getAttribute("observaciones")!=null) {
            valida_desde = (Date) request.getAttribute("valida_desde");
            valida_hasta = (Date) request.getAttribute("valida_hasta");
            fecha_expedicion = (Date) request.getAttribute("fecha_expedicion");
            restricciones = (String) request.getAttribute("restricciones");
            observaciones = (String) request.getAttribute("observaciones");
            tipo_licencia = (String) request.getAttribute("tipo_licencia");

            request.getSession().setAttribute("valida_desde", valida_desde);

            request.getSession().setAttribute("valida_hasta", valida_hasta);

            request.getSession().setAttribute("fecha_expedicion", fecha_expedicion);
            
            request.getSession().setAttribute("observaciones", observaciones);

            request.getSession().setAttribute("restricciones", restricciones);

            request.getSession().setAttribute("tipo_licencia", tipo_licencia);
            
        } else {

            observaciones= (String) request.getSession().getAttribute("observaciones");
            valida_desde = (Date) request.getSession().getAttribute("valida_desde");
            valida_hasta = (Date) request.getSession().getAttribute("valida_hasta");
            fecha_expedicion = (Date) request.getSession().getAttribute("fecha_expedicion");
            restricciones = (String) request.getSession().getAttribute("restricciones");
            tipo_licencia = (String) request.getSession().getAttribute("tipo_licencia");
                
        }

        if (request.getSession().getAttribute("anversoLicencia") != null && request.getSession().getAttribute("reversoLicencia") != null) {
            url_img_licencia_anverso= (String)request.getSession().getAttribute("anversoLicencia");
            url_img_licencia_reverso = (String)request.getSession().getAttribute("reversoLicencia");
            

              EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
                EntityManager em = emf.createEntityManager();
                
                TipoLicenciaJpaController tljc=new TipoLicenciaJpaController(emf);
                
                TipoLicencia tl = (tljc.findTipoLicencia(Long.parseLong(tipo_licencia)));
                LicenciaJpaController ljc=new LicenciaJpaController(emf);
            
                Licencia l=new Licencia();
                
                
                l.setId_tipo_licencia(tl);
                l.setId_usuario(usuario);
                l.setLicencia_validada(Boolean.FALSE);
                l.setObservaciones(observaciones);
                l.setValida_desde(valida_desde);
                l.setValida_hasta(valida_hasta);
                l.setFechaExpedicion(fecha_expedicion);
                l.setRestricciones(restricciones);
                l.setUrl_img_licencia_anverso(url_img_licencia_anverso);
                l.setUrl_img_licencia_reverso(url_img_licencia_reverso);
                
                try { //Persistimos los datos en la base de datos
                    ljc.create(l);
                    request.getSession().setAttribute("licencia", l);
                    response.sendRedirect("./VerLicenciaCreada");
                } catch (RollbackException e) {
                    System.out.println(e);
                    String error = "";
                }
                
            
            
            
        } else if (request.getSession().getAttribute("anversoLicencia") != null) {
            url_img_licencia_anverso = (String)request.getSession().getAttribute("anversoLicencia");
            request.getSession().setAttribute(url_img_licencia_anverso, "anversoLicencia");
            
            getServletContext().getRequestDispatcher(siguienteControlador).forward(request, response);
        } else {

            getServletContext().getRequestDispatcher(vista).forward(request, response);
        }

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AnadirImagenLicencia.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AnadirImagenLicencia.class.getName()).log(Level.SEVERE, null, ex);
        }
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
