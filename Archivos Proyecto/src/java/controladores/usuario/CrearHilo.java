
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
import java.util.Date;
import modelo.dao.HiloJpaController;
import modelo.entidades.Hilo;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "CrearHilo", urlPatterns = {"/usuario/CrearHilo"})
public class CrearHilo extends HttpServlet {

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
            String vista = "/usuario/crearHilo.jsp";
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            request.setAttribute("usuario", usuario);
            
            
            
            if(request.getParameter("titulo_descriptivo")!=null && request.getParameter("tema")!=null){
             String titulo_descriptivo=  request.getParameter("titulo_descriptivo");
             String tema= request.getParameter("tema");
             
             
              EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
                HiloJpaController hjc=new HiloJpaController(emf);
                Hilo h=new Hilo();
                h.setTitutlo_descriptivo(titulo_descriptivo);
                h.setEtiqueta_tema(tema);
                Date d=new Date();
                h.setFecha_creacion(d);
                h.setId_usuario(usuario);
                
                try{
                    hjc.create(h);
                    response.sendRedirect("../Foro");
                    return;
                }catch(Exception e){
                    
                }
                
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
