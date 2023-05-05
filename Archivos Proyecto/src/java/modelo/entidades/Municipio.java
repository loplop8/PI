
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zatonio
 */
@Entity
@Table(name = "municipio")
@Cacheable(false)
@XmlRootElement

@NamedQueries({
    
    @NamedQuery(name = "Municipio.findMunicipiosPorProvincia", query = "SELECT m FROM Municipio m WHERE m.provincia.id_provincia = :idProvincia")
        
})



public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_municipio")
    private Long id_municipio;
   
    @ManyToOne 
    @JoinColumn(name="id_provincia")    
    private Provincia provincia;

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
            
    @Column(name="nombre")
    private String nombre;

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Long id_municipio) {
        this.id_municipio = id_municipio;
    }

    @Override
    public String toString() {
        return "Municipio{" + "id_municipio=" + id_municipio + ", provincia=" + provincia + ", nombre=" + nombre + '}';
    }

  

    
}
    
    