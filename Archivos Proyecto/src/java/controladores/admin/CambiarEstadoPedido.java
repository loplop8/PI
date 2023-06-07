
package controladores.admin;
/**
 *
 * @author Zatonio
 */

import java.io.IOException;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.AnuncioJpaController;
import modelo.dao.EstadoAnuncioJpaController;
import modelo.dao.EstadoPedidoJpaController;
import modelo.dao.PedidoJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.EstadoAnuncio;
import modelo.entidades.EstadoPedido;
import modelo.entidades.Pedido;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "CambiarEstadoPedido", urlPatterns = {"/admin/CambiarEstadoPedido"})
public class CambiarEstadoPedido extends HttpServlet {

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
        
           
        
        if(request.getParameter("pedidoEditar")!=null){
           
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife"); 
        PedidoJpaController pjc = new PedidoJpaController(emf);
        EstadoPedidoJpaController epjc= new EstadoPedidoJpaController(emf);
        Long id = Long.valueOf(request.getParameter("pedidoEditar"));
        
            Pedido pedido = pjc.findPedido(id);
            EstadoPedido ep= epjc.findEstadoPedido(pedido.getId_estado_pedido().getId_estado_pedido());
            
            
            if(ep.getId_estado_pedido()==1L){
                ep=epjc.findEstadoPedido(3L);
                pedido.setId_estado_pedido(ep);
            }else if(ep.getId_estado_pedido()==3L){
                ep=epjc.findEstadoPedido(4L);
                pedido.setId_estado_pedido(ep);
                Date hoy=new Date();
                pedido.setFecha_entrega(hoy);
            }
         
        
        try {
                    pjc.edit(pedido);
                    response.sendRedirect("./AdministrarPedidos");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando el pedido");
                    request.setAttribute("pedido", pedido);
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
