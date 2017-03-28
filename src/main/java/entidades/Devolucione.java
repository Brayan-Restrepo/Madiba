package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the devoluciones database table.
 * 
 */
@Entity
@Table(name="devoluciones")
@NamedQuery(name="Devolucione.findAll", query="SELECT d FROM Devolucione d")
public class Devolucione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_devolucion")
	private Long idDevolucion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private double valor;

	private boolean devolucion;
	
	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;

	public Devolucione() {
	}

	public Long getIdDevolucion() {
		return this.idDevolucion;
	}

	public void setIdDevolucion(Long idDevolucion) {
		this.idDevolucion = idDevolucion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public boolean isDevolucion() {
		return devolucion;
	}

	public void setDevolucion(boolean devolucion) {
		this.devolucion = devolucion;
	}
	
}