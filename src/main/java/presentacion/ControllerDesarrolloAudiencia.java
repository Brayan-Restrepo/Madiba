package presentacion;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Audiencia;
import entidades.Parte;
import negocio.iAudienciaBean;


@ManagedBean
@ViewScoped
public class ControllerDesarrolloAudiencia {
	
	public ControllerDesarrolloAudiencia(){	}

	@EJB
	public iAudienciaBean audienciaBean;
	
	@ManagedProperty(value = "#{modelDesarrolloAudiencia}")
	private ModelDesarrolloAudiencia modelDesarrolloAudiencia;
		
	public ModelDesarrolloAudiencia getModelDesarrolloAudiencia() {
		return modelDesarrolloAudiencia;
	}

	public void setModelDesarrolloAudiencia(ModelDesarrolloAudiencia modelDesarrolloAudiencia) {
		this.modelDesarrolloAudiencia = modelDesarrolloAudiencia;
	}

	public void cargarDatosDesarrolloAudiencia(Long idAudiencia){
		Audiencia audiencia = this.audienciaBean.findAudienciaResultadoAsistenia(idAudiencia);
		
		List<String[]> listParte = new ArrayList<String[]>();
		for(int i=0;i<audiencia.getAsistencias().size();i++){
			String[] asistenciaVector = new String[3];
			asistenciaVector[0]=audiencia.getAsistencias().get(i).getParte().getTipoParte();
			asistenciaVector[1]=audiencia.getAsistencias().get(i).getParte().getApellidos()+" "+audiencia.getAsistencias().get(i).getParte().getNombres();
			asistenciaVector[2]=String.valueOf(audiencia.getAsistencias().get(i).getAsistio());
			listParte.add(asistenciaVector);
		}
		this.modelDesarrolloAudiencia.setAsistencia(listParte);
		
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
}
