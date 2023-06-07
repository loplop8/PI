
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "factura")
@Cacheable(false)
@XmlRootElement
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_factura")
    private Long id_factura;

    @OneToOne
    @JoinColumn(name="id_pedido")
    private Pedido id_pedido;
    
    
    
    @Column(name="num_factura")
    private Long num_factura;

    @Column(name="descripcion_descuento")
    private String descripcion_descuento;
    
    @Column(name="importe_descuento")
    private Double importe_descuento;
     
    @Column(name="importe_bruto")
    private Double importe_bruto;
   
    @Column(name="importe_gastos")
    private Double importe_gastos;
   
    @Column(name="precio_total_factura")
    private Double precio_total_factura;
    
    @Column(name="base_imponible")
    private Double base_imponible;
   
    @Column(name="url_factura")
    private String url_factura;
    
    
    @Column(name="descripcion_gastos")
    private String descripcion_gastos;

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Pedido getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Pedido id_pedido) {
        this.id_pedido = id_pedido;
    }

    

    public Long getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(Long num_factura) {
        this.num_factura = num_factura;
    }

    public String getDescripcion_descuento() {
        return descripcion_descuento;
    }

    public void setDescripcion_descuento(String descripcion_descuento) {
        this.descripcion_descuento = descripcion_descuento;
    }

    public Double getImporte_descuento() {
        return importe_descuento;
    }

    public void setImporte_descuento(Double importe_descuento) {
        this.importe_descuento = importe_descuento;
    }

    public Double getImporte_bruto() {
        return importe_bruto;
    }

    public void setImporte_bruto(Double importe_bruto) {
        this.importe_bruto = importe_bruto;
    }

    public Double getImporte_gastos() {
        return importe_gastos;
    }

    public void setImporte_gastos(Double importe_gastos) {
        this.importe_gastos = importe_gastos;
    }

    public Double getPrecio_total_factura() {
        return precio_total_factura;
    }

    public void setPrecio_total_factura(Double precio_total_factura) {
        this.precio_total_factura = precio_total_factura;
    }

    public Double getBase_imponible() {
        return base_imponible;
    }

    public void setBase_imponible(Double base_imponible) {
        this.base_imponible = base_imponible;
    }

    public String getUrl_factura() {
        return url_factura;
    }

    public void setUrl_factura(String url_factura) {
        this.url_factura = url_factura;
    }

    public String getDescripcion_gastos() {
        return descripcion_gastos;
    }

    public void setDescripcion_gastos(String descripcion_gastos) {
        this.descripcion_gastos = descripcion_gastos;
    }

    @Override
    public String toString() {
        return "Factura{" + "id_factura=" + id_factura + ", id_pedido=" + id_pedido + ", num_factura=" + num_factura + ", descripcion_descuento=" + descripcion_descuento + ", importe_descuento=" + importe_descuento + ", importe_bruto=" + importe_bruto + ", importe_gastos=" + importe_gastos + ", precio_total_factura=" + precio_total_factura + ", base_imponible=" + base_imponible + ", url_factura=" + url_factura + ", descripcion_gastos=" + descripcion_gastos + '}';
    }

    
    
    
    
    
}
    
    