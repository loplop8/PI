
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Zatonio
 */
@Entity
@Table(name = "usuario")
@Cacheable(false)




public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_usuario")
    private Long id_usuario;
    
    @Column(name="nickname", unique = true)
    private String nickname;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellidos")
    private String apellidos;
    
    @Column(name="email", unique= true)
    private String email;
    
    @Column(name="contraseña")
    private String contraseña;
    
    @Column(name="rol")
    private String rol;

    
    
    @Column(name="telefono")
    private String telefono;
    
    @ManyToOne
    @JoinColumn(name="id_municipio")
    private Municipio id_municipio;

    public Municipio getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Municipio id_municipio) {
        this.id_municipio = id_municipio;
    }

    
    
    
    

    

    
    
    
    @Column(name="direccion")
    private String direccion;
    
    @Column(name="fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;
    
    @Column(name="nif")
    private String nif;
    
    @Column(name="url_img_perfil")
    private String url_img_perfil;
    
    @Column(name="esta_activo")
    private Boolean esta_activo;

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getUrl_img_perfil() {
        return url_img_perfil;
    }

    public void setUrl_img_perfil(String url_img_perfil) {
        this.url_img_perfil = url_img_perfil;
    }

    public Boolean getEsta_activo() {
        return esta_activo;
    }

    public void setEsta_activo(Boolean esta_activo) {
        this.esta_activo = esta_activo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nickname=" + nickname + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email +  ", telefono=" + telefono + ", direccion=" + direccion + ", fecha_nacimiento=" + fecha_nacimiento + ", nif=" + nif + ", url_img_perfil=" + url_img_perfil + ", esta_activo=" + esta_activo + '}';
    }
    
    
    
    

    
}
