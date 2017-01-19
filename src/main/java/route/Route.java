package route;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Route {
	
	private String ruta;
	
	public Route() {
		this.ruta = "modulos/conciliadores/listaconciliadores";
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	
	
}
