package presentacion;

import java.util.ArrayList;
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
	private String noAcuerdo;
	
	private String tipoResultado = "ACUERDO";
	
	/**
	 * Es el valor del los CheckBox True si asistieron y False si no
	 */
	private List<Long[]> listaAsistencias;
	
	private boolean audiencia;
	
	/**
	 * Variable que define si se debe mostrar componente para acuerdo parcial o no
	 */
	private boolean acuerdoParcial;
	
	private boolean ficha; 

	private List<Long> selectSolicitud; 
	private String statusSelect = "";

	public ModelAudiencia(){
		this.audiencia = false;
		this.acuerdoParcial=false;
		
		this.ficha=true;
		this.selectSolicitud=new ArrayList<Long>();
		//this.valorAsistencias = new Long[2];
	}
	
	public boolean isFicha() {
		return ficha;
	}

	public void setFicha(boolean ficha) {
		this.ficha = ficha;
	}

	public List<Long> getSelectSolicitud() {
		return selectSolicitud;
	}

	public void setSelectSolicitud(List<Long> selectSolicitud) {
		this.selectSolicitud = selectSolicitud;
	}

	public String getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	public String getNoAcuerdo() {
		return noAcuerdo;
	}

	public void setNoAcuerdo(String noAcuerdo) {
		this.noAcuerdo = noAcuerdo;
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
	
	public List<Long[]> getListaAsistencias() {
		return listaAsistencias;
	}

	public void setListaAsistencias(List<Long[]> listaAsistencias) {
		this.listaAsistencias = listaAsistencias;
	}
	
	public void addSelectSolicitud(Long id, String estado) {
		System.out.println("Entre al click select "+id);
		if(!statusSelect.equals(estado)){
			statusSelect = estado;
			this.selectSolicitud = new ArrayList<Long>();
		}
		if(estado.equalsIgnoreCase("AUDIENCIA-CITACION") || estado.equalsIgnoreCase("AUDIENCIA-FINALIZADA")){
			boolean isSelect = false;
			int position = 0;
			
			for(int i=0;i<this.selectSolicitud.size();i++){
				if(this.selectSolicitud.get(i)==id){
					isSelect = true;
					position = i;
					break;
				}
			}
			if(isSelect){
				this.selectSolicitud.remove(position);
			}else{
				this.selectSolicitud.add(id);
			}
			System.out.println(isSelect);
		}
		else{
			if(this.selectSolicitud.size()>0){
				Long idSolicitudLista = this.selectSolicitud.get(0);
				this.selectSolicitud = new ArrayList<Long>();
				if(idSolicitudLista != id){
					this.selectSolicitud.add(id);
				}
			}else{
				this.selectSolicitud = new ArrayList<Long>();
				this.selectSolicitud.add(id);
				statusSelect = estado;
			}
		}
		System.out.println(	this.selectSolicitud.toString());
	}
	
}
