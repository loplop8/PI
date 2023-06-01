
package controladores.admin;
/**
 *
 * @author Zatonio
 */
import controladores.usuario.*;
import controladores.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.MunicipioJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Municipio;

import modelo.entidades.Usuario;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "EditarPerfilUsuario", urlPatterns = {"/admin/EditarPerfilUsuario"}  )
public class EditarPerfilUsuario extends HttpServlet {
    
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
            throws ServletException, IOException, ParseException {
        String error = "";
        String nombre = "";
        String apellidos = "";
        String email = "";
        String direccion = "";
        String telefono = "";
        String nickname = "";
        Date fecha_nacimiento = null;
        String nif = "";
        String rol = "";
        String contraseña = "";
        Long id_municipio=0L;
        
        
        String vista = "/admin/editarPerfilUsuario.jsp";           
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife"); 
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        MunicipioJpaController mjc=new MunicipioJpaController(emf);
        
        Long id = Long.valueOf(request.getParameter("usuarioEditar"));
        
        Usuario editar = ujc.findUsuario(id);
        Municipio municipio=mjc.findMunicipio(id_municipio);
        
        request.setAttribute( "usuarioEditar", editar);
        
        request.getParameter("id_usuario");
        
        Date fechaUsuarioEditar=editar.getFecha_nacimiento();
        // Crear un objeto SimpleDateFormat para el formato actual de la fecha
        SimpleDateFormat formatoNuevo = new SimpleDateFormat("yyyy-MM-dd");

        // Convertir la fecha al formato del input 
        SimpleDateFormat formatoActual = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formatoNuevo.format(formatoActual.parse(formatoActual.format(fechaUsuarioEditar)));
        
        request.setAttribute("fecha", fechaFormateada);
        
        if (request.getParameter("nombre") != null 
                && request.getParameter("apellidos") != null
                && request.getParameter("apellidos") != null
                && request.getParameter("email") != null
                && request.getParameter("direccion") != null
                && request.getParameter("telefono") != null
                && request.getParameter("nickname") != null
                && request.getParameter("nif") != null
                && request.getParameter("municipios") != null
                && request.getParameter("rol") != null
                
                ) { //Si se recoge alguno de los tres parametros editables  es que ya ha estado en la vista y a pulsado en editar  en el fomrulario 
            // Editando
            
            
            nombre = request.getParameter("nombre");
            request.setAttribute("nombre",nombre);
            apellidos = request.getParameter("apellidos");
            request.setAttribute("apellidos",apellidos);
            email = request.getParameter("email");
            email.trim();
            request.setAttribute("email",email);
            direccion = request.getParameter("direccion");
            direccion.trim();
            request.setAttribute("direccion", direccion);
            telefono = request.getParameter("telefono");
            telefono.trim();
            request.setAttribute("telefono",telefono);
            nickname = request.getParameter("nickname");
            nickname = nickname.trim();
            request.setAttribute("nickname", nickname);
            nif = request.getParameter("nif");
            request.setAttribute("nif", nif);
            id_municipio=Long.valueOf(request.getParameter("municipios"));            
            request.setAttribute("municipios",(id_municipio));
            rol=request.getParameter("rol");
            request.setAttribute("rol", rol);
            
            if (fecha_nacimiento == null) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // Cambiamos el formato de la fecha

                try {
                    fecha_nacimiento = format.parse(request.getParameter("fecha_nacimiento")); //
                } catch (ParseException ex) {
                    Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (email.isEmpty()) {
                error = "El email no puede estar vacio";
            } else if (nickname.isEmpty()) {
                error = "El nombre de usuario no puede estar vacío";
            } else if (telefono.isEmpty()) {
                error = "El nickname no puede estar vacío";
            } else { 
                try {
                    editar.setNombre(nombre);
                    editar.setApellidos(apellidos);
                    editar.setDireccion(direccion);
                    editar.setEmail(email);
                    editar.setNif(nif);
                    editar.setId_municipio(mjc.findMunicipio(id_municipio));
                    editar.setNickname(nickname);
                    editar.setFecha_nacimiento(fecha_nacimiento);
                    editar.setRol(rol);
                    editar.setTelefono(telefono);
                    
                    ujc.edit(editar);
                    
                    response.sendRedirect("./AdministrarUsuarios");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando el usuario");
                    request.setAttribute("usuario", usuario);
                    request.setAttribute( "usuarioEditar", editar);
                }
            }

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.setAttribute("usuario", usuario);
                request.setAttribute( "usuarioEditar", editar);
        
            }
        } else { //Si hay algun error editando dejamos el usuario como estaba
            
            request.setAttribute("usuario", usuario);
            request.setAttribute( "usuarioEditar", editar);
        
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
        } catch (ParseException ex) {
            Logger.getLogger(EditarPerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(EditarPerfilUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
