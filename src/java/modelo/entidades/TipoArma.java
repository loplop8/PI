
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
@Table(name = "tipo_arma")
@Cacheable(false)
@XmlRootElement
public class TipoArma implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_tipo_arma")
    private Long id_tipo_arma;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="descripcion")
    private String descripcion;

    public Long getId_tipo_arma() {
        return id_tipo_arma;
    }

    public void setId_tipo_arma(Long id_tipo_arma) {
        this.id_tipo_arma = id_tipo_arma;
    }

   

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoArma{" + "id_tipo_arma=" + id_tipo_arma + ", tipo=" + tipo + ", descripcion=" + descripcion + '}';
    }

    
    

    
    
}
    
    