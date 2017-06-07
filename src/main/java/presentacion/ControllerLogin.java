package presentacion;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
		String nickname = this.login.getNickname().toLowerCase();
		String password = MD5(this.login.getPassword());
		
		
		boolean usuarioValido = this.loginBean.autenticarUsuario(nickname,password);
		FacesMessage msg;
		
		if(usuarioValido){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: "+nickname+" Correcto", null);
			this.login.setValidado(true);
			//Si el Usuario es Autenticado se Le Asigna su Rol
			String[] datos = this.loginBean.userRole(nickname, password);
			this.login.setRole(datos[0]);
			if(datos[1].equals("")){
				this.login.setIdConciliador(null);
			}else{
				this.login.setIdConciliador(Long.valueOf(datos[1]));
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario: "+nickname+" Incorrecto", null);
			this.login.setValidado(false);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public String entrar(){
		if(this.login.isValidado()){
			if (this.login.getRole().equalsIgnoreCase("conalbos")) {
				return "/modulos/solicitudes/listasolicitudes?faces-redirect=true";
			}else{
				return "/modulos/audiencia/listaaudiencias?faces-redirect=true";
			}
		}else{
			return "login"; 
		}
	}
	

	public void redirect(){
		if(!this.login.isValidado()){
			try {
				System.out.print(this.login.getRole());
				FacesContext.getCurrentInstance().getExternalContext().redirect("/Madiba/login.jsf");
			} catch (IOException e) {
				System.out.print("redirect");
			}
		}
	}
	
	public void redirectConciliador(){
		try {
			if(this.login.isValidado()){
				if(!this.login.getRole().equalsIgnoreCase("Conciliador")){
					FacesContext.getCurrentInstance().getExternalContext().redirect("/Madiba/accesodenegado.jsf");
				}
			}else{
				FacesContext.getCurrentInstance().getExternalContext().redirect("/Madiba/login.jsf");
			}
		} catch (IOException e) {
			System.out.print("redirectConciliador");
		}
	}
	
	/**
	 * 
	 */
	public void redirectConalbos(){
		try {
			if(this.login.isValidado()){
				if(!this.login.getRole().equalsIgnoreCase("Conalbos")){
					FacesContext.getCurrentInstance().getExternalContext().redirect("/Madiba/accesodenegado.jsf");
				}
			}else{
				FacesContext.getCurrentInstance().getExternalContext().redirect("/Madiba/login.jsf");
			}
		} catch (IOException e) {
			System.out.print("redirectConalbos");
		}
	}
	
	public void logout(){
		try {
			//Object session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			//HttpSession httpSession = (HttpSession)session;
			//httpSession.invalidate();
			this.login.setValidado(false);
			//this.login.setRole(null);
			//this.login.setIdConciliador(null);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/Madiba/login.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String hash(String clear) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
	        byte[] b = md.digest(clear.getBytes()); 
	        int size = b.length;
	        StringBuffer h = new StringBuffer(size); 
	        for (int i = 0; i < size; i++) { 
	            int u = b[i]&255; // unsigned conversion 
	            if (u<16) { 
	                h.append("0"+Integer.toHexString(u)); 
	            } else { 
	                h.append(Integer.toHexString(u)); 
	            } 
	        } 
	        return h.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return null; 
    } 

    public String MD5(String palabra) { 
        String pe = ""; 
        try { 
            pe = hash(palabra); 
        } catch (Exception e) { 
            throw new Error("Error: Al encriptar el password");     
        } 
        return pe; 
    }

}
