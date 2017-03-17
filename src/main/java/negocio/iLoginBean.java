package negocio;

import javax.ejb.Remote;

@Remote
public interface iLoginBean {
	
	public boolean autenticarUsuario(String nickname, String password);
	public String userRole(String nickname, String password);
}