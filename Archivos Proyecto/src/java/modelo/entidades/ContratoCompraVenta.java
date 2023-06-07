
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
@Table(name = "contrato_compra_venta")
@Cacheable(false)
@XmlRootElement
public class ContratoCompraVenta implements Serializable {

    @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_contrato_compra_venta")
    private Long id_contrato_compra_venta;

    
    @OneToOne
    @JoinColumn(name="id_factura")
    private Factura id_factura;
    
    
    
    @Column(name="url_contrato_compra_venta")
    private String url_contrato_compra_venta;

    public Long getId_contrato_compra_venta() {
        return id_contrato_compra_venta;
    }

    public void setId_contrato_compra_venta(Long id_contrato_compra_venta) {
        this.id_contrato_compra_venta = id_contrato_compra_venta;
    }

    public Factura getId_factura() {
        return id_factura;
    }

    public void setId_factura(Factura id_factura) {
        this.id_factura = id_factura;
    }

    

    public String getUrl_contrato_compra_venta() {
        return url_contrato_compra_venta;
    }

    public void setUrl_contrato_compra_venta(String url_contrato_compra_venta) {
        this.url_contrato_compra_venta = url_contrato_compra_venta;
    }

    @Override
    public String toString() {
        return "ContratoCompraVenta{" + "id_contrato_compra_venta=" + id_contrato_compra_venta + ", id_factura=" + id_factura + ", url_contrato_compra_venta=" + url_contrato_compra_venta + '}';
    }

    
   
     

    
    
}
    
    