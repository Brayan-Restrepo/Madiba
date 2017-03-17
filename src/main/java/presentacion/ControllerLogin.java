package presentacion;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import negocio.iLoginBean;
 

@ManagedBean
public class ControllerLogin {
		
	@ManagedProperty(value="#{modelLogin}")
	private ModelLogin login;
	
	@EJB
	iLoginBean loginBean;

	public ControllerLogin(){}

	public ModelLogin getLogin() {
		return login;
	}

	public void setLogin(ModelLogin login) {
		this.login = login;
	}

	public void autenticar() {	
		String nickname = this.login.getNickname();
		String password = this.login.getPassword();
		
		
		boolean usuarioValido = loginBean.autenticarUsuario(nickname,password);
		FacesMessage msg;
		
		if(usuarioValido){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: "+nickname+" Correcto", null);
			this.login.setValidado(true);
			//Si el Usuario es Autenticado se Le Asigna su Rol
			this.login.setRole(loginBean.userRole(nickname, password));
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: "+nickname+" Incorrecto", null);
			this.login.setValidado(false);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public String entrar(){
		if(this.login.isValidado()){
			return "index";
		}else{
			return "login"; 
		}
	}
	

	public void redirect(){
		if(!this.login.isValidado()){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
