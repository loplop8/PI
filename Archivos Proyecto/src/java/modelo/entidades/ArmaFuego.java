
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
@Table(name = "arma_fuego")
@Cacheable(false)
@XmlRootElement
public class ArmaFuego implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="id_arma")
    private Arma id_arma;

    public Arma getId_arma() {
        return id_arma;
    }

    public void setId_arma(Arma id_arma) {
        this.id_arma = id_arma;
    }
    
    @ManyToOne
    @JoinColumn(name="id_licencia")
    private Licencia id_licencia;
    
    
    @Column(name="num_guia")
    private String num_guia;

    
    @Column(name="calibre")
    private String calibre;
    
    @Column(name="num_identificacion")
    private String num_identificacion;
    
    
    @Temporal(TemporalType.DATE)
    private Date fecha_expedicion_guia;
    
    @Column(name="guia_validada")
    private Boolean guia_validada;
    
    @Column(name="url_img_guia_reverso")
    private String url_img_guia_reverso;
    
    @Column(name="url_img_guia_anverso")
    private String url_img_guia_anverso;

    public String getUrl_img_guia_reverso() {
        return url_img_guia_reverso;
    }

    public void setUrl_img_guia_reverso(String url_img_guia_reverso) {
        this.url_img_guia_reverso = url_img_guia_reverso;
    }

    public String getUrl_img_guia_anverso() {
        return url_img_guia_anverso;
    }

    public void setUrl_img_guia_anverso(String url_img_guia_anverso) {
        this.url_img_guia_anverso = url_img_guia_anverso;
    }
    
    

    public Licencia getId_licencia() {
        return id_licencia;
    }

    public void setId_licencia(Licencia id_licencia) {
        this.id_licencia = id_licencia;
    }

    public String getNum_guia() {
        return num_guia;
    }

    public void setNum_guia(String num_guia) {
        this.num_guia = num_guia;
    }

    public String getCalibre() {
        return calibre;
    }

    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    public String getNum_identificacion() {
        return num_identificacion;
    }

    public void setNum_identificacion(String num_identificacion) {
        this.num_identificacion = num_identificacion;
    }

    public Date getFecha_expedicion_guia() {
        return fecha_expedicion_guia;
    }

    public void setFecha_expedicion_guia(Date fecha_expedicion_guia) {
        this.fecha_expedicion_guia = fecha_expedicion_guia;
    }

    public Boolean getGuia_validada() {
        return guia_validada;
    }

    public void setGuia_validada(Boolean guia_validada) {
        this.guia_validada = guia_validada;
    }

    @Override
    public String toString() {
        return "ArmaFuego{" + "id_arma=" + id_arma + ", id_licencia=" + id_licencia + ", num_guia=" + num_guia + ", calibre=" + calibre + ", num_identificacion=" + num_identificacion + ", fecha_expedicion_guia=" + fecha_expedicion_guia + ", guia_validada=" + guia_validada + ", url_img_guia_reverso=" + url_img_guia_reverso + ", url_img_guia_anverso=" + url_img_guia_anverso + '}';
    }

    
    
    
    
    

    
    
}
    
    