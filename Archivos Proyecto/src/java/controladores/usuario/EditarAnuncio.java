
package controladores.usuario;
/**
 *
 * @author Zatonio
 */
import controladores.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.dao.AnuncioJpaController;
import modelo.dao.ImagenJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Anuncio;
import modelo.entidades.Imagen;

import modelo.entidades.Usuario;
import org.apache.tika.Tika;

/**
 *
 * @author Zatonio
 */

@MultipartConfig(maxFileSize = 10000000, fileSizeThreshold = 10000000)  //Añadimos la configuracion Multipart 
@WebServlet(name = "EditarAnuncio", urlPatterns = {"/usuario/EditarAnuncio"}  )
public class EditarAnuncio extends HttpServlet {
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
            throws ServletException, IOException {
        String vista = "/usuario/editarAnuncio.jsp";           
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        Long idAnuncio=0L;
        if(request.getParameter("anuncioEditar")!=null){
             idAnuncio= Long.valueOf(request.getParameter("anuncioEditar"));
        }else{
             idAnuncio= (Long)(request.getSession().getAttribute("anuncioEditar"));
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");   
        AnuncioJpaController ajc=new AnuncioJpaController(emf);
        Anuncio anuncio=ajc.findAnuncio(idAnuncio);
        request.getSession().setAttribute("anuncioEditar",idAnuncio);
        request.setAttribute("anuncio",anuncio);
        String error="";
         String vistaError="../error.jsp";
        
        
        if (request.getParameter("titulo ") != null || request.getParameter("descripcion") != null || request.getParameter("precio") != null) { //Si se recoge alguno de los tres parametros editables  es que ya ha estado en la vista y a pulsado en editar  en el fomrulario 
            // Editando
            
            String descripcion=request.getParameter("descripcion");
            String titulo= request.getParameter("titulo");
            Double precio= Double.parseDouble(request.getParameter("precio"));
            
            
            anuncio.setDescripcion(descripcion);
            anuncio.setTitulo(titulo);
            anuncio.setPrecio(precio);
            
            try{
                ajc.edit(anuncio);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
            
            if(request.getPart("images")!=null && request.getPart("images").getSize() > 0){
            
             ImagenJpaController ijc=new ImagenJpaController(emf);
             
             List<Imagen> imagenes=ijc.findImagenEntities();
             List<Imagen> imagenesAnuncio=new ArrayList<>();
             
             
             
             for(Imagen imagen : imagenes ){
                 if(imagen.getId_anuncio().getId_anuncio().equals(idAnuncio)){
                     imagenesAnuncio.add(imagen);
                 }           
             }
             
             for (Imagen imagen:imagenesAnuncio){
                 try{
                     ijc.destroy(imagen.getId_imagen());
                 }catch(Exception e){
                     System.out.println(e.getMessage());
                 }
             }
             
             
             List<Part> fileParts = request.getParts().stream()
            .filter(part -> "images".equals(part.getName()))
            .collect(Collectors.toList());
             for (Part filePart : fileParts){
        // Obtiene el nombre del archivo del archivo de la solicitud
        
        
        String filePath = Paths.get(filePart.getSubmittedFileName()).toString();
        String fileType = filePath.substring(filePath.lastIndexOf('.') + 1);
        

        String fileName = "anuncio"+usuario.getNickname() + anuncio.getId_anuncio()+fileParts.indexOf(filePart) + "." + fileType;
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
        i.setId_anuncio(anuncio);
        i.setUrl_imagen(fileUrl);
        
               
        try{
            ijc.create(i);
        }catch(Exception e){
            
        }
        imagenes.add(i);
            }
                
                
            }
            
            response.sendRedirect("./MisAnuncios");
           

            }
         else { //Si hay algun error editando dejamos el anuncio como estaba
            
            request.setAttribute("anuncio", anuncio);
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
