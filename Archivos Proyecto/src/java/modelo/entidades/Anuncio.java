
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
@Table(name = "anuncio")
@Cacheable(false)
@XmlRootElement
public class Anuncio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_anuncio")
    private Long id_anuncio;

    @OneToOne
    @JoinColumn(name="id_arma")
    private Arma id_arma;
    
    @Temporal(TemporalType.DATE)
    private Date fecha_public;
    
    
    
    
    
    @Column(name="descripcion")
    private String descripcion;
   
    
    @Column(name="titulo")
    private String titulo;
   
    
    @Column(name="precio")
    private Double precio;

    public Long getId_anuncio() {
        return id_anuncio;
    }

    public void setId_anuncio(Long id_anuncio) {
        this.id_anuncio = id_anuncio;
    }

    public Arma getId_arma() {
        return id_arma;
    }

    public void setId_arma(Arma id_arma) {
        this.id_arma = id_arma;
    }

    public Date getFecha_public() {
        return fecha_public;
    }

    public void setFecha_public(Date fecha_public) {
        this.fecha_public = fecha_public;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Anuncio{" + "id_anuncio=" + id_anuncio + ", id_arma=" + id_arma + ", fecha_public=" + fecha_public + ", descripcion=" + descripcion + ", titulo=" + titulo + ", precio=" + precio + '}';
    }
   
    
    
    

    
    
}
    
    