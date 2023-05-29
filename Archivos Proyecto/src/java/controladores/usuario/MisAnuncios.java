
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
import modelo.dao.AnuncioJpaController;
import modelo.dao.ArmaFuegoJpaController;
import modelo.dao.ArmaReplicaJpaController;
import modelo.dao.LicenciaJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.ArmaFuego;
import modelo.entidades.ArmaReplica;
import modelo.entidades.Licencia;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "MisAnuncios", urlPatterns = {"/usuario/MisAnuncios"})
public class MisAnuncios extends HttpServlet {

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
        String vista="/usuario/misAnuncios.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
         request.setAttribute("usuario", usuario);
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
         AnuncioJpaController ajc=new AnuncioJpaController(emf);
         List<Anuncio> anuncios= ajc.findAnuncioEntities();
         List<Anuncio> anunciosUsuario=new ArrayList<>();
         
         
         
         
         for (Anuncio anuncio: anuncios) {
            if(anuncio.getId_arma().getId_usuario().getId_usuario().equals(usuario.getId_usuario())){
                anunciosUsuario.add(anuncio);
               
        }
         }
         
         request.setAttribute("anuncios", anunciosUsuario);
        
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
