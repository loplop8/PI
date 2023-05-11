/*
 * Servlet Controlador MenuDepartamentos.
 */
package controladores.usuario;

/**
 *
 * @author Zatonio
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.dao.LicenciaJpaController;
import modelo.dao.TipoLicenciaJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Licencia;
import modelo.entidades.TipoLicencia;
import modelo.entidades.Usuario;
import org.apache.tika.Tika;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "AnadirImagenDNI", urlPatterns = {"/usuario/AnadirImagenDNI"})
public class AnadirImagenDNI extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        String vista = "/usuario/anadirImagenAnversoDNI.jsp";
        String siguienteControlador = "/usuario/AnadirImagenReversoDNI";
    
  
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);

       
        if(request.getSession().getAttribute("anversodni")!=null && request.getSession().getAttribute("reversodni")!=null){
            
            String anverso=(String)request.getSession().getAttribute("anversodni");
            
            String reverso=(String)request.getSession().getAttribute("reversodni");
            
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
            UsuarioJpaController ujc=new UsuarioJpaController(emf);
            
            
            usuario.setUrl_img_dni_anverso(anverso);
            usuario.setUrl_img_dni_reverso(reverso);
            
            try{
                ujc.edit(usuario);
                 request.getSession().setAttribute("usuario", usuario);
                    response.sendRedirect("./VerImagenesDNI");
            }catch (RollbackException e) {
                    System.out.println(e);
                    String error = "";
                }
            
        
        
        }else if(request.getSession().getAttribute("anversodni")!=null){
            
            
         getServletContext().getRequestDispatcher(siguienteControlador).forward(request, response);
  
        }else{
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
        } catch (Exception ex) {
            Logger.getLogger(AnadirImagenDNI.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(AnadirImagenDNI.class.getName()).log(Level.SEVERE, null, ex);
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
