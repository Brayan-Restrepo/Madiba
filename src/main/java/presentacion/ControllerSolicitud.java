package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ControllerSolicitud {
	
	private static boolean inicio = true;
	public static List<ModelSolicitud> listaSolicitud = new ArrayList<ModelSolicitud>();
	
	public ControllerSolicitud(){
		if(ControllerSolicitud.inicio){
			String[] convocante = {"Luis Alberto Rodríguez", "Jaime Rodríguez"};
			String[] convocado = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("29/09/2016",convocante,convocado,"Por Reparto","Breve descripcion...","t"));
			String[] convocante2 = {"Luis Alberto Rodríguez", "Jaime Rodríguez"};
			String[] convocado2 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("28/09/2016",convocante2,convocado2,"2345673409","Breve descripcion...","t"));
			String[] convocante3 = {"Luis Alberto Rodríguez", "Jaime Rodríguez"};
			String[] convocado3 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("30/09/2016",convocante3,convocado3,"87655543","Breve descripcion...","t"));
			String[] convocante4 = {"Luis Alberto Rodríguez", "Jaime Rodríguez"};
			String[] convocado4 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("03/10/2016",convocante4,convocado4,"Por Reparto","Breve descripcion...","t"));
			String[] convocante5 = {"Luis Alberto Rodríguez", "Jaime Rodríguez"};
			String[] convocado5 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("06/10/2016",convocante5,convocado5,"Por Reparto","Breve descripcion...","t"));
		}
	}

	public static List<ModelSolicitud> getListaSolicitud() {
		return listaSolicitud;
	}
	
}
