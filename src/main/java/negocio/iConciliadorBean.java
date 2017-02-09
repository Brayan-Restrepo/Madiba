package negocio;

import java.util.List;

import javax.ejb.Remote;

import entidades.Conciliador;

@Remote
public interface iConciliadorBean {
	
	public List<Conciliador> allConciliador();
	public String consultarConciliador(); 
}
