package presentacion;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Audiencia;
import entidades.Parte;
import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iSolicitudBean;


@ManagedBean
@ViewScoped
public class ControllerDesarrolloAudiencia {
	
	public ControllerDesarrolloAudiencia(){	}

	@EJB
	public iAudienciaBean audienciaBean;
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	@ManagedProperty(value = "#{modelDesarrolloAudiencia}")
	private ModelDesarrolloAudiencia modelDesarrolloAudiencia;

	@ManagedProperty(value = "#{modelLogin}")
	private ModelLogin modelLogin;
	
	public ModelLogin getModelLogin() {
		return modelLogin;
	}

	public void setModelLogin(ModelLogin modelLogin) {
		this.modelLogin = modelLogin;
	}

	public ModelDesarrolloAudiencia getModelDesarrolloAudiencia() {
		return modelDesarrolloAudiencia;
	}

	public void setModelDesarrolloAudiencia(ModelDesarrolloAudiencia modelDesarrolloAudiencia) {
		this.modelDesarrolloAudiencia = modelDesarrolloAudiencia;
	}

	public void cargarAsistencia(Long idAudiencia){
		Audiencia audiencia = this.audienciaBean.findAudienciaResultadoAsistenia(idAudiencia);
		List<String[]> listParte = new ArrayList<String[]>();
		for(int i=0;i<audiencia.getAsistencias().size();i++){
			String[] asistenciaVector = new String[5];
			asistenciaVector[0]=audiencia.getAsistencias().get(i).getParte().getTipoParte();
			asistenciaVector[1]=audiencia.getAsistencias().get(i).getParte().getApellidos()+" "+audiencia.getAsistencias().get(i).getParte().getNombres();
			asistenciaVector[2]=String.valueOf(audiencia.getAsistencias().get(i).getAsistio());
			asistenciaVector[3]=String.valueOf(audiencia.getAsistencias().get(i).getParte().getIdParte());
			asistenciaVector[4]=audiencia.getAsistencias().get(i).getExcusa();
			listParte.add(asistenciaVector);
		}
		this.modelDesarrolloAudiencia.setAsistencia(listParte);
		
	}
	public void cargarDatosDesarrolloAudiencia(Long idAudiencia){
		Audiencia audiencia = this.audienciaBean.findAudienciaResultadoAsistenia(idAudiencia);
		this.cargarAsistencia(idAudiencia);
		if(audiencia.getResultados().size()==1){
			this.modelDesarrolloAudiencia.setAcuerdoParcial(false);
			this.modelDesarrolloAudiencia.setObservacion(audiencia.getResultados().get(0).getConclusion());
			this.modelDesarrolloAudiencia.setTipoResultado(audiencia.getResultados().get(0).getTipoResultado());
		}else if(audiencia.getResultados().size()==2){
			this.modelDesarrolloAudiencia.setAcuerdoParcial(true);
			for(int i=0;i<audiencia.getResultados().size();i++){
				if(audiencia.getResultados().get(i).getTipoResultado().equals("NOACUERDO")){
					this.modelDesarrolloAudiencia.setNoAcuerdo(audiencia.getResultados().get(i).getConclusion());
				}else if(audiencia.getResultados().get(i).getTipoResultado().equals("ACUERDO")){
					this.modelDesarrolloAudiencia.setAcuerdo(audiencia.getResultados().get(i).getConclusion());
				}
			}
			
		}
	}
	
	public boolean hayAsistencias(Long idAudiencia){
		Audiencia audiencia = this.audienciaBean.findAudienciaResultadoAsistenia(idAudiencia);
		if(audiencia.getAsistencias().size()==0){
			return false;
		}
		return true;
	}
	
	public boolean bloquearBoton(List<Long> selectSolicitud){
		
		if(selectSolicitud.size()==0){
			return true;
		}else{
			Long id = selectSolicitud.get(0)+0L;
			Solicitud solicitud = this.solicitudBean.findSolicitud(id);
			if(solicitud.getEstado().equals("AUDIENCIA-ENCURSO") ){
				if(solicitud.getAudiencias().get(solicitud.getAudiencias().size()-1).getAsistencias().size()==0 && this.modelLogin.getRole().equals("conciliador")){
					System.out.println("True");
					return true;
				}else{
					System.out.println("False");
					return false;
				}
			}
		}
	
		return true;
	}
}
