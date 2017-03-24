package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Usuario" database table.
 * 
 */
@Entity
@Table(name="\"Usuario\"")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nickname;

	private String password;

	private String role;
	
	private String idConciliador;

	public Usuario() {
	}
	
	public String getIdConciliador() {
		return idConciliador;
	}

	public void setIdConciliador(String idConciliador) {
		this.idConciliador = idConciliador;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}