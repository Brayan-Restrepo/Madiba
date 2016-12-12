package presentacion;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import negocio.iLoginBean;
 
@ManagedBean
public class ControllerLogin {
	private boolean validado;
	
	@ManagedProperty(value="#{modelLogin}")
	private ModelLogin login;
	
	@EJB
	iLoginBean loginBean;

	public ControllerLogin(){	
	}

	public ModelLogin getLogin() {
		return login;
	}

	public void setLogin(ModelLogin login) {
		this.login = login;
	}

	/**
	 * Valida si el usuario existe y la contraseña es correcta, sino muestra mensajes de alerta
	 */
	public void autenticar() {	
		String nickname = login.getNickname();
		String password = login.getPassword();
		boolean usuarioValido = loginBean.autenticarUsuario(nickname,password);
		FacesMessage msg;
		if(usuarioValido){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: "+nickname+" Correcto", null);
			validado = true;
			LoginSingleton loginSing= LoginSingleton.getInstance(nickname);
			//loginSing.cerrarSession();
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: "+nickname+" o Contraseña Incorrecta", null);
			validado = false;
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	/**
	 * Verifica si el usuario se valido correctamente y direcciona a la pagina de inicio o se queda en la que esta
	 * @return Retorna el nombre de la vista a mostrar
	 */
	public String entrar(){
		if(validado){
			return "index";
		}else{
			return "login"; 
		}
	}
	
	public void cerrarSession(){
		LoginSingleton loginSing= LoginSingleton.getInstance("");
		loginSing.cerrarSession();
	}
	
}






