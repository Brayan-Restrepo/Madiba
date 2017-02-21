package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ConciliadorDAO;
import entidades.Conciliador;

@Stateless
public class ConciliadorBean implements iConciliadorBean {

	@Inject
	ConciliadorDAO conciliadorDAO;
	
	@Override
	public List<Conciliador> allConciliador() {
		return conciliadorDAO.allConciliador();
	}

}
