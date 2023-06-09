
package controladores.moderador;
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
import modelo.dao.HiloJpaController;
import modelo.dao.MensajeJpaController;
import modelo.dao.NotificacionJpaController;

import modelo.dao.UsuarioJpaController;
import modelo.entidades.Hilo;
import modelo.entidades.Mensaje;
import modelo.entidades.Notificacion;

import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "BorrarHilo", urlPatterns = {"/moderador/BorrarHilo"})
public class BorrarHilo extends HttpServlet {

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
       
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
        
        UsuarioJpaController ujc=new UsuarioJpaController(emf);
        
        if(request.getParameter("hilo")!=null){
            Long idHilo= (Long.parseLong(request.getParameter("hilo")));
            HiloJpaController hjc=new HiloJpaController(emf);
            Hilo h=hjc.findHilo(idHilo);
            MensajeJpaController mjc=new MensajeJpaController(emf);
            
            
            Notificacion n= new Notificacion();
            NotificacionJpaController njc=new NotificacionJpaController(emf);
            for(Mensaje mensaje:mjc.findMensajeEntities()){
                if(mensaje.getId_hilo().getId_hilo().equals(idHilo)){
                    
                    try{
                        
                mjc.destroy(mensaje.getId_mensaje());
                
            n.setMensaje("El mensaje con contenido"+ mensaje.getContenido()+ " se ha borrado debido a que el hilo que lo contenia se ha borrado" );
                        n.setId_usuario(mensaje.getId_usuario());
                        njc.create(n);
                
                        
                        
            }catch(Exception e){
                
            }
                
                }
            
            }
            
            try{
                
                hjc.destroy(idHilo);
                n.setMensaje("El hilo "+h.getTitutlo_descriptivo()+" se ha eliminado por el moderador por no ser adecuado");
                njc.create(n);
            }catch(Exception e){
                
            }
            
            
            
            response.sendRedirect("./ModerarForo");
            
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
