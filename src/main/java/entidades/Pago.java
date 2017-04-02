package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "Pago" database table.
 * 
 */
@Entity
@Table(name="\"Pago\"")
@NamedQuery(name="Pago.findAll", query="SELECT p FROM Pago p")
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_pago")
	private Long idPago;

	private Integer banco;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="forma_pago")
	private Integer formaPago;

	private Integer referencia;

	private double valor;

	/**
	 * (PAGADO - NOPAGADO) - Los Pagos Normales
	 * (SPAGADO - SNOPAGADO) - Los pagos por sobrecosto (del 10% o por nueva Cuantia) 
	 */
	private String estado;
	
	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;

	public Pago() {
	}

	public Long getIdPago() {
		return this.idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	public Integer getBanco() {
		return this.banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getReferencia() {
		return this.referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}