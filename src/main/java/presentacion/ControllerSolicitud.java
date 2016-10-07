package presentacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ControllerSolicitud {
	
	private static boolean inicio = true;
	public static List<ModelSolicitud> listaSolicitud = new ArrayList<ModelSolicitud>();
	
	public ControllerSolicitud(){
		if(ControllerSolicitud.inicio){
			ControllerSolicitud.inicio = false;
			String[] convocante = {"Luis Alberto Rodríguez"}; //Tener en cuenta que al añadir mas, se desconfigura la vista
			String[] convocado = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("29/09/2016",convocante,convocado,"Por Reparto","Breve descripcion...","Radicar"));
			String[] convocante2 = {"Luis Alberto Rodríguez"};
			String[] convocado2 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("28/09/2016",convocante2,convocado2,"2345673409","Breve descripcion...","Radicar"));
			String[] convocante3 = {"Luis Alberto Rodríguez"};
			String[] convocado3 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("30/09/2016",convocante3,convocado3,"87655543","Breve descripcion...","Liquidar"));
			String[] convocante4 = {"Luis Alberto Rodríguez"};
			String[] convocado4 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("03/10/2016",convocante4,convocado4,"Por Reparto","Breve descripcion...","Liquidar"));
			String[] convocante5 = {"Luis Alberto Rodríguez"};
			String[] convocado5 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("06/10/2016",convocante5,convocado5,"Por Reparto","Breve descripcion...","Radicar"));
			String[] convocante6 = {"Luis Alberto Rodríguez"};
			String[] convocado6 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud("06/10/2016",convocante6,convocado6,"Por Reparto","Breve descripcion...","Audiencia"));
		}
	}

	public List<ModelSolicitud> getListaSolicitud() {
		return ControllerSolicitud.listaSolicitud;
	}
	
	public List<ModelSolicitud> solicitudesPorEstado(String estado){
		List<ModelSolicitud> listaPorEstado = new ArrayList<ModelSolicitud>();
		int size = ControllerSolicitud.listaSolicitud.size();
		
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equals(estado)){
				listaPorEstado.add(ControllerSolicitud.listaSolicitud.get(i));
			}			
		}
		
		return listaPorEstado;
	}
}
