package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Anexo" database table.
 * 
 */
@Entity
@Table(name="\"Anexo\"")
@NamedQueries({
	@NamedQuery(name="Anexo.findAll", query="SELECT a FROM Anexo a"),
	@NamedQuery(name="Anexo.countAll", query="SELECT max(a.idAnexo) FROM Anexo a")
})
public class Anexo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_anexo")
	private Long idAnexo;

	@Column(name="anexo_num")
	private Integer anexoNum;

	private String contenido;

	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;

	public Anexo() {
	}

	public Long getIdAnexo() {
		return this.idAnexo;
	}

	public void setIdAnexo(Long idAnexo) {
		this.idAnexo = idAnexo;
	}

	public Integer getAnexoNum() {
		return this.anexoNum;
	}

	public void setAnexoNum(Integer anexoNum) {
		this.anexoNum = anexoNum;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}