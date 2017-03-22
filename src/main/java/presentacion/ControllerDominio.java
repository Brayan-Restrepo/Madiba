package presentacion;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import negocio.iDominioBean;



@ManagedBean
public class ControllerDominio {

	@EJB
	public iDominioBean dominioBean;

	
	/**
	 * Constructor: arma la lista de solicitudes solo la primera vez
	 */
	public ControllerDominio(){

	}
	
	/**
	 * Develve lista con los nombres de los campos que pertenecen al tipo 
	 * @param tipo
	 * @return
	 */
	public List<String> listaDominio(String tipo){
		return this.dominioBean.listaDominio(tipo);
	}
	
	/**
	 * Develve el nombre del campo a mostrar 
	 * @param id
	 * @return
	 */
	public String nombreDominio(int id){
		return this.dominioBean.nombreDominio(id);
	}
		
}
