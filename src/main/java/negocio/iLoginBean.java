package negocio;

import javax.ejb.Remote;

@Remote
public interface iLoginBean {
	
	public boolean autenticarUsuario(String nickname, String password);
}