
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 *
 * @author Zatonio
 */
@Entity
@Table(name = "mensaje")
@Cacheable(false)
@XmlRootElement
public class Mensaje implements Serializable {

    @Id
    @Column(name="id_mensaje")
    private Long id_mensaje;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario id_usuario;
    
   
    @ManyToOne
    @JoinColumn(name="id_hilo")
    private Usuario id_hilo;

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Column(name="contenido")
    private String contenido;
   
    
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_publicacion")
    private Date fecha_publicacion;

    public Long getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(Long id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public Usuario getId_hilo() {
        return id_hilo;
    }

    public void setId_hilo(Usuario id_hilo) {
        this.id_hilo = id_hilo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "id_mensaje=" + id_mensaje + ", id_usuario=" + id_usuario + ", id_hilo=" + id_hilo + ", contenido=" + contenido + ", fecha_publicacion=" + fecha_publicacion + '}';
    }

    

    
    
    
    

    
    
}
    
    