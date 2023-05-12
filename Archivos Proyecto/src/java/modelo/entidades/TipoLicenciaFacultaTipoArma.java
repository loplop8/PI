
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author Zatonio
 */

@Entity
@Table(name = "tipo_licencia_faculta_tipo_arma")
@IdClass(TipoLicenciaFacultaTipoArmaId.class)
public class TipoLicenciaFacultaTipoArma implements Serializable {
    
    @Id
    @Column(name = "id_tipo_licencia")
    private Long idTipoLicencia;
    
    @Id
    @Column(name = "id_tipo_arma")
    private Long idTipoArma;
    
    // Relación con la tabla de TipoLicencia (clave externa)
    @ManyToOne
    @JoinColumn(name = "id_tipo_licencia", insertable = false, updatable = false)
    private TipoLicencia tipoLicencia;
    
    // Relación con la tabla de TipoArma (clave externa)
    @ManyToOne
    @JoinColumn(name = "id_tipo_arma", insertable = false, updatable = false)
    private TipoArma tipoArma;
    
    // Resto de atributos de la tabla
    
    // Getters y setters
    
    public Long getIdTipoLicencia() {
        return idTipoLicencia;
    }
    
    public void setIdTipoLicencia(Long idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }
    
    public Long getIdTipoArma() {
        return idTipoArma;
    }
    
    public void setIdTipoArma(Long idTipoArma) {
        this.idTipoArma = idTipoArma;
    }
    
    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }
    
    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }
    
    public TipoArma getTipoArma() {
        return tipoArma;
    }
    
    public void setTipoArma(TipoArma tipoArma) {
        this.tipoArma = tipoArma;
    }
    
    // Otros métodos y lógica de negocio
    
    @Override
    public String toString() {
        return "TipoLicenciaFacultaTipoArma [idTipoLicencia=" + idTipoLicencia + ", idTipoArma=" + idTipoArma + "]";
    }
}