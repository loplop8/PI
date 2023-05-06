
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zatonio
 */
@Entity
@Table(name = "imagen")
@Cacheable(false)
@XmlRootElement





public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_imagen")
    private Long id_imagen;
   
    @ManyToOne 
    @JoinColumn(name="id_anuncio")    
    private Anuncio id_anuncio;

            
    @Column(name="url_imagen")
    private String url_imagen;

    public Long getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(Long id_imagen) {
        this.id_imagen = id_imagen;
    }

    public Anuncio getId_anuncio() {
        return id_anuncio;
    }

    public void setId_anuncio(Anuncio id_anuncio) {
        this.id_anuncio = id_anuncio;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    @Override
    public String toString() {
        return "Imagen{" + "id_imagen=" + id_imagen + ", id_anuncio=" + id_anuncio + ", url_imagen=" + url_imagen + '}';
    }

    
    
    
    
  

    
}
    
    