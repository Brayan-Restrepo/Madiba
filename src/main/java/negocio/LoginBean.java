package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.LoginDAO;
import entidades.Usuario;

@Stateless
public class LoginBean implements iLoginBean{
	
	@Inject
	LoginDAO loginDAO;

	public LoginBean(){}
	
	@Override
	public boolean autenticarUsuario(String nickname, String password){
		boolean usuarioValido = false;
		Usuario usuarioBD = loginDAO.consultarUsuario(nickname);
		if(usuarioBD != null){
			if(usuarioBD.getPassword().equals(password)){
				usuarioValido = true;
			}
		}
		return usuarioValido;
	}

	@Override
	public String[] userRole(String nickname, String password) {
		Usuario usuarioBD = loginDAO.consultarUsuario(nickname);
		String[] datos = new String[2];
		if(usuarioBD != null){
		 datos[0]=usuarioBD.getRole();
		 if(usuarioBD.getIdConciliador()!=null){
			 datos[1]=usuarioBD.getIdConciliador().toString();
		 }else{
			 datos[1]="";
		 }
		 
		}
		return datos;
	}
}