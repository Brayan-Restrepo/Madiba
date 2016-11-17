package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Especialidad" database table.
 * 
 */
@Entity
@Table(name="\"Especialidad\"")
@NamedQuery(name="Especialidad.findAll", query="SELECT e FROM Especialidad e")
public class Especialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_especialidad")
	private Integer idEspecialidad;

	@Column(name="nombre_especialidad")
	private String nombreEspecialidad;

	//bi-directional many-to-one association to Conciliador
	@ManyToOne
	@JoinColumn(name="id_conciliador")
	private Conciliador conciliador;

	public Especialidad() {
	}

	public Integer getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombreEspecialidad() {
		return this.nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public Conciliador getConciliador() {
		return this.conciliador;
	}

	public void setConciliador(Conciliador conciliador) {
		this.conciliador = conciliador;
	}

}