
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author Zatonio
 */

@Entity
@Table(name = "tipo_licencia_faculta_tipo_arma")
public class TipoLicenciaFacultaTipoArma implements Serializable {

    @EmbeddedId
    private TipoLicenciaFacultaTipoArmaId id;

    @ManyToOne
    @MapsId("idTipoLicencia")
    private TipoLicencia tipoLicencia;

    @ManyToOne
    @MapsId("idTipoArma")
    private TipoArma tipoArma;

    // Métodos getter y setter

    public TipoLicenciaFacultaTipoArmaId getId() {
        return id;
    }

    public void setId(TipoLicenciaFacultaTipoArmaId id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TipoLicenciaFacultaTipoArma{" + "id=" + id + ", tipoLicencia=" + tipoLicencia + ", tipoArma=" + tipoArma + '}';
    }
    
    

}
    