package presentacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import entidades.Audiencia;
import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iSolicitudBean;

@ManagedBean
@SessionScoped
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
		List<Long[]> listaAsistencias= new ArrayList<Long[]>();
		for(int i=0;i<this.solicitud.getPartes().size();i++){
			Long[] valAsistencia= new Long[2];
			valAsistencia[0] = this.solicitud.getPartes().get(i).getIdParte();
			valAsistencia[1] = 0L;
			listaAsistencias.add(valAsistencia);
		}
		this.modelAudiencia.setListaAsistencias(listaAsistencias);
	}
	
	/**
	 * 	
	 * @param estdoAudiencia -> FINALIZADA, APLAZADA, SUSPENDIDA
	 */
	public void addResultado(String estdoAudiencia){
		
		String tipoResultado = this.modelAudiencia.getTipoResultado();
		String observacion = this.modelAudiencia.getObservacion();
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;
		if((observacion!="" && observacion!=null) && tipoResultado!=null && tipoResultado!=""){
			if(estdoAudiencia.equals("FINALIZADA")){
				
				this.audienciaBean.addResultado(tipoResultado, this.solicitud.getAudiencias().get(lastAudiencia), observacion, this.solicitud.getIdSolicitud());
				
			}
		}
		//return "listaaudiencias";
	}
	
	public void suspenderAudiencia(){
		this.solicitudBean.actualizarEstadoSolicitud(this.solicitud.getIdSolicitud(), "DESIGNACION");
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;
		this.audienciaBean.actualizarEstadoAudiencia(this.solicitud.getAudiencias().get(lastAudiencia).getIdAudiencia(), "SUSPENDIDA");
	}
	
	
	public void addAsistencia(Long idParte){
		List<Long[]> listaAsistencias = new ArrayList<Long[]>();
		Long valor = 0L;
		int posicion = 0;
		for(int i=0;i<this.modelAudiencia.getListaAsistencias().size();i++){
			if(this.modelAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelAudiencia.getListaAsistencias().get(i)[1];
				posicion = i;
			}
			listaAsistencias.add(this.modelAudiencia.getListaAsistencias().get(i));
		}
		if(valor==2L){
			listaAsistencias.get(posicion)[1] = 0L;
			System.out.println("Entre 2");
		}else{
			listaAsistencias.get(posicion)[1] = valor+1L;
			System.out.println("Entre 0, 1");
		}
		
		this.modelAudiencia.setListaAsistencias(listaAsistencias);
	}
	
	public String changeIcon(Long idParte){
		String icono="fa-square";
		Long valor = 0L;
		for(int i=0;i<this.modelAudiencia.getListaAsistencias().size();i++){
			if(this.modelAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelAudiencia.getListaAsistencias().get(i)[1];
				break;
			}
		}
		//System.out.println("asdasdasdasdasdasdasds  "+valor);
		if(valor==0){
			icono = "fa-square";
		}
		if(valor==1){
			icono = "fa-check";
		}
		if(valor==2){
			icono = "fa-close";
		}
		return icono;
	}
	
	public String changeColorButton(Long idParte){
		String color="warning";
		Long valor = 0L;
		for(int i=0;i<this.modelAudiencia.getListaAsistencias().size();i++){
			if(this.modelAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelAudiencia.getListaAsistencias().get(i)[1];
				break;
			}
		}
		if(valor==0){
			color = "warning";
		}
		if(valor==1){
			color = "success";
		}
		if(valor==2){
			color = "danger";
		}
		return color;
	}
}
