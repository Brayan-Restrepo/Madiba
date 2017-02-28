package presentacion;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ModelAudiencia {
	
	private String observacion;
	
	/**
	 * Es el valor del los CheckBox True si asistieron y False si no
	 */
	private List<String> listaAsistencias;
	
	private boolean audiencia;
	
	public ModelAudiencia(){
		this.audiencia = false;
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
	
	/**
	 * Lista de Aistencias
	 * @return lista de asistencia
	 */
	public List<String> getListaAsistencias() {
		return listaAsistencias;
	}

	/**
	 * Lista de Asistencias
	 * @param listaAsistencias
	 */
	public void setListaAsistencias(List<String> listaAsistencias) {
		this.listaAsistencias = listaAsistencias;
	}
}
