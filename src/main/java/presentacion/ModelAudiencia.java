package presentacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ModelAudiencia {

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
	private String noAcuerd;
	
	private String tipoResultado = "ACUERDO";
	
	/**
	 * Es el valor del los CheckBox True si asistieron y False si no
	 */
	private Long[] valorAsistencias;
	private List<Long[]> listaAsistencias;
	
	private boolean audiencia;
	
	/**
	 * Variable que define si se debe mostrar componente para acuerdo parcial o no
	 */
	private boolean acuerdoParcial;
	
	public String getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	public String getNoAcuerd() {
		return noAcuerd;
	}

	public void setNoAcuerd(String noAcuerd) {
		this.noAcuerd = noAcuerd;
	}

	public ModelAudiencia(){
		this.audiencia = false;
		this.acuerdoParcial=false;
		this.valorAsistencias = new Long[2];
	}
	
	public boolean isAcuerdoParcial() {
		return acuerdoParcial;
	}

	public void setAcuerdoParcial(boolean acuerdoParcial) {
		this.acuerdoParcial = acuerdoParcial;
	}

	public String getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

	public boolean isAudiencia() {
		return audiencia;
	}

	public void setAudiencia(boolean audiencia) {
		this.audiencia = audiencia;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Long[] getValorAsistencias() {
		return valorAsistencias;
	}

	public void setValorAsistencias(Long[] valorAsistencias) {
		this.valorAsistencias = valorAsistencias;
	}

	public List<Long[]> getListaAsistencias() {
		return listaAsistencias;
	}

	public void setListaAsistencias(List<Long[]> listaAsistencias) {
		this.listaAsistencias = listaAsistencias;
	}
	
}
