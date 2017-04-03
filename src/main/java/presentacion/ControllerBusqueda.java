package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class ControllerBusqueda {
		
	@ManagedProperty(value="#{modelBusqueda}")
	private ModelBusqueda busqueda;

	public ControllerBusqueda(){}

	public ModelBusqueda getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(ModelBusqueda busqueda) {
		this.busqueda = busqueda;
	}

	
}
