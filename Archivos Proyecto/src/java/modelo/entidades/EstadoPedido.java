
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "estado_pedido")
@Cacheable(false)
@XmlRootElement
public class EstadoPedido implements Serializable {

    public Long getId_estado_pedido() {
        return id_estado_pedido;
    }

    public void setId_estado_pedido(Long id_estado_pedido) {
        this.id_estado_pedido = id_estado_pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_estado_pedido")
    private Long id_estado_pedido;

    @Override
    public String toString() {
        return "EstadoPedido{" + "id_estado_pedido=" + id_estado_pedido + ", estado=" + estado + ", descripcion=" + descripcion + '}';
    }
    
    @Column(name="estado")
    private String estado;
    
    @Column(name="descripcion")
    private String descripcion;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
    

    
    
}
    
    