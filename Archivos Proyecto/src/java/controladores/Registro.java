/*
 * Servlet Controlador Registro.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.MunicipioJpaController;
import modelo.dao.ProvinciaJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Municipio;
import modelo.entidades.Provincia;
import modelo.entidades.Usuario;

/**
 *
 * @author Zatonio
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        String vista = "/registro.jsp";
        
        String mensajeNif="";
        String mensajeEmail="";
        String mensajeNick="";
        String mensajeTelefono="";
        String error = "";
        String nombre = "";
        String apellidos = "";
        String email = "";
        String direccion = "";
        String telefono = "";
        String nickname = "";
        Date fecha_nacimiento = null;
        String nif = "";
        String rol = "normal";
        String contraseña = "";
        Long id_municipio=0L;
        String nombre_padre="";
        
        String nombre_madre="";
        String municipion="";
        Long provinciasn=0L;
        
        if (request.getParameter("nombre") != null 
                && request.getParameter("apellidos") != null
                && request.getParameter("apellidos") != null
                && request.getParameter("email") != null
                && request.getParameter("direccion") != null
                && request.getParameter("telefono") != null
                && request.getParameter("nickname") != null
                && request.getParameter("nif") != null
                && request.getParameter("contraseña") != null
                && request.getParameter("contraseña_rep") != null
                && request.getParameter("privacidad") != null
                && request.getParameter("municipios") != null
                && request.getParameter("fecha_nacimiento") !=null
                && request.getParameter("municipiosn") !=null
                && request.getParameter("provinciasn") !=null
                && request.getParameter("nombre_padre") !=null
                && request.getParameter("nombre_madre") !=null
                ) { //Si los campos no son null 

                
                 
            
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
            contraseña = request.getParameter("contraseña");
            request.setAttribute("contrasaeña", contraseña);
            id_municipio=Long.valueOf(request.getParameter("municipios"));            
            request.setAttribute("municipios",(id_municipio));            
            nombre_madre=request.getParameter("nombre_madre");
            nombre_padre=request.getParameter("nombre_padre");
            municipion=request.getParameter("municipiosn");
            provinciasn=Long.valueOf(request.getParameter("provinciasn"));
            
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecondWeaponLife");
               
                
                
            
            
            
            
            
            
            
            //Encriptamos la contraseña con sha256
            try {
                // Crea una instancia del algoritmo de hash SHA-256
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                // Convierte el password a bytes y calcula el hash
                byte[] hashedPassword = md.digest(contraseña.getBytes());

                // Convierte el hash a una cadena hexadecimal
                StringBuilder sb = new StringBuilder();
                for (byte b : hashedPassword) {
                    sb.append(String.format("%02x", b));
                }
                String hashedPasswordHex = sb.toString();

                // hashedPasswordHex contiene el hash en formato hexadecimal
                
                contraseña=hashedPasswordHex;
                
            } catch (NoSuchAlgorithmException e) {
                // Manejo de errores
                e.printStackTrace();
            }

            
            if (fecha_nacimiento == null) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // Cambiamos el formato de la fecha

                try {
                    fecha_nacimiento = format.parse(request.getParameter("fecha_nacimiento")); //
                } catch (ParseException ex) {
                    Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (nombre.isEmpty()) { //Comprobamos y mostramos mensajes
                error = "El nombre no puede estar vacío";
            } else if (apellidos.isEmpty()) {
                error = "El campo apellidos no puede estar vacío";
            } else if (email.isEmpty()) {
                error = "El email no puede estar vacío";
            } else if (direccion.isEmpty()) {
                error = "La direccion no puede estar vacía";
            } else if (telefono.isEmpty()) { //Si no hay error creamos un nuevo usuario
                error = "El telefono no puede estar vacío";
            } else if (nif.isEmpty()) {
                error = "El nif no puede estar vacio";
                //TODO:ACABAR LOS ELSE IF CON TODOS LOS CAMPOS
            } else {
                MunicipioJpaController mjc=new MunicipioJpaController(emf);
                ProvinciaJpaController pjc=new ProvinciaJpaController(emf);
                
                Usuario u = new Usuario();
                
                u.setNombre(nombre);
                u.setApellidos(apellidos);
                u.setEmail(email);
                u.setEsta_activo(Boolean.TRUE);
                u.setFecha_nacimiento(fecha_nacimiento);
                u.setNif(nif);
                u.setRol(rol);
                u.setTelefono(telefono);
                u.setNickname(nickname);
                u.setContraseña(contraseña);
                u.setDireccion(direccion);
                u.setNombre_madre(nombre_madre);
                u.setNombre_padre(nombre_padre);
                u.setNacido_en(municipion);
                Provincia p=pjc.findProvincia(provinciasn);
                u.setProvincia_nacimiento(p.getNombre());
                u.setId_municipio(mjc.findMunicipio(id_municipio));
                u.setUrl_img_perfil(null);
                u.setUrl_img_dni_anverso(null);
                u.setUrl_img_dni_reverso(null);
                u.setDni_validado(Boolean.FALSE);
               
                UsuarioJpaController ujc = new UsuarioJpaController(emf); //Llamamos controlador JPA USUARIO
                try { //Persistimos los datos en la base de datos
                    ujc.create(u);
                    response.sendRedirect("./Login"); // 
                    return;
                } catch (RollbackException e) {
                    System.out.println(e);
                    error = "";
                }

            }
        
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
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
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
