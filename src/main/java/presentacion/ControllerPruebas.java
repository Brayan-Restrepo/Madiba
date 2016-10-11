package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
public class ControllerPruebas {

	public ControllerPruebas(){
	}


	//Pruebas de Ajax en boton
	public String cambiarEstado(AjaxBehaviorEvent evento){
		return "hola";
	}
}
