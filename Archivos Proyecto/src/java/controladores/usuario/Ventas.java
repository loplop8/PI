
package controladores.usuario;
/**
 *
 * @author Zatonio
 */


import java.io.IOException;
import java.lang.reflect.Array;
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
import modelo.dao.ContratoCompraVentaJpaController;
import modelo.dao.FacturaJpaController;
import modelo.dao.PedidoJpaController;
import modelo.entidades.Anuncio;

import modelo.entidades.ContratoCompraVenta;
import modelo.entidades.Factura;
import modelo.entidades.Pedido;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "Ventas", urlPatterns = {"/usuario/Ventas"})
public class Ventas extends HttpServlet {

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
        String vista = "/usuario/misVentas.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife"); 
        PedidoJpaController pjc = new PedidoJpaController(emf);
         //Mandamos los pedidos a la vista
        List<Pedido> pedidos=new ArrayList<>();
        
        for (Pedido pedido: pjc.findPedidoEntities()) {
            if(pedido.getId_arma().getId_usuario().getId_usuario().equals(usuario.getId_usuario())){
                pedidos.add(pedido);
         }
        }
        request.setAttribute("pedidos",pedidos);
        
        
        FacturaJpaController fjc= new FacturaJpaController(emf);
        List<Factura>facturas=new ArrayList<>();
        
        ContratoCompraVentaJpaController ccjc=new ContratoCompraVentaJpaController(emf);
        
        List<ContratoCompraVenta> contratos=new ArrayList<>();
        for(ContratoCompraVenta contrato:ccjc.findContratoCompraVentaEntities()){
            if(contrato.getId_factura().getId_pedido().getId_arma().getId_usuario().getId_usuario().equals(usuario.getId_usuario())){
                contratos.add(contrato);
            }
        }
        
        request.setAttribute("contratos",contratos );
        
        AnuncioJpaController ajc=new AnuncioJpaController(emf);
        List<Anuncio> anuncios=new ArrayList<>();
        
        
        for(Anuncio anuncio:ajc.findAnuncioEntities()){
            if(anuncio.getId_arma().getId_usuario().getId_usuario().equals(usuario.getId_usuario())){
                anuncios.add(anuncio);
            }
            
        }
        
        
        request.setAttribute("anuncios", anuncios);
        
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
