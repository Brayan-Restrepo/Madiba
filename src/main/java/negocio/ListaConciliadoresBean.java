package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ListaConciliadoresDAO;

@Stateless
public class ListaConciliadoresBean implements iListaConciliadoresBean{

	@Inject
	ListaConciliadoresDAO listaConciliadoresDAO;
	
	public ListaConciliadoresBean(){
		
	}
	
	public List listaConciliadores(){
		return listaConciliadoresDAO.listaConciliadores();
	}
	
	public List listaReparto(){
		return listaConciliadoresDAO.listaReparto();
	}
}
