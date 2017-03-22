package presentacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Audiencia;
import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iSolicitudBean;

@ManagedBean
@ViewScoped
public class ControllerAudiencia {

	
	public ControllerAudiencia() {

		this.coloresEstado = new HashMap<String, String>();
		this.coloresEstado.put("GRABADA", "info");
		this.coloresEstado.put("PAGADA", "primary");
		this.coloresEstado.put("RADICADA", "warning");
		this.coloresEstado.put("DESIGNACION", "success");
		this.coloresEstado.put("AUDIENCIA-CITACION", "info");
		this.coloresEstado.put("AUDIENCIA-PENDIENTE", "primary");
		this.coloresEstado.put("AUDIENCIA-ENCURSO", "warning");
		this.coloresEstado.put("AUDIENCIA-FINALIZADA", "success");
		this.coloresEstado.put("REGISTRADA", "black");
	}
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	@EJB
	public iAudienciaBean audienciaBean;
	
	@ManagedProperty(value = "#{modelAudiencia}")
	private ModelAudiencia modelAudiencia;
	
	private Map<String, String> coloresEstado;
	
	private Solicitud solicitud;

	public ModelAudiencia getModelAudiencia() {
		return modelAudiencia;
	}

	public void setModelAudiencia(ModelAudiencia modelAudiencia) {
		this.modelAudiencia = modelAudiencia;
	}
	
	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	/**
	 * Consulta en la DB la Solicitud de la Audiencia
	 * @param id -> idSolicitud
	 * @return Solicitud
	 */
	public void findSolicitud(Long id){
		this.solicitud = this.solicitudBean.findSolicitud(id);
	}
	

	
	

	public void aplazarAudiencia(){
				
		Long id = this.modelAudiencia.getSelectSolicitud().get(0)+0L;
		List<Audiencia> audiencia = this.solicitudBean.findSolicitud(id).getAudiencias();
		Long idAudiencia = audiencia.get(audiencia.size()-1).getIdAudiencia();
		
		this.audienciaBean.actualizarEstadoAudiencia(idAudiencia, "APLAZADA");
		this.solicitudBean.actualizarEstadoSolicitud(id, "DESIGNACION");
	}
	
	
	
	
	public String seleccionarSolicitud(Long idSolicitud,String estado){
		for(int i=0;i<this.modelAudiencia.getSelectSolicitud().size();i++){
			if(this.modelAudiencia.getSelectSolicitud().get(i)==idSolicitud){
				return "seleccionar-solicitud-"+this.coloresEstado.get(estado);
			}
		}
		return "";
	}
	
	
	public String changeIconSelect(Long idSolicitud){
		
		for(int i=0;i<this.modelAudiencia.getSelectSolicitud().size();i++){
			if(this.modelAudiencia.getSelectSolicitud().get(i)==idSolicitud){
				return "fa-check";
			}
		}
		
		return "fa-square";
	}
	
	public boolean bloquearBoton(String estado1, String estado2){
		
		if(this.modelAudiencia.getSelectSolicitud().size()==0){
			return true;
		}else{
			Long id = this.modelAudiencia.getSelectSolicitud().get(0)+0L;
			String estadoSolicitud = this.solicitudBean.findSolicitud(id).getEstado();
			if(estadoSolicitud.equals(estado1) || estadoSolicitud.equals(estado2) ){
				return false;
			}
		}
	
		return true;
	}
	
	public boolean bloquearBotonAplazar(String estado1, String estado2){
		
		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			Long id = this.modelAudiencia.getSelectSolicitud().get(0)+0L;
			String estadoSolicitud = this.solicitudBean.findSolicitud(id).getEstado();
			if(estadoSolicitud.equals(estado1) || estadoSolicitud.equals(estado2) ){
				return false;
			}
		}
	
		return true;
	}	
}
