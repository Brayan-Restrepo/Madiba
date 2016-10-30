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

	public void autenticar() {	
		String nickname = login.getNickname();
		String password = login.getPassword();
		
		boolean usuarioValido = loginBean.autenticarUsuario(nickname,password);
		FacesMessage msg;
		
		if(usuarioValido){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: "+nickname+" Correcto", null);
			validado = true;
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: "+nickname+" Incorrecto", null);
			validado = false;
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public String entrar(){
		if(validado){
			return "index";
		}else{
			return "login"; 
		}
	}
}
