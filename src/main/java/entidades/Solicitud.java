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
@NamedQueries({
	@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s WHERE s.estado like :estado ORDER BY s.idSolicitud"),
	
	@NamedQuery(name="Solicitud.findAudiencias", query="SELECT s FROM Solicitud s WHERE s.fecha between :fechaInicial AND :fechaFinal AND s.estado like 'AUDIENCIA%' ORDER BY s.fecha"),
	@NamedQuery(name="Solicitud.findAudienciasFiltroParteFecha", query="SELECT DISTINCT s FROM Solicitud s, Agenda ag, Audiencia au, Parte p WHERE ag.fecha BETWEEN :fechaInicial AND :fechaFinal AND ag.audiencia.idAudiencia = au.idAudiencia AND au.solicitud.idSolicitud = s.idSolicitud AND s.idSolicitud = p.solicitud.idSolicitud AND p.identificacion=:identificacion AND p.tipoParte=:tipoParte AND s.estado like 'AUDIENCIA%' ORDER BY s.fecha"),
	@NamedQuery(name="Solicitud.findAudienciasFiltroConciliadorFecha", query="SELECT DISTINCT s FROM Agenda agd, Audiencia aud, Solicitud s, Designacion d, Conciliador c WHERE agd.fecha BETWEEN :fechaInicial AND :fechaFinal AND agd.audiencia.idAudiencia = aud.idAudiencia AND aud.solicitud.idSolicitud = s.idSolicitud AND s.idSolicitud = d.solicitud.idSolicitud AND d.aceptada <> false AND d.conciliador.idConciliador = c.idConciliador AND c.identificacion=:identificacion AND s.estado like 'AUDIENCIA%' ORDER BY s.fecha"),
	@NamedQuery(name="Solicitud.findAudienciasFiltroRadicadoFecha", query="SELECT s FROM Agenda agd, Audiencia aud, Solicitud s WHERE agd.fecha BETWEEN :fechaInicial AND :fechaFinal AND agd.audiencia.idAudiencia = aud.idAudiencia AND aud.solicitud.idSolicitud = s.idSolicitud AND s.nroRadicado = :nroRadicado AND s.estado like 'AUDIENCIA%' ORDER BY s.fecha"),
	
	@NamedQuery(name="Solicitud.findAudienciasConciliador", query="SELECT s FROM Solicitud s, Designacion d WHERE s.idSolicitud=d.solicitud.idSolicitud AND d.conciliador.idConciliador=:idConciliador AND d.aceptada <> false AND s.estado like 'AUDIENCIA%' ORDER BY s.fecha"),
	@NamedQuery(name="Solicitud.findAudienciasConciliadorFiltroParteFecha", query="SELECT s FROM Agenda agd, Audiencia aud, Solicitud s, Parte p, Designacion d WHERE agd.fecha BETWEEN :fechaInicial AND :fechaFinal AND agd.audiencia.idAudiencia = aud.idAudiencia AND aud.solicitud.idSolicitud = s.idSolicitud AND s.idSolicitud = p.solicitud.idSolicitud AND p.identificacion = :identificacion AND p.tipoParte = :tipoParte AND s.idSolicitud = d.solicitud.idSolicitud AND d.aceptada <> false AND d.conciliador.idConciliador = :idConciliador  AND s.estado like 'AUDIENCIA%' ORDER BY s.fecha"),
	@NamedQuery(name="Solicitud.findAudienciasConciliadorFiltroRadicadoFecha", query="SELECT s FROM Agenda agd, Audiencia aud, Solicitud s, Designacion d WHERE agd.fecha BETWEEN :fechaInicial AND :fechaFinal AND agd.audiencia.idAudiencia = aud.idAudiencia AND aud.solicitud.idSolicitud = s.idSolicitud AND s.nroRadicado = :nroRadicado AND s.idSolicitud = d.solicitud.idSolicitud AND d.aceptada <> false AND d.conciliador.idConciliador = :idConciliador AND s.estado like 'AUDIENCIA%' ORDER BY s.fecha"),
	
	@NamedQuery(name="Solicitud.findSolicitudes", query="SELECT s FROM Solicitud s WHERE s.fecha between :fechaInicial AND :fechaFinal ORDER BY s.fecha"),
	@NamedQuery(name="Solicitud.findSolicitudesFiltroParteFecha", query="SELECT s FROM Solicitud s, Parte p WHERE s.fecha between :fechaInicial AND :fechaFinal AND s.idSolicitud = p.solicitud.idSolicitud AND p.identificacion = :identificacion AND p.tipoParte=:tipoParte ORDER BY s.fecha"),
	@NamedQuery(name="Solicitud.findSolicitudesFiltroConciliadorFecha", query="SELECT s FROM Solicitud s, Designacion d, Conciliador c WHERE s.fecha BETWEEN :fechaInicial AND :fechaFinal AND s.idSolicitud = d.solicitud.idSolicitud AND d.aceptada <> false AND d.conciliador.idConciliador = c.idConciliador AND c.identificacion = :identificacion ORDER BY s.fecha"),
	@NamedQuery(name="Solicitud.findSolicitudesFiltroRadicadoFecha", query="SELECT s FROM Solicitud s WHERE s.fecha BETWEEN :fechaInicial AND :fechaFinal AND s.nroRadicado = :nroRadicado AND s.estado like 'AUDIENCIA%' ORDER BY s.fecha"),
	
	
})
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_solicitud")
	private Long idSolicitud;

	private String asunto;

	private Boolean conciliable;

	private Double cuantia;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="nro_radicado")
	private String nroRadicado;
	
	@Column(name="valor_pagar")
	private double valorPagar;

	//bi-directional many-to-one association to Anexo
	@OneToMany(mappedBy="solicitud")
	private List<Anexo> anexos;

	//bi-directional many-to-one association to Audiencia
	@OneToMany(mappedBy="solicitud")
	private List<Audiencia> audiencias;

	//bi-directional many-to-one association to Designacion
	@OneToMany(mappedBy="solicitud")
	private List<Designacion> designacions;

	//bi-directional many-to-one association to Pago
	@OneToMany(mappedBy="solicitud")
	private List<Pago> pagos;

	//bi-directional many-to-one association to Parte
	@OneToMany(mappedBy="solicitud")
	private List<Parte> partes;

	//bi-directional many-to-one association to Actas_Conciliacione
	@OneToMany(mappedBy="solicitud")
	private List<Actas_Conciliacione> actasConciliaciones;

	//bi-directional many-to-one association to Devolucione
	@OneToMany(mappedBy="solicitud")
	private List<Devolucione> devoluciones;

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

	public Double getCuantia() {
		return this.cuantia;
	}

	public void setCuantia(Double cuantia) {
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

	public String getNroRadicado() {
		return this.nroRadicado;
	}

	public void setNroRadicado(String nroRadicado) {
		this.nroRadicado = nroRadicado;
	}
	
	public double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public List<Anexo> getAnexos() {
		return this.anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}

	public Anexo addAnexo(Anexo anexo) {
		getAnexos().add(anexo);
		anexo.setSolicitud(this);

		return anexo;
	}

	public Anexo removeAnexo(Anexo anexo) {
		getAnexos().remove(anexo);
		anexo.setSolicitud(null);

		return anexo;
	}

	public List<Audiencia> getAudiencias() {
		return this.audiencias;
	}

	public void setAudiencias(List<Audiencia> audiencias) {
		this.audiencias = audiencias;
	}

	public Audiencia addAudiencia(Audiencia audiencia) {
		getAudiencias().add(audiencia);
		audiencia.setSolicitud(this);

		return audiencia;
	}

	public Audiencia removeAudiencia(Audiencia audiencia) {
		getAudiencias().remove(audiencia);
		audiencia.setSolicitud(null);

		return audiencia;
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

	public List<Pago> getPagos() {
		return this.pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Pago addPago(Pago pago) {
		getPagos().add(pago);
		pago.setSolicitud(this);

		return pago;
	}

	public Pago removePago(Pago pago) {
		getPagos().remove(pago);
		pago.setSolicitud(null);

		return pago;
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
	public List<Actas_Conciliacione> getActasConciliaciones() {
		return this.actasConciliaciones;
	}

	public void setActasConciliaciones(List<Actas_Conciliacione> actasConciliaciones) {
		this.actasConciliaciones = actasConciliaciones;
	}

	public Actas_Conciliacione addActasConciliacione(Actas_Conciliacione actasConciliacione) {
		getActasConciliaciones().add(actasConciliacione);
		actasConciliacione.setSolicitud(this);

		return actasConciliacione;
	}

	public Actas_Conciliacione removeActasConciliacione(Actas_Conciliacione actasConciliacione) {
		getActasConciliaciones().remove(actasConciliacione);
		actasConciliacione.setSolicitud(null);

		return actasConciliacione;
	}

	public List<Devolucione> getDevoluciones() {
		return this.devoluciones;
	}

	public void setDevoluciones(List<Devolucione> devoluciones) {
		this.devoluciones = devoluciones;
	}

	public Devolucione addDevolucione(Devolucione devolucione) {
		getDevoluciones().add(devolucione);
		devolucione.setSolicitud(this);

		return devolucione;
	}

	public Devolucione removeDevolucione(Devolucione devolucione) {
		getDevoluciones().remove(devolucione);
		devolucione.setSolicitud(null);

		return devolucione;
	}

}