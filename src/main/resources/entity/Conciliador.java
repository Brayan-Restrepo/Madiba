package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the conciliador database table.
 * 
 */
@Entity
@NamedQuery(name="Conciliador.findAll", query="SELECT c FROM Conciliador c")
public class Conciliador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String apellidos;

	private String email;

	private String especialidad;

	private String nombres;

	private BigDecimal telefono;

	//bi-directional one-to-one association to Reparto
	@OneToOne(mappedBy="conciliador")
	private Reparto reparto;

	public Conciliador() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public BigDecimal getTelefono() {
		return this.telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

	public Reparto getReparto() {
		return this.reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}

}