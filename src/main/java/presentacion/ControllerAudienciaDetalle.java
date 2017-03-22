package presentacion;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iSolicitudBean;

@ManagedBean
@ViewScoped
public class ControllerAudienciaDetalle {

	public ControllerAudienciaDetalle(){}
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	@EJB
	public iAudienciaBean audienciaBean;
	
	private Solicitud solicitud;
	
	private Long idAudienciaSelect;
	
	public long getIdAudienciaSelect() {
		return idAudienciaSelect;
	}

	public void setIdAudienciaSelect(Long idAudienciaSelect) {
		if(this.idAudienciaSelect==idAudienciaSelect){
			this.idAudienciaSelect = null;
		}else{
			this.idAudienciaSelect = idAudienciaSelect;
		}
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	/**
	 * Obtine todas Las Audiencias de una Solicitud
	 * @param id idSolicitud
	 */
	public void findSolicitud(Long idSolicitud){
		this.solicitud = this.solicitudBean.findSolicitud(idSolicitud);
	}
	
	/**
	 * Cuando Selecciona una Audiencia en la tabla o en la Ficha
	 * retorna una clase que pinta el fondo del componente de un color
	 * @param idAudiencia
	 * @return Clase del componente que pinta el fondo
	 */
	public String seleccionarAudiencia(Long idAudiencia){
		if(this.idAudienciaSelect==idAudiencia){
			return "seleccionar-solicitud-warning";
		}else{
			return "";
		}
	}
	
	/**
	 * Bloque el boton desarrollo Audiencia en la Fentana detalle 
	 * Audiencia Hasta que seleccione Alguna Audiencia
	 * @return
	 */
	public boolean bloquearBotonDesarrolloAudiencia(){
		if(this.idAudienciaSelect==null){
			return true;
		}else{
			return false;
		}	
	}
	
	public String changeIconSelect(Long idAudiencia){
		
			if(this.idAudienciaSelect==idAudiencia){
				return "fa-check";
			}
		
		return "fa-square";
	}
	

	public boolean verificarAsistencias(Long idAudiencia){
		return this.audienciaBean.verificarAsistencias(idAudiencia);
	}
}
