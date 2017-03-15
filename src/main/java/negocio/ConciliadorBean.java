package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ConciliadorDAO;
import entidades.Conciliador;
import entidades.Reparto;

@Stateless
public class ConciliadorBean implements iConciliadorBean {

	@Inject
	ConciliadorDAO conciliadorDAO;
	
	@Override
	public List<Conciliador> allConciliador() {
		return conciliadorDAO.allConciliador();
	}

	@Override
	public void reparto(Long idSolicitud, List<Reparto> reparto) {
		// TODO Auto-generated method stub
		this.conciliadorDAO.reparto(idSolicitud, reparto);
	}

	@Override
	public void removeAllReparto() {
		// TODO Auto-generated method stub
		List<Reparto> reparto = this.conciliadorDAO.allReparto();
		this.conciliadorDAO.removeAllReparto(reparto);
	}

	@Override
	public List<Reparto> allReparto() {
		// TODO Auto-generated method stub
		return this.conciliadorDAO.allReparto();
	}

}
