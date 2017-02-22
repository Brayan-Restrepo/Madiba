package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Designacion" database table.
 * 
 */
@Entity
@Table(name="\"Designacion\"")
@NamedQuery(name="Designacion.findAll", query="SELECT d FROM Designacion d")
public class Designacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_designacion")
	private Long idDesignacion;

	private Boolean aceptada;

	private String observacion;

	@Column(name="tipo_designacion")
	private String tipoDesignacion;

	//bi-directional many-to-one association to Conciliador
	@ManyToOne
	@JoinColumn(name="id_conciliador")
	private Conciliador conciliador;

	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;

	public Designacion() {
	}

	public Long getIdDesignacion() {
		return this.idDesignacion;
	}

	public void setIdDesignacion(Long idDesignacion) {
		this.idDesignacion = idDesignacion;
	}

	public Boolean getAceptada() {
		return this.aceptada;
	}

	public void setAceptada(Boolean aceptada) {
		this.aceptada = aceptada;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipoDesignacion() {
		return this.tipoDesignacion;
	}

	public void setTipoDesignacion(String tipoDesignacion) {
		this.tipoDesignacion = tipoDesignacion;
	}

	public Conciliador getConciliador() {
		return this.conciliador;
	}

	public void setConciliador(Conciliador conciliador) {
		this.conciliador = conciliador;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}