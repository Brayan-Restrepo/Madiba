package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "Auditoria" database table.
 * 
 */
@Entity
@Table(name="\"Auditoria\"")
@NamedQuery(name="Auditoria.findAll", query="SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_auditoria")
	private Long idAuditoria;

	private String accion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="id_registro")
	private Integer idRegistro;

	@Column(name="registro_ant")
	private String registroAnt;

	@Column(name="registro_desp")
	private String registroDesp;

	private String responsable;

	private String tabla;

	public Auditoria() {
	}

	public Long getIdAuditoria() {
		return this.idAuditoria;
	}

	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public String getAccion() {
		return this.accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdRegistro() {
		return this.idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getRegistroAnt() {
		return this.registroAnt;
	}

	public void setRegistroAnt(String registroAnt) {
		this.registroAnt = registroAnt;
	}

	public String getRegistroDesp() {
		return this.registroDesp;
	}

	public void setRegistroDesp(String registroDesp) {
		this.registroDesp = registroDesp;
	}

	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTabla() {
		return this.tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

}