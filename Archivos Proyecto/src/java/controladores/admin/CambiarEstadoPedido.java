
package controladores.admin;
/**
 *
 * @author Zatonio
 */

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
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
import com.paypal.api.payments.*;
import com.paypal.*;
import com.paypal.base.rest.PayPalRESTException;
import java.util.Arrays;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.logging.Level;
import java.util.logging.Logger;



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
            throws ServletException, IOException, PayPalRESTException {
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
            }else if(ep.getId_estado_pedido()==4L){
                
        String clientId = "Ac8yE_eDwR7B_uRD6u4It8W6ibZnoJYxjF53Pl6qX9IV4o-Tde-sCjPbHSI23QO6O-I_ykr6nbRCBt3X";
        String clientSecret = "EN7XLoBZktp-zaDmTGSO2oAxBdvKtgOrU-tHK0XifjuDBAkuBQ3HPejKgLlvIWP1kKDmEdjgrGxiYpns";
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
        PayoutSenderBatchHeader batchHeader = new PayoutSenderBatchHeader();
        Payout payout = new Payout();
        payout.setSenderBatchHeader(batchHeader);
        payout.getSenderBatchHeader().setSenderBatchId("Pedidoo :"+pedido.getNumero_pedido().toString());
        payout.getSenderBatchHeader().setEmailSubject(pedido.getId_arma().getId_usuario().getEmail());
        
        
        Currency amount = new Currency();
       AnuncioJpaController ajc=new AnuncioJpaController(emf);
       
       for(Anuncio anuncio: ajc.findAnuncioEntities()){
           if(anuncio.getId_arma().getId_arma().equals(pedido.getId_arma().getId_arma())){
                amount.setValue(anuncio.getPrecio().toString());
           }
       }
        
        
        
        amount.setCurrency("EUR");

        PayoutItem payoutItem = new PayoutItem();
        payoutItem.setRecipientType("EMAIL");
        payoutItem.setAmount(amount);
        payoutItem.setReceiver("receptor@example.com");
        payoutItem.setSenderItemId("Venta del arma "+pedido.getId_arma().getId_tipo_arma().getTipo()+" "+pedido.getId_arma().getMarca());

        payout.setItems(Arrays.asList(payoutItem));

        try {
            PayoutBatch payoutBatch = payout.create(apiContext, null);
            System.out.println("Payout Batch ID: " + payoutBatch.getBatchHeader().getPayoutBatchId());
            
            
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        ep=epjc.findEstadoPedido(5L);
        pedido.setId_estado_pedido(ep);
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
        try {
            processRequest(request, response);
        } catch (PayPalRESTException ex) {
            Logger.getLogger(CambiarEstadoPedido.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (PayPalRESTException ex) {
            Logger.getLogger(CambiarEstadoPedido.class.getName()).log(Level.SEVERE, null, ex);
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
