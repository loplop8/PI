
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "notificacion")
@Cacheable(false)




public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_notificacion")
    private Long id_notificacion;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario id_usuario;
    
    
    
    @Column(name="mensaje")
    private String mensaje;

    public Long getId_notificacion() {
        return id_notificacion;
    }

    
    public void setId_notificacion(Long id_notificacion) {
        this.id_notificacion = id_notificacion;
    }

    
    public Usuario getId_usuario() {
        return id_usuario;
    }

    
    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Notificacion{" + "id_notificacion=" + id_notificacion + ", id_usuario=" + id_usuario + ", mensaje=" + mensaje + '}';
    }
    
    
    

   
    
    
    
    

    
}
