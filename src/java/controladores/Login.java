
package controladores;
/**
 *
 * @author Zatonio
 */
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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
import modelo.entidades.Usuario;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.security.NoSuchAlgorithmException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        String vista = "/login.jsp";
        // Si hemos recibido los datos del formulario
        if (request.getParameter("email") != null && 
                request.getParameter("contraseña") != null) {
            String email = request.getParameter("email");
            String contraseña = request.getParameter("contraseña");
           contraseña= security.Security.encriptaContraseña(contraseña);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
            
            
            UsuarioJpaController ujc = new UsuarioJpaController(emf);
            List<Usuario> usuarios = ujc.findUsuarioEntities();
            for (Usuario u : usuarios) {
                if (u.getEmail().equals(email) && u.getContraseña().equals(contraseña)) {
                    request.getSession().setAttribute("usuario", u);
                    if("admin".equals(u.getRol())){
                        response.sendRedirect("admin/PanelAdministracion");
                        return;
                        
                    }else if("moderador".equals(u.getRol())){
                        response.sendRedirect("moderador/PanelModeracion");
                        return;
                    }else{
                        response.sendRedirect("./Inicio");
                        return;
                    }
                    
                }
            }
        
            String error = "Usuario o contraseña incorrectos";
            request.setAttribute("error", error);
            request.setAttribute("email", email);
            request.setAttribute("contraseña", contraseña);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
