
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
import modelo.dao.MensajeJpaController;
import modelo.entidades.Hilo;
import modelo.entidades.Mensaje;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "PublicarMensaje", urlPatterns = {"/usuario/PublicarMensaje"})
public class PublicarMensaje extends HttpServlet {

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
            String vista = "/usuario/verHilo.jsp";
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            request.setAttribute("usuario", usuario);
            System.out.println(request.getParameter("hilo"));
            
            if(request.getParameter("hilo")!=null && request.getParameter("mensaje")!=null){
             Long idHilo=Long.valueOf(request.getParameter("hilo"));
             String mensaje=request.getParameter("mensaje");
             
             
              EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
                HiloJpaController hjc=new HiloJpaController(emf);
                Hilo h=hjc.findHilo(idHilo);
                Mensaje m=new Mensaje();
                m.setContenido(mensaje);
                Date d=new Date();
                m.setFecha_publicacion(d);
                m.setId_usuario(usuario);
                m.setId_hilo(h);
                
                MensajeJpaController mjc=new MensajeJpaController(emf);
                request.getSession().setAttribute("hiloVer", h.getId_hilo());
                try{
                    mjc.create(m);
                    response.sendRedirect("../VerHilo");
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
