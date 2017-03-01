package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Dominio" database table.
 * 
 */
@Entity
@Table(name="\"Dominio\"")
@NamedQuery(name="Dominio.findType", query="SELECT d.nombre FROM Dominio d WHERE d.tipo=:tipo")

public class Dominio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_dominio")
	private Integer idDominio;

	private String nombre;

	private String tipo;

	public Dominio() {
	}

	public Integer getIdDominio() {
		return this.idDominio;
	}

	public void setIdDominio(Integer idDominio) {
		this.idDominio = idDominio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}