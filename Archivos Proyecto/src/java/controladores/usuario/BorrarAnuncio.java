
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Anuncio;
import modelo.entidades.Imagen;

import modelo.entidades.Usuario;
import org.apache.tika.Tika;

/**
 *
 * @author Zatonio
 */


@WebServlet(name = "BorrarAnuncio", urlPatterns = {"/usuario/BorrarAnuncio"}  )
public class BorrarAnuncio extends HttpServlet {
  
    
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
            throws ServletException, IOException, NonexistentEntityException {
        String vista = "/usuario/editarAnuncio.jsp";           
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        Long idAnuncio=0L;
       idAnuncio= Long.valueOf(request.getParameter("anuncioBorrar"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");   
        AnuncioJpaController ajc=new AnuncioJpaController(emf);
        Anuncio anuncio=ajc.findAnuncio(idAnuncio);
        ImagenJpaController ijc=new ImagenJpaController(emf);
        List<Imagen> imagenes=ijc.findImagenEntities();
        List<Imagen> imagenesAnuncio=new ArrayList<>();
        
        //Borramos las imagenes asociadas al anuncio y despues eliminamos el anuncio 
        for(Imagen imagen: imagenes){
            if(imagen.getId_anuncio().getId_anuncio().equals(idAnuncio)){
                imagenesAnuncio.add(imagen);
            }
        }
        for(Imagen imagen: imagenesAnuncio){
            ijc.destroy(imagen.getId_imagen());
        }
        ajc.destroy(idAnuncio);
        response.sendRedirect("./MisAnuncios");    
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
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BorrarAnuncio.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BorrarAnuncio.class.getName()).log(Level.SEVERE, null, ex);
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
