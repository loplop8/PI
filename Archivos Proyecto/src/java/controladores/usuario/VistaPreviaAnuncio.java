
package controladores.usuario;
/**
 *
 * @author Zatonio
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Anuncio;
import modelo.entidades.ArmaFuego;
import modelo.entidades.ArmaReplica;
import modelo.entidades.Imagen;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "VistaPreviaAnuncio", urlPatterns = {"/usuario/VistaPreviaAnuncio"})
public class VistaPreviaAnuncio extends HttpServlet {

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
        String vista = "/usuario/verAnuncio.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        List<Imagen>imagenes= new ArrayList<>();
        imagenes=(List<Imagen>) request.getSession().getAttribute("imagenes");
        Anuncio an=(Anuncio)request.getSession().getAttribute("anuncio");
        if(request.getSession().getAttribute("arma_fuego")!=null){
            ArmaFuego af= (ArmaFuego)request.getSession().getAttribute("arma_fuego");
            request.getSession().setAttribute("arma_fuego", af);
        }else if(request.getSession().getAttribute("arma_replica")!=null){
            
        
                ArmaReplica ar= (ArmaReplica)request.getSession().getAttribute("arma_replica");
                request.getSession().setAttribute("arma_replica", ar);
        }       
        
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
