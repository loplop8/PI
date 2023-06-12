
package controladores.usuario;
/**
 *
 * @author Zatonio
 */

import controladores.moderador.*;
import controladores.admin.*;
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
import modelo.dao.LicenciaJpaController;
import modelo.dao.NotificacionJpaController;
import modelo.entidades.Licencia;
import modelo.entidades.Notificacion;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "MisNotificaciones", urlPatterns = {"/usuario/MisNotificaciones"})
public class MisNotificaciones extends HttpServlet {

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
        String vista="/usuario/verNotificaciones.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
         request.setAttribute("usuario", usuario);
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
         NotificacionJpaController njc=new NotificacionJpaController(emf);
         List<Notificacion> notificaciones= njc.findNotificacionEntities();
         List<Notificacion> notificacionesUsuario=new ArrayList<>();
         List<Notificacion> notificacionesAdmin=new ArrayList<>();
         List<Notificacion> notificacionesMod=new ArrayList<>();
         
         
         
         if(usuario.getRol().equals("admin")){
             
             for (Notificacion notificacion : notificaciones) {
            if(notificacion.getId_usuario().getId_usuario().equals(usuario.getId_usuario())){
                 
                     notificacionesUsuario.add(notificacion); 
             
            }
            if(notificacion.getId_usuario().getRol().equals("admin")){
                notificacionesAdmin.add(notificacion);
            }
            
            
             }
             
             for (Notificacion notificacion : notificacionesAdmin) {
             
                 try{
                      njc.destroy(notificacion.getId_notificacion());
                  }   catch(Exception e){
                      System.out.println("Error borrando notificacion");
                  }
             }
             
             
             
         }else if(usuario.getRol().equals("moderador")){
         
                for (Notificacion notificacion : notificaciones) {
            if(notificacion.getId_usuario().getId_usuario().equals(usuario.getId_usuario())){
                 
                     notificacionesUsuario.add(notificacion); 
             
            }
            if(notificacion.getId_usuario().getRol().equals("moderador")){
                notificacionesMod.add(notificacion);
            }
            
            
             }
             
             for (Notificacion notificacion : notificacionesMod) {
             
                 try{
                      njc.destroy(notificacion.getId_notificacion());
                  }   catch(Exception e){
                      System.out.println("Error borrando notificacion");
                  }
             }   
         }
         else{
         for (Notificacion notificacion : notificaciones) {
            if(notificacion.getId_usuario().getId_usuario().equals(usuario.getId_usuario())){
                 
                     notificacionesUsuario.add(notificacion); 
                     
                     
                  try{
                      njc.destroy(notificacion.getId_notificacion());
                  }   catch(Exception e){
                      System.out.println("Error borrando notificacion");
                  }
               
                     
                  
        }
         }
         }
         
         request.setAttribute("notificaciones", notificacionesUsuario);
        
         
            getServletContext().getRequestDispatcher(vista).forward(request, response);
            
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
