package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ListaConciliadoresDAO;
import entidades.Conciliador;

@Stateless
public class ListaConciliadoresBean implements iListaConciliadoresBean{

	@Inject
	ListaConciliadoresDAO listaConciliadoresDAO;
	
	public ListaConciliadoresBean(){
		
	}
	
	public String buscarConciliador(int id_conciliador){
		String apellido = "";
		Conciliador conciliadorBD = listaConciliadoresDAO.consultarConciliador(id_conciliador);
		if(conciliadorBD != null){
			apellido = conciliadorBD.getApellidos();
		}
		return apellido;
	}
}
