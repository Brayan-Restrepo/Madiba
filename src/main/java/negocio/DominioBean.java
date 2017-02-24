package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.DominioDAO;

@Stateless
public class DominioBean implements iDominioBean {

	@Inject
	DominioDAO dominioDAO;

	@Override
	public List<String> listaDominio(String tipo) {
		return this.dominioDAO.listaDominio(tipo);
	}

	@Override
	public String nombreDominio(int id) {
		return this.dominioDAO.findName(id);
	}

}
