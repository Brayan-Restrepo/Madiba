package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reparto database table.
 * 
 */
@Entity
@NamedQuery(name="Reparto.findAll", query="SELECT r FROM Reparto r")
public class Reparto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long cedula;

	private String turno;

	//bi-directional one-to-one association to Conciliador
	@OneToOne
	@JoinColumn(name="cedula")
	private Conciliador conciliador;

	public Reparto() {
	}

	public long getCedula() {
		return this.cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getTurno() {
		return this.turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Conciliador getConciliador() {
		return this.conciliador;
	}

	public void setConciliador(Conciliador conciliador) {
		this.conciliador = conciliador;
	}

}