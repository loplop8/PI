
package controladores;
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
import modelo.dao.ArmaFuegoJpaController;
import modelo.dao.ArmaReplicaJpaController;
import modelo.dao.ImagenJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.ArmaFuego;
import modelo.entidades.ArmaReplica;
import modelo.entidades.Imagen;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "VerAnuncio", urlPatterns = {"/VerAnuncio"})
public class VerAnuncio extends HttpServlet {

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
        String vista = "/verAnuncio.jsp";
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        List<Imagen>imagenes= new ArrayList<>();
        Long idAnuncio= Long.parseLong(request.getParameter("anuncioVer"));
        
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");   
        AnuncioJpaController ajc= new AnuncioJpaController(emf);
        Anuncio anuncio=ajc.findAnuncio(idAnuncio);
        request.setAttribute( "anuncio",anuncio);
        ImagenJpaController ijc=new ImagenJpaController(emf);
        List<Imagen> imagenesAnuncio=new ArrayList<>();
        imagenes=ijc.findImagenEntities();
        for(Imagen imagen:imagenes){
            if(imagen.getId_anuncio().getId_anuncio().equals(idAnuncio)){
                imagenesAnuncio.add(imagen);
            }
        }
        
        request.setAttribute("imagenes", imagenesAnuncio);
        ArmaFuegoJpaController afjc=new ArmaFuegoJpaController(emf);
       
        if( afjc.findArmaFuego(anuncio.getId_arma().getId_arma()) != null){
            ArmaFuego af=afjc.findArmaFuego(anuncio.getId_arma().getId_arma());
            request.setAttribute("arma_fuego", af);
        }else{
            ArmaReplicaJpaController arjc=new ArmaReplicaJpaController(emf);
            ArmaReplica ar=arjc.findArmaReplica(anuncio.getId_arma().getId_arma());
             request.setAttribute("arma_replica", ar);
        }
          String comprar="comprar";
          request.setAttribute("comprar", comprar);
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
