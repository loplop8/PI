
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
import modelo.dao.HiloJpaController;
import modelo.dao.MensajeJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.Hilo;
import modelo.entidades.Mensaje;

import modelo.entidades.Usuario;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "/VerHilo", urlPatterns = {"/VerHilo"}  )
public class VerHilo extends HttpServlet {
    
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
        
        
        
        String vista = "/verHilo.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if(usuario!=null){
        request.setAttribute("usuario", usuario);    
        }
        
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
        HiloJpaController hjc=new HiloJpaController(emf);
        Long idHilo;
        if(request.getParameter("hiloVer")!=null){
            idHilo=Long.valueOf(request.getParameter("hiloVer"));
        }else{
            idHilo=(Long)request.getSession().getAttribute("hiloVer");
            request.getSession().removeAttribute("hiloVer");
        }
         
    
        Hilo h= hjc.findHilo(idHilo);
       
        request.setAttribute("hilo", h);
        
        List<Mensaje> mensajes=new ArrayList<>();
        MensajeJpaController mjc=new MensajeJpaController(emf);
        
        for(Mensaje mensaje:mjc.findMensajeEntities()){
            if(mensaje.getId_hilo().getId_hilo().equals(idHilo)){
                mensajes.add(mensaje);
            }
        }
        request.setAttribute("mensajes", mensajes);
        
       getServletContext().getRequestDispatcher(vista).forward(request, response);
      
       
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
