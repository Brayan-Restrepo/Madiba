package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControllerSession {
	
	/**
	 * El id de la solicitud solo Utilizar en el desarrollo de Audiencia
	 */
	private Long idSolicitudDesarrolloAudiencia;
	
	/**
	 * Id de la Solicitud para ver el detalle de esa Audiencia
	 */
	private Long idSolicitudAudienciaDetalle;
	
	public ControllerSession(){}

	public Long getIdSolicitudDesarrolloAudiencia() {
		return idSolicitudDesarrolloAudiencia;
	}

	public void setIdSolicitudDesarrolloAudiencia(Long idSolicitudDesarrolloAudiencia) {
		this.idSolicitudDesarrolloAudiencia = idSolicitudDesarrolloAudiencia;
	}

	public Long getIdSolicitudAudienciaDetalle() {
		return idSolicitudAudienciaDetalle;
	}

	public void setIdSolicitudAudienciaDetalle(Long idSolicitudAudienciaDetalle) {
		this.idSolicitudAudienciaDetalle = idSolicitudAudienciaDetalle;
	}
	

	
}
