package negocio;

import javax.ejb.Remote;

@Remote
public interface iConciliadorBean {
	
	public String consultarConciliador(); 
}
