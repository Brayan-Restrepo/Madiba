package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ConciliadorDAO;
import entidades.Conciliador;

@Stateless
public class ConciliadorBean implements iConciliadorBean {

	@Inject
	ConciliadorDAO conciliadorDAO;
	
	public String consultarConciliador() {
		Conciliador conciliadorDB = conciliadorDAO.consultarConciliador(1);
		
		return conciliadorDB.getApellidos();
	}

}
