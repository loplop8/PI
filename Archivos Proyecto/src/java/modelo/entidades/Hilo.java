
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
@Table(name = "hilo")
@Cacheable(false)
@XmlRootElement
public class Hilo implements Serializable {

    @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_hilo")
    private Long id_hilo;

   @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario id_usuario;
    

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Column(name="titulo_descriptivo")
        private String titutlo_descriptivo;
   
    
    @Column(name="etiqueta_tema")
    private String etiqueta_tema;
   
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_creacion")
    private Date fecha_creacion;

    public Long getId_hilo() {
        return id_hilo;
    }

    public void setId_hilo(Long id_hilo) {
        this.id_hilo = id_hilo;
    }

    public String getTitutlo_descriptivo() {
        return titutlo_descriptivo;
    }

    public void setTitutlo_descriptivo(String titutlo_descriptivo) {
        this.titutlo_descriptivo = titutlo_descriptivo;
    }

    public String getEtiqueta_tema() {
        return etiqueta_tema;
    }

    public void setEtiqueta_tema(String etiqueta_tema) {
        this.etiqueta_tema = etiqueta_tema;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        return "Hilo{" + "id_hilo=" + id_hilo + ", id_usuario=" + id_usuario + ", titutlo_descriptivo=" + titutlo_descriptivo + ", etiqueta_tema=" + etiqueta_tema + ", fecha_creacion=" + fecha_creacion + '}';
    }
    
    
    
    
    

    
    
}
    
    