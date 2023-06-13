
package controladores.moderador;
/**
 *
 * @author Zatonio
 */


import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.MensajeJpaController;
import modelo.dao.NotificacionJpaController;

import modelo.dao.UsuarioJpaController;
import modelo.entidades.Mensaje;
import modelo.entidades.Notificacion;

import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "BorrarMensaje", urlPatterns = {"/moderador/BorrarMensaje"})
public class BorrarMensaje extends HttpServlet {

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
        
        if(request.getParameter("mensaje")!=null && request.getParameter("hilo")!=null ){
            Long idHilo= (Long.parseLong(request.getParameter("hilo")));
            Long idMensaje=(Long.parseLong(request.getParameter("mensaje")));
            MensajeJpaController mjc=new MensajeJpaController(emf);
            Mensaje m=mjc.findMensaje(idMensaje);
            Notificacion n= new Notificacion();
            NotificacionJpaController njc= new NotificacionJpaController(emf);
            try{
                mjc.destroy(idMensaje);
                n.setMensaje("Se ha eliminado un mensaje por el moderador por contenido inadecuado");
                n.setId_usuario(m.getId_usuario());
                njc.create(n);
            }catch(Exception e){
                
            }
            
            request.getSession().setAttribute("hilo", idHilo);
            response.sendRedirect("./ModerarMensajes");
            
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
