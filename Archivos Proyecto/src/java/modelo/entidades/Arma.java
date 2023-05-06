
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
@Table(name = "arma")
@Cacheable(false)
@XmlRootElement
public class Arma implements Serializable {

    @Id
    @Column(name="id_arma")
    private Long id_arma;

    public Long getId_arma() {
        return id_arma;
    }

    public void setId_arma(Long id_arma) {
        this.id_arma = id_arma;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    

    public TipoArma getId_tipo_arma() {
        return id_tipo_arma;
    }

    public void setId_tipo_arma(TipoArma id_tipo_arma) {
        this.id_tipo_arma = id_tipo_arma;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario id_usuario;
    
    @ManyToOne
    @JoinColumn(name="id_tipo_arma")
    private TipoArma id_tipo_arma;

    
    @Column(name="marca")
    private String marca;
   
    @Override
    public String toString() {
        return "Arma{" + "id_arma=" + id_arma + ", id_usuario=" + id_usuario + ", id_tipo_arma=" + id_tipo_arma + ", marca=" + marca + '}';
    }
    
    

    
    
}
    
    