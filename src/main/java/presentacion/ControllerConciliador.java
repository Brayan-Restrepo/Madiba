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

@ManagedBean
public class ControllerConciliador {
	
	
	//Constructor: arma la lista de conciliadores solo la primera vez
	public ControllerConciliador() {}
	
	@ManagedProperty(value = "#{modelConciliador}")
	private ModelConciliador modelConciliador;

	@EJB
	public iConciliadorBean conciliadorBean;
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	public ModelConciliador getModelConciliador() {
		return modelConciliador;
	}

	public void setModelConciliador(ModelConciliador modelConciliador) {
		this.modelConciliador = modelConciliador;
	}

	/**
	 * Obtiene toda la lista de conciliadores
	 * @return List de Todos los Conciliadores
	 */
	public List<Conciliador> listaConciliador() {
		return conciliadorBean.allConciliador();		
	}

	public List<Conciliador> listaConciliadorReparto() {
		return conciliadorBean.allConciliadorReparto();		
	}
	
	//Designa un conciliador al caso, sea por solicitud o por reparto
	public void designarConciliador(String conciliador){
		
	}
	
	//Designa el conciliador que este esperando el turno y lo manda al final de la cola
	public void reparto(Solicitud auxSolicitud) {
		List<Reparto> reparto = this.conciliadorBean.allReparto();
		
		String tipoDesignacion = auxSolicitud.getDesignacions().get(auxSolicitud.getDesignacions().size()-1).getTipoDesignacion();
		this.modelConciliador.setTipoDesignacion(tipoDesignacion);
		
		this.conciliadorBean.removeAllReparto();
		this.modelConciliador.setConciliador(this.conciliadorBean.reparto(auxSolicitud.getIdSolicitud(),reparto));
		
		
	}
	
}
