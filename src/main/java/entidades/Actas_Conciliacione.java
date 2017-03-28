package entidades;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Actas_Conciliaciones" database table.
 * 
 */
@Entity
@Table(name="\"Actas_Conciliaciones\"")
@NamedQuery(name="Actas_Conciliacione.findAll", query="SELECT a FROM Actas_Conciliacione a")
public class Actas_Conciliacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_acta_concil")
	private Long idActaConcil;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_expedicion")
	private Date fechaExpedicion;

	@Column(name="limite_copias")
	private Integer limiteCopias;

	private String ruta;

	private String tipo;

	//bi-directional many-to-one association to Solicitud
	@ManyToOne
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;

	//bi-directional many-to-one association to Copia
	@OneToMany(mappedBy="actasConciliacione")
	private List<Copia> copias;

	public Actas_Conciliacione() {
	}

	public Long getIdActaConcil() {
		return this.idActaConcil;
	}

	public void setIdActaConcil(Long idActaConcil) {
		this.idActaConcil = idActaConcil;
	}

	public Date getFechaExpedicion() {
		return this.fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public Integer getLimiteCopias() {
		return this.limiteCopias;
	}

	public void setLimiteCopias(Integer limiteCopias) {
		this.limiteCopias = limiteCopias;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public List<Copia> getCopias() {
		return this.copias;
	}

	public void setCopias(List<Copia> copias) {
		this.copias = copias;
	}

	public Copia addCopia(Copia copia) {
		getCopias().add(copia);
		copia.setActasConciliacione(this);

		return copia;
	}

	public Copia removeCopia(Copia copia) {
		getCopias().remove(copia);
		copia.setActasConciliacione(null);

		return copia;
	}

}