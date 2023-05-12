
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Zatonio
 */
public class TipoLicenciaFacultaTipoArmaId implements Serializable {
    private Long idTipoLicencia;
    private Long idTipoArma;
    
    // Constructor, getters y setters
    
    
    
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int hashCode() {
        return super.hashCode(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
    
}
    
    