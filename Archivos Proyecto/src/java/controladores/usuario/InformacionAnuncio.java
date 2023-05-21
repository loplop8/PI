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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import modelo.dao.AnuncioJpaController;
import modelo.dao.ArmaFuegoJpaController;
import modelo.dao.ImagenJpaController;
import modelo.dao.LicenciaJpaController;
import modelo.dao.TipoLicenciaJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.Arma;
import modelo.entidades.ArmaFuego;
import modelo.entidades.Imagen;
import modelo.entidades.Licencia;
import modelo.entidades.TipoLicencia;
import modelo.entidades.Usuario;
import org.apache.tika.Tika;

/**
 *
 * @author Zatonio
 */

@MultipartConfig(maxFileSize = 100000000, fileSizeThreshold = 100000000)  //Añadimos la configuracion Multipart, como maximo 100 MB 
@WebServlet(name = "InformacionAnuncio", urlPatterns = {"/usuario/InformacionAnuncio"})
public class InformacionAnuncio extends HttpServlet {
    private static final String UPLOAD_DIR = "/img/uploads/anuncio";
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
        String vista="/usuario/anadirImagenReversoGuia.jsp";
        String vistaError="../error.jsp";
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        
           
            Arma a= (Arma) request.getSession().getAttribute("arma");
            
            if(request.getPart("images")!=null && request.getParameter("titulo")!=null && request.getParameter("descripcion")!=null && request.getParameter("precio")!=null ) { 
        
                String descipcion=request.getParameter("descripcion");
                String titulo=request.getParameter("titulo");
                Double precio=Double.parseDouble(request.getParameter("precio"));
                
                Anuncio an=new Anuncio();
                an.setId_arma(a);
                an.setDescripcion(error);
                an.setTitulo(titulo);
                an.setPrecio(precio);
                Date d=new Date();
                an.setFecha_public(d);
                
                EntityManagerFactory emf=Persistence.createEntityManagerFactory("SecondWeaponLife");
                AnuncioJpaController ajc=new AnuncioJpaController(emf);
                try{
                    ajc.create(an);
                }catch(Exception e){
                    
                }
                
                
                List<Part> fileParts = request.getParts().stream()
            .filter(part -> "images".equals(part.getName()))
            .collect(Collectors.toList());

            for (Part filePart : fileParts){
        // Obtiene el nombre del archivo del archivo de la solicitud
        
        
        String filePath = Paths.get(filePart.getSubmittedFileName()).toString();
        String fileType = filePath.substring(filePath.lastIndexOf('.') + 1);
        

        String fileName = "anuncio"+usuario.getNickname() + an.getId_anuncio()+fileParts.indexOf(filePart) + "." + fileType;
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
            
        //Insertamos las imagenes en la tabla de imagenes
        
        Imagen i=new Imagen();
        i.setId_anuncio(an);
        i.setUrl_imagen(fileUrl);
        
                ImagenJpaController ijc =new ImagenJpaController(emf);
        try{
            ijc.create(i);
        }catch(Exception e){
            
        }
        
            }
        
            
            
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
