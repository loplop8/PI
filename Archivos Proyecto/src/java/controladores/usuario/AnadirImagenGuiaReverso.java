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
import modelo.dao.ArmaFuegoJpaController;
import modelo.dao.LicenciaJpaController;
import modelo.dao.TipoLicenciaJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Arma;
import modelo.entidades.ArmaFuego;
import modelo.entidades.Licencia;
import modelo.entidades.TipoLicencia;
import modelo.entidades.Usuario;
import org.apache.tika.Tika;

/**
 *
 * @author Zatonio
 */

@MultipartConfig(maxFileSize = 10000000, fileSizeThreshold = 10000000)  //Añadimos la configuracion Multipart 
@WebServlet(name = "AnadirImagenGuiaReverso", urlPatterns = {"/usuario/AnadirImagenGuiaReverso"})
public class AnadirImagenGuiaReverso extends HttpServlet {
    private static final String UPLOAD_DIR = "/img/uploads/guia";
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
        
        String error="";
        String siguienteControlador="/usuario/crearAnversoGuia";
        String vista="/usuario/anadirImagenReversoGuia.jsp";
        String vistaError="../error.jsp";

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
            
            String url_anverso=(String)request.getSession().getAttribute("anversoguia");
            String num_guia= (String)request.getSession().getAttribute("num_guia");
            String calibre=  (String)request.getSession().getAttribute("calibre" );
            Date fecha_expedicion_guia= (Date) request.getSession().getAttribute("fecha_expedicion_guia" );
            String num_identificacion=(String) request.getSession().getAttribute("num_identificacion");
            Long idLicencia=(Long)(request.getSession().getAttribute("idLicencia"));
            
           
            Arma a= (Arma) request.getSession().getAttribute("arma");
            request.getSession().setAttribute("arma", a);
            
        if (request.getPart("file2")!= null){
            Part filePart = request.getPart("file2");
        
        // Obtiene el nombre del archivo del archivo de la solicitud
       String filePath = Paths.get(filePart.getSubmittedFileName()).toString();
        String fileType = filePath.substring(filePath.lastIndexOf('.') + 1);
        String userId = "id" + usuario.getId_usuario();

        String fileName = "reverso"+usuario.getNickname() + userId + "." + fileType;
        // Obtiene el stream de entrada del archivo de la solicitud
        InputStream fileContent = filePart.getInputStream();
         // Detecta el tipo MIME del archivo utilizando Apache Tika
        Tika tika = new Tika();
        String mimeType = tika.detect(fileContent);
        
        // Verifica que el archivo sea una imagen
        if (!mimeType.startsWith("image/")) {
            error="El archivo debe ser un archivo de imagen valido";
            request.setAttribute("errorImagen", error);
            
            getServletContext().getRequestDispatcher(vistaError).forward(request, response);
            return;
        }
        
        // Crea el directorio de subida si no existe
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        // Guarda el archivo en el servidor
        File file = new File(uploadPath + File.separator + fileName);
        try (InputStream input = fileContent) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        
        
        
        
        // Ponemos la url para guardar la url en la base de datos
        String fileUrl = request.getContextPath() + "/" + UPLOAD_DIR + "/" + fileName;
        
        
            ArmaFuego af=new ArmaFuego();
            
            af.setCalibre(calibre);
            af.setFecha_expedicion_guia(fecha_expedicion_guia);
            af.setGuia_validada(Boolean.FALSE);
            af.setId_arma(a);
            af.setNum_guia(num_guia);
            af.setNum_identificacion(num_identificacion);
            af.setUrl_img_guia_anverso(url_anverso);
            af.setUrl_img_guia_reverso(fileUrl);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
            LicenciaJpaController ljc= new LicenciaJpaController(emf);
            Licencia l=ljc.findLicencia(idLicencia);
            af.setId_licencia(l);            
            ArmaFuegoJpaController afjc=new ArmaFuegoJpaController(emf);
            
            
            
            try{
             afjc.create(af);
            
            }catch(Exception e){
                
            }
            
            request.getSession().removeAttribute("anversoguia");
             request.getSession().removeAttribute("num_guia");
              request.getSession().removeAttribute("calibre" );
             request.getSession().removeAttribute("fecha_expedicion_guia" );
             request.getSession().removeAttribute("num_identificacion");
            request.getSession().removeAttribute("licencia");
            
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
            Logger.getLogger(AnadirImagenGuiaReverso.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AnadirImagenGuiaReverso.class.getName()).log(Level.SEVERE, null, ex);
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
