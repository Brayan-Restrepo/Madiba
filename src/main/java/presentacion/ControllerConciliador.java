package presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import entidades.Conciliador;
import entidades.Reparto;
import entidades.Solicitud;
import negocio.iConciliadorBean;
import negocio.iSolicitudBean;

import java.util.List;
import java.util.ArrayList;

@ManagedBean
public class ControllerConciliador {
	
	@ManagedProperty(value = "#{modelConciliador}")
	private ModelConciliador conciliador;

	//Permite validar que los datos solo se pinten la primera vez que se carga la pagina y no todas la veces
	private static boolean inicio = true;
	
	//Contiene la lista de conciliadores con datos quemados
	public static List<ModelConciliador> listaConciliador = new ArrayList<ModelConciliador>();

	//Constructor: arma la lista de conciliadores solo la primera vez
	public ControllerConciliador() {
		
	}
	
	@EJB
	public iConciliadorBean conciliadorBean;
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	
	public ModelConciliador getConciliador() {
		return conciliador;
	}

	public void setConciliador(ModelConciliador conciliador) {
		this.conciliador = conciliador;
	}

	/**
	 * Obtiene toda la lista de conciliadores
	 * @return List de Todos los Conciliadores
	 */
	public List<Conciliador> listaConciliador() {
		return conciliadorBean.allConciliador();		
	}

	//Designa un conciliador al caso, sea por solicitud o por reparto
	public void designarConciliador(String conciliador){
		if(conciliador.equals("Por Reparto")){
			//reparto();
		}else{
			solicitado(conciliador);
		}
	}
	
	//Designa el conciliador que este esperando el turno y lo manda al final de la cola
	public void reparto(Long idSolicitud) {
		List<Reparto> reparto = this.conciliadorBean.allReparto();
		Solicitud solicitudDesignacion = this.solicitudBean.findSolicitud(idSolicitud);
		
		String tipoDesignacion = solicitudDesignacion.getDesignacions().get(solicitudDesignacion.getDesignacions().size()-1).getTipoDesignacion();
		
		if(tipoDesignacion.equals("Reparto")){
			this.conciliadorBean.removeAllReparto();
			this.conciliadorBean.reparto(idSolicitud,reparto);
		}
		
	}
	
	//Designa el conciliador que fue seleccionado y lo manda al final de la cola
	public void solicitado(String cedula) {
		//Buscar el conciliador y lo manda a la cola
		int size = ControllerConciliador.listaConciliador.size();
		for (int i = 0; i < size; i++) {
			if(ControllerConciliador.listaConciliador.get(i).getCedula().equals(cedula)){
				ModelConciliador mCon = ControllerConciliador.listaConciliador.get(i);
				ControllerConciliador.listaConciliador.remove(i);
				ControllerConciliador.listaConciliador.add(mCon);
			}
		}
		
		//Asigna los nuevos turnos
		for (int i = 0; i < size; i++) {
			ControllerConciliador.listaConciliador.get(i).setId(i + 1);
		}
	}
	
	//Busca el nombre del conciliador por su cedula
	public String buscarConciliador(String cedula){
		String nombre = "";
		if(cedula.equals("Por Reparto")){
			return "Por Reparto";
		}
		int size = ControllerConciliador.listaConciliador.size();
		for(int i=0; i<size; i++){
			if(ControllerConciliador.listaConciliador.get(i).getCedula().equals(cedula)){
				//nombre = ControllerConciliador.listaConciliador.get(i).getApellido()+" "+ControllerConciliador.listaConciliador.get(i).getNombre();
				nombre = ControllerConciliador.listaConciliador.get(i).getNombre();
				break;
			}
		}
		return nombre;
	}
	
}
