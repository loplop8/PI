
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
@Table(name = "tipo_licencia")
@Cacheable(false)
@XmlRootElement
public class TipoLicencia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_tipo_licencia")
    private Long id_tipo_licencia;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="descripcion")
    private String descripcion;

    public Long getId_tipo_licencia() {
        return id_tipo_licencia;
    }

    public void setId_tipo_licencia(Long id_tipo_licencia) {
        this.id_tipo_licencia = id_tipo_licencia;
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
        return "TipoLicencia{" + "id_tipo_licencia=" + id_tipo_licencia + ", tipo=" + tipo + ", descripcion=" + descripcion + '}';
    }
    
    

    
    
}
    
    