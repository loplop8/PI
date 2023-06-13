
package controladores.admin;
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
import modelo.dao.AnuncioJpaController;
import modelo.dao.EstadoAnuncioJpaController;
import modelo.dao.NotificacionJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.EstadoAnuncio;
import modelo.entidades.Notificacion;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "CambiarEstadoAnuncio", urlPatterns = {"/admin/CambiarEstadoAnuncio"})
public class CambiarEstadoAnuncio extends HttpServlet {

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
        String vista = "/admin/error.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        
        String error="";
        
           
        
        if(request.getParameter("anuncioEditar")!=null){
           
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife"); 
        AnuncioJpaController ajc = new AnuncioJpaController(emf);
        EstadoAnuncioJpaController eajc= new EstadoAnuncioJpaController(emf);
        Long id = Long.valueOf(request.getParameter("anuncioEditar"));
        
            Anuncio anuncio = ajc.findAnuncio(id);
            EstadoAnuncio ea= eajc.findEstadoAnuncio(anuncio.getId_estado_anuncio().getId_estado_anuncio());
            
         if(ea.getId_estado_anuncio()==2){
             ea.setId_estado_anuncio(3L);
             Notificacion n= new Notificacion();
            n.setMensaje("El administrador "+usuario.getNickname()+" ha cambiado el estado del anuncio con titulo"+anuncio.getTitulo()+" a "+ea.getDescripcion());
            UsuarioJpaController ujc=new UsuarioJpaController(emf);
            NotificacionJpaController njc=new NotificacionJpaController(emf);
                        n.setId_usuario(anuncio.getId_arma().getId_usuario());
                        njc.create(n);
         }else{
             ea.setId_estado_anuncio(2L);
             Notificacion n= new Notificacion();
            n.setMensaje("El administrador  ha cambiado el estado del anuncio con titulo"+anuncio.getTitulo()+" a "+ea.getDescripcion());
            UsuarioJpaController ujc=new UsuarioJpaController(emf);
            NotificacionJpaController njc=new NotificacionJpaController(emf);
                        n.setId_usuario(anuncio.getId_arma().getId_usuario());
                        njc.create(n);
         }
         anuncio.setId_estado_anuncio(ea);
           
        
        try {
                    ajc.edit(anuncio);
                    if(ajc.esAnuncioArmasReplica(id)){
                        response.sendRedirect("./AdministrarAnunciosReplica");
                    }else{
                       response.sendRedirect("./AdministrarAnunciosFuego");
                    }
                  
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando el usuario");
                    request.setAttribute("usuario", usuario);
                }
            

            
        }else{
            error="Lo siento ha habido un fallo en la aplicacion";
            request.setAttribute("error", error);
            
        }
                 if (!error.isEmpty()) {
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
