package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ModelLogin {
	
	public ModelLogin(){
		nickname="conalbos";
		role="conalbos";
	}
	private String nickname;
	private String password;
	private String role;
	private Long idConciliador;
	private boolean validado;
	
	public Long getIdConciliador() {
		return idConciliador;
	}

	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}