package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;
import java.util.ArrayList;

@ManagedBean
public class ControllerListaConciliadores {

	@ManagedProperty(value = "#{modelFichaConciliador}")
	private ModelListaConciliadores fichaConciliador;

	public ControllerListaConciliadores() {
	}

	public ModelListaConciliadores getConciliador() {
		return fichaConciliador;
	}

	public void setConciliador(ModelListaConciliadores fichaConciliador) {
		this.fichaConciliador = fichaConciliador;
	}


}
