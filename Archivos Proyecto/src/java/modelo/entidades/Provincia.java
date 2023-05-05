
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
@Table(name = "provincia")
@Cacheable(false)
@XmlRootElement
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_provincia")
    private Long id_provincia;
    
    @Column(name="nombre", unique = true)
    private String nombre;
    
    @OneToMany(mappedBy = "provincia")
    private List<Municipio> municipios;

    public Long getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(Long id_provincia) {
        this.id_provincia = id_provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public String toString() {
        return "Provincia{" + "id_provincia=" + id_provincia + ", nombre=" + nombre + ", municipios=" + municipios + '}';
    }
    

    
    
}
    
    