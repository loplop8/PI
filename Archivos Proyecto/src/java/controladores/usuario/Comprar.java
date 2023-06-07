
package controladores.usuario;
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
import modelo.dao.AnuncioJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.Usuario;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
import java.util.Collections;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "Comprar", urlPatterns = {"/usuario/Comprar"})
public class Comprar extends HttpServlet {

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
Long idAnuncio = Long.parseLong(request.getParameter("anuncio"));
EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
AnuncioJpaController ajc = new AnuncioJpaController(emf);
Anuncio a = ajc.findAnuncio(idAnuncio);
request.getSession().setAttribute("anuncio", a);

APIContext apiContext = new APIContext("Ac8yE_eDwR7B_uRD6u4It8W6ibZnoJYxjF53Pl6qX9IV4o-Tde-sCjPbHSI23QO6O-I_ykr6nbRCBt3X", "EN7XLoBZktp-zaDmTGSO2oAxBdvKtgOrU-tHK0XifjuDBAkuBQ3HPejKgLlvIWP1kKDmEdjgrGxiYpns", "sandbox");

Payer payer = new Payer();
payer.setPaymentMethod("paypal");

Amount amount = new Amount();
Double total=(0.21*(a.getPrecio()*0.125+a.getPrecio()*0.05+a.getPrecio()))+(a.getPrecio()*0.125+a.getPrecio()*0.05+a.getPrecio());

amount.setTotal(String.format("%.2f", total).replace(",", "."));
amount.setCurrency("EUR");

Transaction transaction = new Transaction();
transaction.setAmount(amount);
transaction.setDescription("Compra del Arma: " + a.getTitulo());

Payment payment = new Payment();
payment.setIntent("sale");

RedirectUrls redirectUrls = new RedirectUrls();
redirectUrls.setReturnUrl("http://localhost:8080/SecondWeaponLife/usuario/CompraCompletada");
redirectUrls.setCancelUrl("http://localhost:8080/SecondWeaponLife/Inicio");
payment.setRedirectUrls(redirectUrls);

payment.setPayer(payer);
payment.setTransactions(Collections.singletonList(transaction));

try {
    Payment createdPayment = payment.create(apiContext);
    String redirectUrl = createdPayment.getLinks().stream()
            .filter(link -> link.getRel().equals("approval_url"))
            .findFirst()
            .get()
            .getHref();
    response.sendRedirect(redirectUrl);
    System.out.println("Redirige al usuario a: " + redirectUrl);
} catch (PayPalRESTException e) {
    e.printStackTrace();
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
