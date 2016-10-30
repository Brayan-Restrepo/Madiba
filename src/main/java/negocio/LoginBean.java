package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.UsuarioDAO;
import entidades.Usuario;

@Stateless
public class LoginBean implements iLoginBean{
	
	@Inject
	UsuarioDAO usuarioDAO;

	public LoginBean(){
		
	}

	public boolean autenticarUsuario(String nickname, String password){
		boolean usuarioValido = false;
		Usuario usuarioBD = usuarioDAO.consultarUsuario(nickname);
		if(usuarioBD != null){
			usuarioValido = true;
		}
		return usuarioValido;
	}
}
