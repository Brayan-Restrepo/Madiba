package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private Long idEspecialidad;

	@Column(name="nombre_especialidad")
	private String nombreEspecialidad;

	//bi-directional many-to-one association to Conciliador
	@ManyToOne
	@JoinColumn(name="id_conciliador")
	private Conciliador conciliador;

	//bi-directional many-to-one association to Conciliador_Especialidad
	@OneToMany(mappedBy="especialidad")
	private List<Conciliador_Especialidad> conciliadorEspecialidads;

	public Especialidad() {
	}

	public Long getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(Long idEspecialidad) {
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

	public List<Conciliador_Especialidad> getConciliadorEspecialidads() {
		return this.conciliadorEspecialidads;
	}

	public void setConciliadorEspecialidads(List<Conciliador_Especialidad> conciliadorEspecialidads) {
		this.conciliadorEspecialidads = conciliadorEspecialidads;
	}

	public Conciliador_Especialidad addConciliadorEspecialidad(Conciliador_Especialidad conciliadorEspecialidad) {
		getConciliadorEspecialidads().add(conciliadorEspecialidad);
		conciliadorEspecialidad.setEspecialidad(this);

		return conciliadorEspecialidad;
	}

	public Conciliador_Especialidad removeConciliadorEspecialidad(Conciliador_Especialidad conciliadorEspecialidad) {
		getConciliadorEspecialidads().remove(conciliadorEspecialidad);
		conciliadorEspecialidad.setEspecialidad(null);

		return conciliadorEspecialidad;
	}

}