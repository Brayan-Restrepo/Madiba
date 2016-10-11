package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
public class ControllerSolicitud {

	//Permite validar que los datos solo se pinten la primera vez que se carga la pagina y no todas la veces
	private static boolean inicio = true;
	
	//Contiene la lista de solicitudes con datos quemados
	public static List<ModelSolicitud> listaSolicitud = new ArrayList<ModelSolicitud>();
	
	//Constructor: arma la lista de solicitudes solo la primera vez
	public ControllerSolicitud(){
		if(ControllerSolicitud.inicio){
			ControllerSolicitud.inicio = false;
			
			//Arma la lista de conciliadores
			String[] convocante = {"Luis Alberto Rodríguez"}; //Tener en cuenta que al añadir mas, se desconfigura la vista
			String[] convocado = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					1,"29/09/2015",convocante,convocado,"Por Reparto","Breve descripcion...","Radicar"));
			String[] convocante2 = {"Luis Alberto Rodríguez"};
			String[] convocado2 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					2,"28/09/2014",convocante2,convocado2,"2345673409","Breve descripcion...","Radicar"));
			String[] convocante3 = {"Luis Alberto Rodríguez"};
			String[] convocado3 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					3,"30/09/2016",convocante3,convocado3,"87655543","Breve descripcion...","Liquidar"));
			String[] convocante4 = {"Luis Alberto Rodríguez"};
			String[] convocado4 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					4,"03/10/2016",convocante4,convocado4,"Por Reparto","Breve descripcion...","Liquidar"));
			String[] convocante5 = {"Luis Alberto Rodríguez"};
			String[] convocado5 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					5,"06/10/2016",convocante5,convocado5,"Por Reparto","Breve descripcion...","Radicar"));
			String[] convocante6 = {"Luis Alberto Rodríguez"};
			String[] convocado6 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					6,"06/10/2016",convocante6,convocado6,"Por Reparto","Breve descripcion...","Audiencia"));
		}
	}

	public List<ModelSolicitud> getListaSolicitud() {
		return ControllerSolicitud.listaSolicitud;
	}
	
	//Filtra las solicitudes por su estado
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

	//Cambia el estado de la solicitud despues de ser gestionada
	public void cambiarEstado(int id){
		String[] estados = {"Liquidar","Radicar","Audiencia","Registrar","Finalizado"};
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getId() == id){
				String estadoActual = ControllerSolicitud.listaSolicitud.get(i).getEstado();
				String nuevoEstado = "";
				for(int j=0; j<estados.length; j++){
					if(estados[j].equals(estadoActual)){
						nuevoEstado = estados[j+1];
					}
				}
				ControllerSolicitud.listaSolicitud.get(i).setEstado(nuevoEstado);
				//Designa el conciliador y actualiza la lista
				if(estadoActual.equals("Radicar")){
					ControllerConciliador conciliador = new ControllerConciliador();
					conciliador.designarConciliador(ControllerSolicitud.listaSolicitud.get(i).getConciliador());
				}
			}			
		}
	}
	
	//Define el color de la ficha segun su estado
	public String colorFicha(int id, String tipoComponente){
		String[][] matrizColores = {{"Liquidar","info"}, //Azul claro
							  {"Radicar","primary"}, //Azul rey
							  {"Audiencia","warning"}, //Naranja
							  {"Registrar","success"}, //Verde 
							  {"Finalizado","default"}}; //Gris
		String color = "";
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getId() == id){
				String estadoActual = ControllerSolicitud.listaSolicitud.get(i).getEstado();
				for(int j=0; j<matrizColores.length; j++){
					if(matrizColores[j][0].equals(estadoActual)){
						color = tipoComponente+"-"+matrizColores[j][1];
					}
				}
			}			
		}
		return color;
	}
}
