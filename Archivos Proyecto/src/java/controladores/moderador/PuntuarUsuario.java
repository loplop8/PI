
package controladores.moderador;
/**
 *
 * @author Zatonio
 */

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
import modelo.dao.HiloJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Hilo;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "PuntuarUsuario", urlPatterns = {"/moderador/PuntuarUsuario"})
public class PuntuarUsuario extends HttpServlet {

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
        String vista = "/moderador/moderarHilos.jsp";
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        Long idUsuario;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
        
        UsuarioJpaController ujc=new UsuarioJpaController(emf);
        
        if(request.getParameter("usuarioHilo")!=null){
           
           idUsuario=Long.parseLong(request.getParameter("usuarioHilo"));
            Usuario u=ujc.findUsuario(idUsuario);
            int puntuacionActual=u.getPuntuacion_foro();
            puntuacionActual--;
            u.setPuntuacion_foro(puntuacionActual);
            
            try{
                ujc.edit(u);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
            response.sendRedirect("./ModerarForo");
           
        }else if(request.getParameter("usuarioMensaje")!=null && request.getParameter("hilo")!=null ){
            idUsuario=Long.parseLong(request.getParameter("usuarioMensaje"));
          Long  idHilo=Long.parseLong(request.getParameter("hilo"));
          
          Usuario u=ujc.findUsuario(idUsuario);
            int puntuacionActual=u.getPuntuacion_foro();
            puntuacionActual--;
            u.setPuntuacion_foro(puntuacionActual);
          
            try{
                ujc.edit(u);
                
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
