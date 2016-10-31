package presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import negocio.iListaConciliadoresBean;

@ManagedBean
public class ControllerListaConciliadores {

	@ManagedProperty(value = "#{modelListaConciliadores}")
	private ModelListaConciliadores listaConciliadores;
	
	@EJB
	iListaConciliadoresBean listaConciliadoresBean;

	public ControllerListaConciliadores() {
	}

	public ModelListaConciliadores getListaConciliadores() {
		return listaConciliadores;
	}

	public void setListaConciliadores(ModelListaConciliadores listaConciliadores) {
		this.listaConciliadores = listaConciliadores;
	}
	
	public List listaConciliadores(){
		return listaConciliadoresBean.listaConciliadores();
	}

	public List listaReparto(){
		return listaConciliadoresBean.listaReparto();
	}
}
