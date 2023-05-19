
package controladores.usuario;
/**
 *
 * @author Zatonio
 */

import controladores.Registro;
import controladores.moderador.*;
import controladores.admin.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ArmaJpaController;
import modelo.dao.TipoArmaJpaController;
import modelo.entidades.Arma;
import modelo.entidades.TipoArma;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "CrearAnuncio", urlPatterns = {"/usuario/CrearAnuncio"})
public class CrearAnuncio extends HttpServlet {

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
        String vista = "/usuario/crearAnuncio.jsp";
        String vistaError="/usuario/errorCambiandoImagen.jsp";
        String vistaImagenAnversoGuia="/usuario/anadirImagenAnversoGuia.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        
        
        
        if(usuario.getDni_validado()==false){
            String error="Lo sentimos, su dni debe estar validado para poder crear un anuncio";
            request.setAttribute("errorImagen", error);
            
            
            getServletContext().getRequestDispatcher(vistaError).forward(request, response);
        }
        
        if(request.getParameter("tipo_arma")!=null && request.getParameter("num_guia")!=null && request.getParameter("calibre")!=null && request.getParameter("num_identificacion")!=null && request.getParameter("fecha_expedicion_guia")!=null && request.getParameter("licencia")!=null    ){
            Long idLicencia=Long.parseLong(request.getParameter("licencia"));
            String marca=request.getParameter("marca");
            String num_guia=request.getParameter("num_guia");
            String calibre=request.getParameter("calibre");
            String num_identificacion=request.getParameter("num_identificacion");
            
            Date fecha_expedicion_guia=null;
            if (fecha_expedicion_guia == null) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // Cambiamos el formato de la fecha

                try {
                    fecha_expedicion_guia = format.parse(request.getParameter("fecha_expedicion_guia")); //
                } catch (ParseException ex) {
                    Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
            String idTipoArma=request.getParameter("tipo_arma");
            Long idTipoArmaLong=Long.parseLong(idTipoArma);
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");                
            TipoArma ta;
            TipoArmaJpaController tajc= new TipoArmaJpaController(emf); 
             ta= tajc.findTipoArma(idTipoArmaLong);
            Arma a=new Arma();
            a.setId_tipo_arma(ta); //En la persistencia recuperamos el id
            a.setId_usuario(usuario);
            a.setMarca(marca);
            ArmaJpaController ajc=new ArmaJpaController(emf);
            try{
             ajc.create(a);
            
            }catch(Exception e){
                
            }

            
            request.getSession().setAttribute("idLicencia", idLicencia);
            request.getSession().setAttribute("arma", a);
            request.getSession().setAttribute("num_guia", num_guia);
            request.getSession().setAttribute("calibre", calibre);
            request.getSession().setAttribute("fecha_expedicion_guia", fecha_expedicion_guia);
            request.getSession().setAttribute("num_identificacion", num_identificacion);
            
            
            
            
            getServletContext().getRequestDispatcher(vistaImagenAnversoGuia).forward(request, response);
            
        }else if(request.getParameter("tipo_arma")!=null && request.getParameter("tipo_gas")!=null&& request.getParameter("capacidad_cargador")!=null && request.getParameter("piezas_canon")!=null){
             Long idLicencia=Long.parseLong(request.getParameter("licencia"));
            String marca=request.getParameter("marca");
            String num_guia=request.getParameter("num_guia");
            String calibre=request.getParameter("calibre");
            String num_identificacion=request.getParameter("num_identificacion");
           
            
            String idTipoArma=request.getParameter("tipo_arma");
            Long idTipoArmaLong=Long.parseLong(idTipoArma);
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
                
            TipoArma ta;
            TipoArmaJpaController tajc= new TipoArmaJpaController(emf);
            
             ta= tajc.findTipoArma(idTipoArmaLong);

            Arma a=new Arma();
            a.setId_tipo_arma(ta); //En la persistencia recuperamos el id
            a.setId_usuario(usuario);
            a.setMarca(marca);
            ArmaJpaController ajc=new ArmaJpaController(emf);
            try{
             ajc.create(a);
            
            }catch(Exception e){
                
            }
            
            
            
            getServletContext().getRequestDispatcher(vistaError).forward(request, response);
        }else{
        
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
            Logger.getLogger(CrearAnuncio.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CrearAnuncio.class.getName()).log(Level.SEVERE, null, ex);
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
