package presentacion;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ModelPago {
	
	private int solicitud;
	private String banco;
	private String formaPago;
	private String cuantia;
	private String referencia;
	
	public ModelPago(){
		
	}
		
	public ModelPago(int solicitud, String banco, String formaPago, String cuantia, String referencia) {
		this.solicitud = solicitud;
		this.banco = banco;
		this.formaPago = formaPago;
		this.cuantia = cuantia;
		this.referencia = referencia;
	}



	public int getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(int solicitud) {
		this.solicitud = solicitud;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getCuantia() {
		return cuantia;
	}
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
}
