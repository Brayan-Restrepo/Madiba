package entidades;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the "Reparto" database table.
 * 
 */
@Entity
@Table(name="\"Reparto\"")
@NamedQuery(name="Reparto.findAll", query="SELECT r FROM Reparto r")
public class Reparto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long turno;

	private String estado;
/*
	//bi-directional one-to-one association to Conciliador
	@OneToOne(mappedBy="reparto")
	private Conciliador conciliador;
*/
	//bi-directional many-to-one association to Conciliador
	@ManyToOne
	@JoinColumn(name="id_conciliador")
	private Conciliador conciliador;
		
	public Reparto() {
	}

	public Long getTurno() {
		return this.turno;
	}

	public void setTurno(Long turno) {
		this.turno = turno;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Conciliador getConciliador() {
		return this.conciliador;
	}

	public void setConciliador(Conciliador conciliador) {
		this.conciliador = conciliador;
	}
}