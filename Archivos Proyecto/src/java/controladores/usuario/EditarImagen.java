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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Usuario;
import org.apache.tika.Tika;
/**
 *
 * @author Zatonio
 */
@WebServlet(name = "EditarImagen", urlPatterns = {"/usuario/EditarImagen"})
@MultipartConfig(maxFileSize = 10000000, fileSizeThreshold = 10000000)  //Añadimos la configuracion Multipart 
public class EditarImagen extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";
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
     String vista = "/usuario/errorCambiandoImagen.jsp";
     String error="";   
        // Obtiene el archivo de la solicitud
        Part filePart = request.getPart("file");
        
        // Obtiene el nombre del archivo del archivo de la solicitud
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // Obtiene el stream de entrada del archivo de la solicitud
        InputStream fileContent = filePart.getInputStream();
        
        // Detecta el tipo MIME del archivo utilizando Apache Tika
        Tika tika = new Tika();
        String mimeType = tika.detect(fileContent);
        
        // Verifica que el archivo sea una imagen
        if (!mimeType.startsWith("image/")) {
            error="El archivo debe ser un archivo de imagen valido";
            request.setAttribute("errorImagen", error);
            
            getServletContext().getRequestDispatcher(vista).forward(request, response);
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
        
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
        UsuarioJpaController ujc= new UsuarioJpaController(emf);
        
        usuario.setUrl_img_perfil(fileUrl);
        
        
        try {
                    ujc.edit(usuario);
                    
                    
                    response.sendRedirect("./EditarPerfil");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando la imagen de perfil");
                    request.setAttribute("usuario", usuario);
                    getServletContext().getRequestDispatcher(vista).forward(request, response);
                    
            
                }
        
        
        // Redirige a la vista de edición de perfil
        
        response.sendRedirect("./EditarPerfil");
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
