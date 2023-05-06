
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
@Table(name = "arma_Replica")
@Cacheable(false)
@XmlRootElement
public class ArmaReplica implements Serializable {

    @Id
    @ManyToOne
    @Column(name="id_arma")
    private Arma id_arma;

    public Arma getId_arma() {
        return id_arma;
    }

    public void setId_arma(Arma id_arma) {
        this.id_arma = id_arma;
    }
    
    @Column(name="tipo_gas")
    private String tipo_gas;
    
    @Column(name="capacidad_cargador")
    private Integer capacidad_cargador;
    

    @Column(name="piezas_canon")
    private String piezas_canon;

    public String getTipo_gas() {
        return tipo_gas;
    }

    public void setTipo_gas(String tipo_gas) {
        this.tipo_gas = tipo_gas;
    }

    public Integer getCapacidad_cargador() {
        return capacidad_cargador;
    }

    public void setCapacidad_cargador(Integer capacidad_cargador) {
        this.capacidad_cargador = capacidad_cargador;
    }

    public String getPiezas_canon() {
        return piezas_canon;
    }

    public void setPiezas_canon(String piezas_canon) {
        this.piezas_canon = piezas_canon;
    }

    @Override
    public String toString() {
        return "ArmaReplica{" + "id_arma=" + id_arma + ", tipo_gas=" + tipo_gas + ", capacidad_cargador=" + capacidad_cargador + ", piezas_canon=" + piezas_canon + '}';
    }
    
    
    
    
    
}
    
    