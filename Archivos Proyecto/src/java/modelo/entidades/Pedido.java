
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
@Table(name = "pedido")
@Cacheable(false)
@XmlRootElement
public class Pedido implements Serializable {

    @Id
    @Column(name="id_pedido")
    private Long id_pedido;

    @OneToOne
    @JoinColumn(name="id_arma")
    private Arma id_arma;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario id_usuario;
    
    @ManyToOne
    @JoinColumn(name="id_estado_pedido")
    private EstadoPedido id_estado_pedido;
    
    
    
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_entrega")
    private Date fecha_entrega;
    
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_compra")
    private Date fecha_compra;
    
    
    
    @Column(name="numero_pedido")
    private Long numero_pedido;

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Arma getId_arma() {
        return id_arma;
    }

    public void setId_arma(Arma id_arma) {
        this.id_arma = id_arma;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public EstadoPedido getId_estado_pedido() {
        return id_estado_pedido;
    }

    public void setId_estado_pedido(EstadoPedido id_estado_pedido) {
        this.id_estado_pedido = id_estado_pedido;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Long getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(Long numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + ", id_arma=" + id_arma + ", id_usuario=" + id_usuario + ", id_estado_pedido=" + id_estado_pedido + ", fecha_entrega=" + fecha_entrega + ", fecha_compra=" + fecha_compra + ", numero_pedido=" + numero_pedido + '}';
    }
    
    
    
    
    
    
   
    
    
    

    
    
}
    
    