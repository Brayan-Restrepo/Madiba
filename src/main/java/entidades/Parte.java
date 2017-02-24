package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Parte" database table.
 * 
 */
@Entity
@Table(name="\"Parte\"")
@NamedQuery(name="Parte.findAll", query="SELECT p FROM Parte p")
public class Parte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_parte")
	private Long idParte;

	private String apellidos;

	private String correo;

	private String direccion;

	private String identificacion;

	private String nombres;

	private String telefono;

	@Column(name="tipo_id")
	private String tipoId;

	@Column(name="tipo_parte")
	private String tipoParte;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="parte")
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;

	public Parte() {
	}

	public Long getIdParte() {
		return this.idParte;
	}

	public void setIdParte(Long idParte) {
		this.idParte = idParte;
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

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getTipoParte() {
		return this.tipoParte;
	}

	public void setTipoParte(String tipoParte) {
		this.tipoParte = tipoParte;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setParte(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setParte(null);

		return asistencia;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}