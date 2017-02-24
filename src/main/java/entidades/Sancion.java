package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Sancion" database table.
 * 
 */
@Entity
@Table(name="\"Sancion\"")
@NamedQuery(name="Sancion.findAll", query="SELECT s FROM Sancion s")
public class Sancion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_sancion")
	private Long idSancion;

	private String descripcion;

	//bi-directional many-to-one association to Conciliador
	@ManyToOne
	@JoinColumn(name="id_conciliador")
	private Conciliador conciliador;

	public Sancion() {
	}

	public Long getIdSancion() {
		return this.idSancion;
	}

	public void setIdSancion(Long idSancion) {
		this.idSancion = idSancion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Conciliador getConciliador() {
		return this.conciliador;
	}

	public void setConciliador(Conciliador conciliador) {
		this.conciliador = conciliador;
	}

}