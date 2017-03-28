package entidades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the "Conciliador" database table.
 * 
 */
@Entity
@Table(name="\"Conciliador\"")
@NamedQueries({
	@NamedQuery(name="Conciliador.findAll", query="SELECT c FROM Conciliador c ORDER BY c.apellidos, c.nombres"),
	@NamedQuery(name="Conciliador.findAllReparto", query="SELECT c FROM Conciliador c,Reparto r WHERE c.idConciliador = r.conciliador.idConciliador ORDER BY r.turno")
})
public class Conciliador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_conciliador")
	private Long idConciliador;

	private String apellidos;

	private String correo;

	private String egresado;

	private Integer experiencia;

	private String foto;

	private String identificacion;

	private String nombres;

	private String telefono;

	@Column(name="tipo_id")
	private String tipoId;

	/*
	//bi-directional one-to-one association to Reparto
	@OneToOne
	@JoinColumn(name="id_conciliador", referencedColumnName="id_conciliador")
	private Reparto reparto;
	*/
	
	//bi-directional many-to-one association to Reparto
	@OneToMany(mappedBy="conciliador")
	private List<Reparto> repartos;
	
	//bi-directional many-to-one association to Conciliador_Especialidad
	@OneToMany(mappedBy="conciliador")
	private List<Conciliador_Especialidad> conciliadorEspecialidads;

	//bi-directional many-to-one association to Designacion
	@OneToMany(mappedBy="conciliador")
	private List<Designacion> designacions;

	public Conciliador() {
	}

	public Long getIdConciliador() {
		return this.idConciliador;
	}

	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEgresado() {
		return this.egresado;
	}

	public void setEgresado(String egresado) {
		this.egresado = egresado;
	}

	public Integer getExperiencia() {
		return this.experiencia;
	}

	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoId() {
		return this.tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public List<Reparto> getRepartos() {
		return this.repartos;
	}

	public void setRepartos(List<Reparto> repartos) {
		this.repartos = repartos;
	}

	public Reparto addReparto(Reparto reparto) {
		getRepartos().add(reparto);
		reparto.setConciliador(this);

		return reparto;
	}

	public Reparto removeReparto(Reparto reparto) {
		getRepartos().remove(reparto);
		reparto.setConciliador(null);

		return reparto;
	}

	public List<Conciliador_Especialidad> getConciliadorEspecialidads() {
		return this.conciliadorEspecialidads;
	}

	public void setConciliadorEspecialidads(List<Conciliador_Especialidad> conciliadorEspecialidads) {
		this.conciliadorEspecialidads = conciliadorEspecialidads;
	}

	public Conciliador_Especialidad addConciliadorEspecialidad(Conciliador_Especialidad conciliadorEspecialidad) {
		getConciliadorEspecialidads().add(conciliadorEspecialidad);
		conciliadorEspecialidad.setConciliador(this);

		return conciliadorEspecialidad;
	}

	public Conciliador_Especialidad removeConciliadorEspecialidad(Conciliador_Especialidad conciliadorEspecialidad) {
		getConciliadorEspecialidads().remove(conciliadorEspecialidad);
		conciliadorEspecialidad.setConciliador(null);

		return conciliadorEspecialidad;
	}

	public List<Designacion> getDesignacions() {
		return this.designacions;
	}

	public void setDesignacions(List<Designacion> designacions) {
		this.designacions = designacions;
	}

	public Designacion addDesignacion(Designacion designacion) {
		getDesignacions().add(designacion);
		designacion.setConciliador(this);

		return designacion;
	}

	public Designacion removeDesignacion(Designacion designacion) {
		getDesignacions().remove(designacion);
		designacion.setConciliador(null);

		return designacion;
	}

}