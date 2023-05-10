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
@WebServlet(name = "AnadirImagenReversoLicencia", urlPatterns = {"/usuario/AnadirImagenReversoLicencia"})
@MultipartConfig(maxFileSize = 10000000, fileSizeThreshold = 10000000)  //Añadimos la configuracion Multipart 
public class AnadirImagenReversoLicencia extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads/licencia";
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

            String vista = "/usuario/anadirImagenReversoLicencia.jsp";




                   Date valida_desde=(Date)request.getSession().getAttribute("valida_desde");
               request.getSession().setAttribute("valida_desde",valida_desde);
               Date valida_hasta=(Date)request.getSession().getAttribute("valida_hasta");
               request.getSession().setAttribute("valida_hasta",valida_hasta);
               Date fecha_expedicion=(Date)request.getSession().getAttribute("fecha_expedicion");
               request.getSession().setAttribute("fecha_expedicion",fecha_expedicion);
               String restricciones =(String)request.getSession().getAttribute("restricciones");
               request.getSession().setAttribute("restricciones",restricciones);
               String tipo_licencia=(String)request.getSession().getAttribute("tipo_licencia");   
               request.getSession().setAttribute("tipo_licencia",tipo_licencia);
               String url_img_licencia_anverso=(String)request.getSession().getAttribute("anversoLicencia");
               request.getSession().setAttribute("anversoLicencia",url_img_licencia_anverso);
               String observaciones =(String)request.getSession().getAttribute("observaciones");
               request.getSession().setAttribute("observaciones",observaciones);

               Usuario usuario=(Usuario)request.getSession().getAttribute("usuario");



         String error="";

        if(request.getPart("file2")!=null){ 

         String controldorAnadirImagenLicencia="/usuario/AnadirImagenLicencia";
         String vistaError = "/usuario/errorCambiandoImagen.jsp";


                // Obtiene el archivo de la solicitud
            Part filePart = request.getPart("file2");

            // Obtiene el nombre del archivo del archivo de la solicitud
                  String fileName = "Reverso"+usuario.getNickname()+usuario.getId_usuario()+Paths.get(filePart.getSubmittedFileName()).getFileName().toString();


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


            request.getSession().setAttribute("reversoLicencia", fileUrl);


            getServletContext().getRequestDispatcher(controldorAnadirImagenLicencia).forward(request, response);
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
