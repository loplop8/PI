
package controladores.usuario;
/**
 *
 * @author Zatonio
 */
import controladores.*;
import java.io.IOException;
import java.util.Map;
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
@WebServlet(name = "EditarPerfil", urlPatterns = {"/usuario/EditarPerfil"}  )
public class EditarPerfil extends HttpServlet {
    
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
        String vista = "/usuario/editarPerfil.jsp";           
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        if (request.getParameter("email") != null || request.getParameter("email") != null || request.getParameter("telefono") != null) { //Si se recoge alguno de los tres parametros editables  es que ya ha estado en la vista y a pulsado en editar  en el fomrulario 
            // Editando
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
            UsuarioJpaController ujc= new UsuarioJpaController(emf);
            String error="";
            String email = request.getParameter("email");
            email = email.trim();

            
            String telefono = request.getParameter("telefono");
            
                
            String nickname = request.getParameter("nickname");
            

            usuario.setEmail(email);
            usuario.setNickname(nickname);
            usuario.setTelefono(telefono);
            

            if (email.isEmpty()) {
                error = "El email no puede estar vacio";
            } else if (nickname.isEmpty()) {
                error = "El nombre de usuario no puede estar vacío";
            } else if (telefono.isEmpty()) {
                error = "El nickname no puede estar vacío";
            } else{ 
                try {
                    ujc.edit(usuario);
                    
                    getServletContext().getRequestDispatcher(vista).forward(request, response);
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando el usuario");
                    request.setAttribute("usuario", usuario);
                }
            }

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.setAttribute("usuario", usuario);

            }
        } else { //Si hay algun error editando dejamos el usuario como estaba
            
            request.setAttribute("usuario", usuario);
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
