package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Solicitud" database table.
 * 
 */
@Entity
@Table(name="\"Solicitud\"")
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_solicitud")
	private Long idSolicitud;

	private String asunto;

	private Boolean conciliable;

	private Integer cuantia;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="nro_radicado")
	private Integer nroRadicado;

	//bi-directional many-to-one association to Designacion
	@OneToMany(mappedBy="solicitud")
	private List<Designacion> designacions;

	//bi-directional many-to-one association to Parte
	@OneToMany(mappedBy="solicitud")
	private List<Parte> partes;

	public Solicitud() {
	}

	public Long getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Boolean getConciliable() {
		return this.conciliable;
	}

	public void setConciliable(Boolean conciliable) {
		this.conciliable = conciliable;
	}

	public Integer getCuantia() {
		return this.cuantia;
	}

	public void setCuantia(Integer cuantia) {
		this.cuantia = cuantia;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getNroRadicado() {
		return this.nroRadicado;
	}

	public void setNroRadicado(Integer nroRadicado) {
		this.nroRadicado = nroRadicado;
	}

	public List<Designacion> getDesignacions() {
		return this.designacions;
	}

	public void setDesignacions(List<Designacion> designacions) {
		this.designacions = designacions;
	}

	public Designacion addDesignacion(Designacion designacion) {
		getDesignacions().add(designacion);
		designacion.setSolicitud(this);

		return designacion;
	}

	public Designacion removeDesignacion(Designacion designacion) {
		getDesignacions().remove(designacion);
		designacion.setSolicitud(null);

		return designacion;
	}

	public List<Parte> getPartes() {
		return this.partes;
	}

	public void setPartes(List<Parte> partes) {
		this.partes = partes;
	}

	public Parte addParte(Parte parte) {
		getPartes().add(parte);
		parte.setSolicitud(this);

		return parte;
	}

	public Parte removeParte(Parte parte) {
		getPartes().remove(parte);
		parte.setSolicitud(null);

		return parte;
	}

}