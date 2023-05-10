
package controladores.admin;
/**
 *
 * @author Zatonio
 */

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.LicenciaJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Licencia;
import modelo.entidades.Usuario;


/**
 *
 * @author Zatonio
 */
@WebServlet(name = "CambiarEstadoLicencia", urlPatterns = {"/admin/CambiarEstadoLicencia"})
public class CambiarEstadoLicencia extends HttpServlet {

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
        String vista = "/admin/error.jsp";
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        
        String error="";
        
           
        
        if(request.getParameter("licenciaEditar")!=null){
           
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife"); 
        LicenciaJpaController ljc = new LicenciaJpaController(emf);
        Long id = Long.valueOf(request.getParameter("licenciaEditar"));
        
            Licencia editar = ljc.findLicencia(id);
            
         if(editar.getLicencia_validada()==true){
             editar.setLicencia_validada(false);
         }else{
             editar.setLicencia_validada(true);
         }
         
           
        
        try {
                    ljc.edit(editar);
                    
                    response.sendRedirect("./AdministrarLicencias");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando la licencia");
                    request.setAttribute("usuario", usuario);
                }
            

            
        }else{
            error="Lo siento ha habido un fallo en la aplicacion";
            request.setAttribute("error", error);
            
        }
                 if (!error.isEmpty()) {
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
