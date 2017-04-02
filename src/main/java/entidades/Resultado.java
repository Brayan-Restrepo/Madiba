package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Resultado" database table.
 * 
 */
@Entity
@Table(name="\"Resultado\"")
@NamedQueries({
	@NamedQuery(name="Resultado.findAll", query="SELECT r FROM Resultado r"),
	@NamedQuery(name="Resultado.countAll", query="SELECT count(a.idResultado) FROM Resultado a")
})
public class Resultado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_resultado")
	private Long idResultado;

	private String conclusion;

	@Column(name="nueva_cuantia")
	private double nuevaCuantia;
	
	@Column(name="tipo_resultado")
	private String tipoResultado;

	//bi-directional many-to-one association to Audiencia
	@ManyToOne
	@JoinColumn(name="id_audiencia")
	private Audiencia audiencia;

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

	public Audiencia getAudiencia() {
		return this.audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	public double getNuevaCuantia() {
		return nuevaCuantia;
	}

	public void setNuevaCuantia(double nuevaCuantia) {
		this.nuevaCuantia = nuevaCuantia;
	}	
}