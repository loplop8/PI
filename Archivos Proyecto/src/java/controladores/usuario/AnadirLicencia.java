
package controladores.usuario;
/**
 *
 * @author Zatonio
 */
import controladores.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Licencia;

import modelo.entidades.Usuario;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "AnadirLicencia", urlPatterns = {"/usuario/AnadirLicencia"}  )
public class AnadirLicencia extends HttpServlet {
    
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
        String vista = "/usuario/anadirLicencia.jsp";
        String controladorImagen="/usuario/AnadirImagenLicencia";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        String tipo_licencia="";
        String observaciones="";
        String restricciones=""; 
        if (request.getParameter("tipo_licencia") != null || request.getParameter("valida_desde") != null || request.getParameter("valida_hasta") != null || request.getParameter("fecha_expedicion") != null  || request.getParameter("observaciones") != null || request.getParameter("restricciones") != null ) {  
            
            tipo_licencia=request.getParameter("tipo_licencia");
            request.setAttribute("tipo_licencia",tipo_licencia);
            observaciones=request.getParameter("observaciones");
            request.setAttribute("observaciones",observaciones );
            restricciones=request.getParameter("restricciones");
            request.setAttribute("restricciones",restricciones );
            
            

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // Cambiamos el formato de la fecha

                try {
                  Date valida_desde = format.parse(request.getParameter("valida_desde")); //
                  request.setAttribute( "valida_desde",valida_desde);
                  
                  
                  Date valida_hasta = format.parse(request.getParameter("valida_hasta"));
                  request.setAttribute("valida_hasta", valida_hasta);
                  //
                  Date fecha_expedicion = format.parse(request.getParameter("fecha_expedicion"));
                  request.setAttribute("fecha_expedicion", fecha_expedicion);//
                  
                } catch (ParseException ex) {
                    Logger.getLogger(AnadirLicencia.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            
            
               getServletContext().getRequestDispatcher(controladorImagen).forward(request, response); 
            
        
        }else{
            getServletContext().getRequestDispatcher(vista).forward(request, response);
        }
        
            
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
