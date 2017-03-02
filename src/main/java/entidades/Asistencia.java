package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Asistencia" database table.
 * 
 */
@Entity
@Table(name="\"Asistencia\"")
@NamedQueries({
	@NamedQuery(name="Asistencia.findAll", query="SELECT a FROM Asistencia a"),
	@NamedQuery(name="Asistencia.countAll", query="SELECT max(a.idAsistencia) FROM Asistencia a")
})
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_asistencia")
	private Long idAsistencia;

	private Boolean asistio;

	private String excusa;

	//bi-directional many-to-one association to Audiencia
	@ManyToOne
	@JoinColumn(name="id_audiencia")
	private Audiencia audiencia;

	//bi-directional many-to-one association to Parte
	@ManyToOne
	@JoinColumn(name="id_parte")
	private Parte parte;

	public Asistencia() {
	}

	public Long getIdAsistencia() {
		return this.idAsistencia;
	}

	public void setIdAsistencia(Long idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public Boolean getAsistio() {
		return this.asistio;
	}

	public void setAsistio(Boolean asistio) {
		this.asistio = asistio;
	}

	public String getExcusa() {
		return this.excusa;
	}

	public void setExcusa(String excusa) {
		this.excusa = excusa;
	}

	public Audiencia getAudiencia() {
		return this.audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	public Parte getParte() {
		return this.parte;
	}

	public void setParte(Parte parte) {
		this.parte = parte;
	}

}