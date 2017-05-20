package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the audconcil database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Audconcil.findConciliadorFecha", query="SELECT a FROM Audconcil a WHERE a.fechasolicitud BETWEEN :fechaini AND :fechafin and a.idconciliador = :identificacion AND a.estadoaudiencia like :estado ORDER BY a.radicado"),
	@NamedQuery(name="Audconcil.findConciliador", query="SELECT a FROM Audconcil a WHERE a.idconciliador = :identificacion"),
	@NamedQuery(name="Audconcil.findAll", query="SELECT a FROM Audconcil a")
})
public class Audconcil implements Serializable {
	private static final long serialVersionUID = 1L;

	private String asunto;

	private String conciliador;

	private String estadoaudiencia;

	@Temporal(TemporalType.DATE)
	private Date fechaaudiencia;

	@Temporal(TemporalType.DATE)
	private Date fechasolicitud;

	private Integer horafinaudiencia;

	private Integer horainiaudiencia;

	@Id
	@Column(name="id")
	private Long id;

	private Long idconciliador;

	private Integer numeroaudiencia;

	private String parte;

	private Integer pretensiones;

	private String radicado;

	private String sala;

	private String tipodesignacion;

	private String tipoparte;

	public Audconcil() {
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getConciliador() {
		return this.conciliador;
	}

	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}

	public String getEstadoaudiencia() {
		return this.estadoaudiencia;
	}

	public void setEstadoaudiencia(String estadoaudiencia) {
		this.estadoaudiencia = estadoaudiencia;
	}

	public Date getFechaaudiencia() {
		return this.fechaaudiencia;
	}

	public void setFechaaudiencia(Date fechaaudiencia) {
		this.fechaaudiencia = fechaaudiencia;
	}

	public Date getFechasolicitud() {
		return this.fechasolicitud;
	}

	public void setFechasolicitud(Date fechasolicitud) {
		this.fechasolicitud = fechasolicitud;
	}

	public Integer getHorafinaudiencia() {
		return this.horafinaudiencia;
	}

	public void setHorafinaudiencia(Integer horafinaudiencia) {
		this.horafinaudiencia = horafinaudiencia;
	}

	public Integer getHorainiaudiencia() {
		return this.horainiaudiencia;
	}

	public void setHorainiaudiencia(Integer horainiaudiencia) {
		this.horainiaudiencia = horainiaudiencia;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdconciliador() {
		return this.idconciliador;
	}

	public void setIdconciliador(Long idconciliador) {
		this.idconciliador = idconciliador;
	}

	public Integer getNumeroaudiencia() {
		return this.numeroaudiencia;
	}

	public void setNumeroaudiencia(Integer numeroaudiencia) {
		this.numeroaudiencia = numeroaudiencia;
	}

	public String getParte() {
		return this.parte;
	}

	public void setParte(String parte) {
		this.parte = parte;
	}

	public Integer getPretensiones() {
		return this.pretensiones;
	}

	public void setPretensiones(Integer pretensiones) {
		this.pretensiones = pretensiones;
	}

	public String getRadicado() {
		return this.radicado;
	}

	public void setRadicado(String radicado) {
		this.radicado = radicado;
	}

	public String getSala() {
		return this.sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getTipodesignacion() {
		return this.tipodesignacion;
	}

	public void setTipodesignacion(String tipodesignacion) {
		this.tipodesignacion = tipodesignacion;
	}

	public String getTipoparte() {
		return this.tipoparte;
	}

	public void setTipoparte(String tipoparte) {
		this.tipoparte = tipoparte;
	}

}