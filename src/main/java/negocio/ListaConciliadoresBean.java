package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ListaConciliadoresDAO;

@Stateless
public class ListaConciliadoresBean implements iListaConciliadoresBean{

	@Inject
	ListaConciliadoresDAO listaConciliadoresDAO;
}
