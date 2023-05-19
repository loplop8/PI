
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
@Table(name = "licencia")
@Cacheable(false)
@XmlRootElement
public class Licencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_licencia")
    private Long id_licencia;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario id_usuario;
    
    @ManyToOne
    @JoinColumn(name="id_tipo_licencia")
    private TipoLicencia id_tipo_licencia;

    @Temporal(TemporalType.DATE)
    @Column(name="valida_hasta")
    private Date valida_hasta;
    
    @Temporal(TemporalType.DATE)
    @Column(name="valida_desde")
    private Date valida_desde;
    
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_expedicion")
    private Date fecha_expedicion;
    
    @Column(name="observaciones")
    private String observaciones;
    
    @Column(name="restricciones")
    private String restricciones;
    
    @Column(name="licencia_validada")
    private Boolean licencia_validada;

    @Column(name="url_img_licencia_anverso")
    private String url_img_licencia_anverso;
    
    @Column(name="url_img_licencia_reverso")
    private String url_img_licencia_reverso;
    
    
    
    public Long getId_licencia() {
        return id_licencia;
    }

    public void setId_licencia(Long id_licencia) {
        this.id_licencia = id_licencia;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public TipoLicencia getId_tipo_licencia() {
        return id_tipo_licencia;
    }

    public void setId_tipo_licencia(TipoLicencia id_tipo_licencia) {
        this.id_tipo_licencia = id_tipo_licencia;
    }

    public Date getValida_hasta() {
        return valida_hasta;
    }

    public void setValida_hasta(Date valida_hasta) {
        this.valida_hasta = valida_hasta;
    }

    public Date getValida_desde() {
        return valida_desde;
    }

    public void setValida_desde(Date valida_desde) {
        this.valida_desde = valida_desde;
    }

    public Date getFechaExpedicion() {
        return fecha_expedicion;
    }

    public void setFechaExpedicion(Date restricciones) {
        this.fecha_expedicion = restricciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getLicencia_validada() {
        return licencia_validada;
    }

    public void setLicencia_validada(Boolean licencia_validada) {
        this.licencia_validada = licencia_validada;
    }

    public Date getFecha_expedicion() {
        return fecha_expedicion;
    }

    public void setFecha_expedicion(Date fecha_expedicion) {
        this.fecha_expedicion = fecha_expedicion;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public String getUrl_img_licencia_anverso() {
        return url_img_licencia_anverso;
    }

    public void setUrl_img_licencia_anverso(String url_img_licencia_anverso) {
        this.url_img_licencia_anverso = url_img_licencia_anverso;
    }

    public String getUrl_img_licencia_reverso() {
        return url_img_licencia_reverso;
    }

    public void setUrl_img_licencia_reverso(String url_img_licencia_reverso) {
        this.url_img_licencia_reverso = url_img_licencia_reverso;
    }

    @Override
    public String toString() {
        return "Licencia{" + "id_licencia=" + id_licencia + ", id_usuario=" + id_usuario + ", id_tipo_licencia=" + id_tipo_licencia + ", valida_hasta=" + valida_hasta + ", valida_desde=" + valida_desde + ", fecha_expedicion=" + fecha_expedicion + ", observaciones=" + observaciones + ", restricciones=" + restricciones + ", licencia_validada=" + licencia_validada + ", url_img_licencia_anverso=" + url_img_licencia_anverso + ", url_img_licencia_reverso=" + url_img_licencia_reverso + '}';
    }
    

    
    
    

    
    
    
    
    
    
    

    
    
}
    
    