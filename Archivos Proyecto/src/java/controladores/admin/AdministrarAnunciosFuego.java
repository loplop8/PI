
package controladores.admin;
/**
 *
 * @author Zatonio
 */

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.AnuncioJpaController;
import modelo.dao.ArmaFuegoJpaController;
import modelo.dao.ArmaReplicaJpaController;
import modelo.dao.ImagenJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.ArmaFuego;
import modelo.entidades.ArmaReplica;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "AdministrarAnunciosFuego", urlPatterns = {"/admin/AdministrarAnunciosFuego"})
public class AdministrarAnunciosFuego extends HttpServlet {

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
        String vista = "/admin/administrarAnunciosFuego.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife"); 
        AnuncioJpaController ajc = new AnuncioJpaController(emf);
        List<Anuncio>anuncios=ajc.findAnuncioEntities();
        request.setAttribute("anuncios",anuncios);
        
        
        
        ArmaFuegoJpaController afjc= new ArmaFuegoJpaController(emf);
        request.setAttribute("armas_fuego", afjc.findArmaFuegoEntities());
       
        
        
        
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
