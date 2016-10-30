package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.LoginDAO;
import entidades.Usuario;

@Stateless
public class LoginBean implements iLoginBean{
	
	@Inject
	LoginDAO loginDAO;

	public LoginBean(){
		
	}

	public boolean autenticarUsuario(String nickname, String password){
		boolean usuarioValido = false;
		Usuario usuarioBD = loginDAO.consultarUsuario(nickname);
		if(usuarioBD != null){
			usuarioValido = true;
		}
		return usuarioValido;
	}
}
