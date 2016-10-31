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
	private Integer idEspecialidad;

	@Column(name="nombre_especialidad")
	private String nombreEspecialidad;

	//bi-directional many-to-one association to Conciliador_Especialidad
	@OneToMany(mappedBy="especialidad")
	private List<Conciliador_Especialidad> conciliadorEspecialidades;

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

	public List<Conciliador_Especialidad> getConciliadorEspecialidades() {
		return this.conciliadorEspecialidades;
	}

	public void setConciliadorEspecialidades(List<Conciliador_Especialidad> conciliadorEspecialidades) {
		this.conciliadorEspecialidades = conciliadorEspecialidades;
	}

	public Conciliador_Especialidad addConciliadorEspecialidade(Conciliador_Especialidad conciliadorEspecialidade) {
		getConciliadorEspecialidades().add(conciliadorEspecialidade);
		conciliadorEspecialidade.setEspecialidad(this);

		return conciliadorEspecialidade;
	}

	public Conciliador_Especialidad removeConciliadorEspecialidade(Conciliador_Especialidad conciliadorEspecialidade) {
		getConciliadorEspecialidades().remove(conciliadorEspecialidade);
		conciliadorEspecialidade.setEspecialidad(null);

		return conciliadorEspecialidade;
	}

}