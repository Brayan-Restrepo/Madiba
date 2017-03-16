package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ModelDesarrolloAudiencia {
	
	public ModelDesarrolloAudiencia(){}
	
	private Long idAudiencia;
	
	private boolean acuerdoParcial;
	
	private String tipoResultado;
	private String noAcuerdo;
	private String acuerdo;
	private String observacion;
	
	public String getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
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
