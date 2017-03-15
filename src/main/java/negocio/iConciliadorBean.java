package negocio;

import java.util.List;

import javax.ejb.Remote;

import entidades.Conciliador;
import entidades.Reparto;

@Remote
public interface iConciliadorBean {
	
	public List<Conciliador> allConciliador();
	
	public List<Reparto> allReparto();
	public void removeAllReparto();
	public Conciliador reparto(Long idSolicitud, List<Reparto> reparto);
	
	public List<Conciliador> allConciliadorReparto();
	
}
