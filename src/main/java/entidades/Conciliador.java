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
@NamedQuery(name="Conciliador.findAll", query="SELECT c FROM Conciliador c ORDER BY c.apellidos, c.nombres")
public class Conciliador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_conciliador")
	private Long idConciliador;

	private String apellidos;

	private String codigo;

	private String correo;

	private String documento;

	private String egresado;

	private Integer experiencia;

	private String foto;

	private String identificacion;

	private String nombres;

	private String telefono;

	@Column(name="tipo_id")
	private String tipoId;

	//bi-directional many-to-one association to Especialidad
	@OneToMany(mappedBy="conciliador",fetch=FetchType.EAGER)
	private List<Especialidad> especialidads;

	//bi-directional many-to-one association to Conciliador_Especialidad
	@OneToMany(mappedBy="conciliador")
	private List<Conciliador_Especialidad> conciliadorEspecialidads;

	//bi-directional many-to-one association to Designacion
	@OneToMany(mappedBy="conciliador")
	private List<Designacion> designacions;

	//bi-directional one-to-one association to Reparto
	@OneToOne(mappedBy="conciliador")
	private Reparto reparto;

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

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public List<Especialidad> getEspecialidads() {
		return this.especialidads;
	}

	public void setEspecialidads(List<Especialidad> especialidads) {
		this.especialidads = especialidads;
	}

	public Especialidad addEspecialidad(Especialidad especialidad) {
		getEspecialidads().add(especialidad);
		especialidad.setConciliador(this);

		return especialidad;
	}

	public Especialidad removeEspecialidad(Especialidad especialidad) {
		getEspecialidads().remove(especialidad);
		especialidad.setConciliador(null);

		return especialidad;
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

	public Reparto getReparto() {
		return this.reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}

}