
package controladores;
/**
 *
 * @author Zatonio
 */
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.LicenciaJpaController;
import modelo.dao.TipoLicenciaJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Licencia;
import modelo.entidades.TipoLicencia;
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
            throws ServletException, IOException, NoSuchAlgorithmException, Exception {
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
                    
                    if(u.getEsta_activo()==false){
                        response.sendRedirect("./Error");
                        return;
                    }else{
                    
                    
                    if("admin".equals(u.getRol())){
                        response.sendRedirect("admin/PanelAdministracion");
                        return;
                        
                    }else if("moderador".equals(u.getRol())){
                        response.sendRedirect("moderador/PanelModeracion");
                        return;
                    }else{
            
            LicenciaJpaController ljc=new LicenciaJpaController(emf);            
            
            List<Licencia> licencias=new ArrayList<>();
            
            licencias=ljc.findLicenciaEntities();
            Boolean encontrado=false;
            for(Licencia l : licencias){
                if(Objects.equals(l.getId_usuario().getId_usuario(), u.getId_usuario())){
                    encontrado=true;
                    break;
                }
            }
            
            if(encontrado==false){
                TipoLicencia tl=new TipoLicencia();
            
            
            List<TipoLicencia>tiposLicencias=new ArrayList<TipoLicencia>();
            
            TipoLicenciaJpaController tljc=new TipoLicenciaJpaController(emf);
            
            
            tiposLicencias= tljc.findTipoLicenciaEntities();
            
                for(int i=0; i<tiposLicencias.size();i++){
                    if(tiposLicencias.get(i).getId_tipo_licencia()==1L){
                        tl=tiposLicencias.get(i);
                        break;
                    }
                }
            
                
            Licencia l= new Licencia();
            
            Date fecha=new Date();
            Date fechaFutura=new Date(fecha.getTime()+1000L*365*24*60*60*1000); //Fecha dentro de 1000 años
            l.setFechaExpedicion(fecha);
            l.setValida_desde(fecha);
             
            l.setValida_hasta(fechaFutura);
            l.setId_tipo_licencia(tl);
            l.setObservaciones("");
            l.setRestricciones("");
            l.setUrl_img_licencia_anverso("");
            l.setUrl_img_licencia_reverso("");
            l.setId_usuario(u);
            l.setLicencia_validada(true);
            
                System.out.println(l);
          
            
                 try { //Persistimos los datos en la base de datos
                    ljc.create(l);
                     response.sendRedirect("./Inicio");
                    return;
                    
                } catch (RollbackException e) {
                    System.out.println(e);
                    
                }
                response.sendRedirect("./Inicio");
                return;
            }else{
                response.sendRedirect("./Inicio");
                return;
            }
                        
                        
                        
                        
                        
                    }
                    
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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
