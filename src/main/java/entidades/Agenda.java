package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "Agenda" database table.
 * 
 */
@Entity
@Table(name="\"Agenda\"")
@NamedQuery(name="Agenda.findAll", query="SELECT a FROM Agenda a")
public class Agenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_agenda")
	private Long idAgenda;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="hora_final")
	private Integer horaFinal;

	@Column(name="hora_inicial")
	private Integer horaInicial;

	//bi-directional one-to-one association to Audiencia
	@OneToOne
	@JoinColumn(name="id_audiencia")
	private Audiencia audiencia;

	//bi-directional many-to-one association to Sala
	@ManyToOne
	@JoinColumn(name="id_sala")
	private Sala sala;

	public Agenda() {
	}

	public Long getIdAgenda() {
		return this.idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getHoraFinal() {
		return this.horaFinal;
	}

	public void setHoraFinal(Integer horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Integer getHoraInicial() {
		return this.horaInicial;
	}

	public void setHoraInicial(Integer horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Audiencia getAudiencia() {
		return this.audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	public Sala getSala() {
		return this.sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}