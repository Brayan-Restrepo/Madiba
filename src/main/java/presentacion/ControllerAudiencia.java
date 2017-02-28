package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Solicitud;
import negocio.iSolicitudBean;

@ManagedBean
@ViewScoped
public class ControllerAudiencia {

	
	public ControllerAudiencia() {}
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	@ManagedProperty(value = "#{modelAudiencia}")
	private ModelAudiencia modelAudiencia;
	
	private Solicitud solicitud;
	
	
	
	public ModelAudiencia getModelAudiencia() {
		return modelAudiencia;
	}

	public void setModelAudiencia(ModelAudiencia modelAudiencia) {
		this.modelAudiencia = modelAudiencia;
	}

	/**
	 * Consulta en la DB la Solicitud de la Audiencia
	 * @param id -> idSolicitud
	 * @return Solicitud
	 */
	public Solicitud findSolicitud(Long id){
		this.solicitud = this.solicitudBean.findSolicitud(id);
		return this.solicitud;
	}
	
	public String addResultado(){
		System.out.println(this.modelAudiencia.getObservacion());
		this.solicitudBean.addResultado("---", this.solicitud, "-----");
		return "listaaudiencias";
	}
}
