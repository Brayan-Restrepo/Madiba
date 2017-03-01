package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Resultado" database table.
 * 
 */
@Entity
@Table(name="\"Resultado\"")
@NamedQuery(name="Resultado.findAll", query="SELECT r FROM Resultado r")
public class Resultado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_resultado")
	private Long idResultado;

	private String conclusion;

	@Column(name="tipo_resultado")
	private String tipoResultado;

	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;

	public Resultado() {
	}

	public Long getIdResultado() {
		return this.idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	public String getConclusion() {
		return this.conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getTipoResultado() {
		return this.tipoResultado;
	}

	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}