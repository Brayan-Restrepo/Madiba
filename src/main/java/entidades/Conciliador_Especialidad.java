package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Conciliador_Especialidad" database table.
 * 
 */
@Entity
@Table(name="\"Conciliador_Especialidad\"")
@NamedQuery(name="Conciliador_Especialidad.findAll", query="SELECT c FROM Conciliador_Especialidad c")
public class Conciliador_Especialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_concil_esp")
	private Integer idConcilEsp;

	//bi-directional many-to-one association to Conciliador
	@ManyToOne
	@JoinColumn(name="id_conciliador")
	private Conciliador conciliador;

	//bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name="id_especialidad")
	private Especialidad especialidad;

	public Conciliador_Especialidad() {
	}

	public Integer getIdConcilEsp() {
		return this.idConcilEsp;
	}

	public void setIdConcilEsp(Integer idConcilEsp) {
		this.idConcilEsp = idConcilEsp;
	}

	public Conciliador getConciliador() {
		return this.conciliador;
	}

	public void setConciliador(Conciliador conciliador) {
		this.conciliador = conciliador;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

}