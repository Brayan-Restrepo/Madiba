package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the copias database table.
 * 
 */
@Entity
@Table(name="copias")
@NamedQuery(name="Copia.findAll", query="SELECT c FROM Copia c")
public class Copia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_copia")
	private Long idCopia;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_generacion")
	private Date fechaGeneracion;

	@Column(name="num_copia")
	private Integer numCopia;

	//bi-directional many-to-one association to Actas_Conciliacione
	@ManyToOne
	@JoinColumn(name="id_acta_constancia")
	private Actas_Conciliacione actasConciliacione;

	public Copia() {
	}

	public Long getIdCopia() {
		return this.idCopia;
	}

	public void setIdCopia(Long idCopia) {
		this.idCopia = idCopia;
	}

	public Date getFechaGeneracion() {
		return this.fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Integer getNumCopia() {
		return this.numCopia;
	}

	public void setNumCopia(Integer numCopia) {
		this.numCopia = numCopia;
	}

	public Actas_Conciliacione getActasConciliacione() {
		return this.actasConciliacione;
	}

	public void setActasConciliacione(Actas_Conciliacione actasConciliacione) {
		this.actasConciliacione = actasConciliacione;
	}

}