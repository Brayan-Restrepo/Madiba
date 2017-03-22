package presentacion;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class ModelDesarrolloAudiencia {
	
	public ModelDesarrolloAudiencia(){
		this.acuerdoParcial=false;
	}
	
	/**
	 * Lista de Asistencia
	 * un vector de tipo String 
	 * La primera pocicion es tipoParte
	 * La Segunda Pocicion Apellido y Nombre
	 * La Tercera Posicion es la Asistencia
	 * La Cuarta Posicion es la IdParte
	 * La Quinta Posicion es la Excusa
	 */
	private List<String[]> Asistencia;
	
	private Long idAudiencia;
		
	/**
	 * Depende de Acuerdo Parcial, si Acuerdo Parcial es False se toma en cuenta esto
	 */
	private String observacion;
	
	/**
	 * Depende de Acuerdo Parcial, si Acuerdo Parcial es true se toma en cuenta esto
	 */
	private String acuerdo;

	/**
	 * Depende de Acuerdo Parcial, si Acuerdo Parcial es true se toma en cuenta esto
	 */
	private String noAcuerdo;
	
	private String tipoResultado;
	
	/**
	 * Es el valor del los CheckBox True si asistieron y False si no
	 */
	private List<Long[]> listaAsistencias;
	
	//private boolean audiencia;
	
	/**
	 * Variable que define si se debe mostrar componente para acuerdo parcial o no
	 */
	private boolean acuerdoParcial;
	
	
	
	public List<Long[]> getListaAsistencias() {
		return listaAsistencias;
	}

	public void setListaAsistencias(List<Long[]> listaAsistencias) {
		this.listaAsistencias = listaAsistencias;
	}

	public List<String[]> getAsistencia() {
		return Asistencia;
	}

	public void setAsistencia(List<String[]> asistencia) {
		Asistencia = asistencia;
	}

	public String getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(String tipoResultado) {
		if(this.tipoResultado == tipoResultado){
			this.tipoResultado = "";
		}else{
			this.tipoResultado = tipoResultado;
		}
	}

	public Long getIdAudiencia() {
		return idAudiencia;
	}

	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	public boolean isAcuerdoParcial() {
		return acuerdoParcial;
	}

	public void setAcuerdoParcial(boolean acuerdoParcial) {
		this.acuerdoParcial = acuerdoParcial;
	}

	public String getNoAcuerdo() {
		return noAcuerdo;
	}

	public void setNoAcuerdo(String noAcuerdo) {
		this.noAcuerdo = noAcuerdo;
	}

	public String getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
