package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iSolicitudBean;

@ManagedBean
@ViewScoped
public class ControllerAudiencia {

	
	public ControllerAudiencia() {}
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	@EJB
	public iAudienciaBean audienciaBean;
	
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
	
	/**
	 * 	
	 * @param estdoAudiencia -> FINALIZADA, APLAZADA, SUSPENDIDA
	 */
	public void addResultado(String estdoAudiencia){
		System.out.println(this.modelAudiencia.getObservacion());
		System.out.println(this.modelAudiencia.getTipoResultado());
		System.out.println(this.modelAudiencia.getNoAcuerd());
		System.out.println(this.modelAudiencia.getAcuerdo());
		this.audienciaBean.addResultado("---", this.solicitud, "-----");
		//return "listaaudiencias";
	}
	
	public void addAsistencia(){
		int size = this.solicitud.getAudiencias().size();
		Long idAudiencia = this.solicitud.getAudiencias().get(size-1).getIdAudiencia();
		//this.solicitudBean.addAsistencia(idAudiencia, 	);
	}
}
