
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Zatonio
 */
@Embeddable
public class TipoLicenciaFacultaTipoArmaId implements Serializable {

    @Column(name = "id_tipo_licencia")
    private Long idTipoLicencia;

    @Column(name = "id_tipo_arma")
    private Long idTipoArma;

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

    
    
}
    
    