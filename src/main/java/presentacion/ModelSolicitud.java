package presentacion;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ModelSolicitud {

	private int id;
	private String fecha;
	private String[] convotante;
	private String[] convocado;
	private String conciliador;
	private String descripcion;
	private String estado;
	private String solicitud;
	private String numeroRadicado;
	private String pretenciones;
	
	/**
	 * Audiencia
	 */
	private String numeroAudi;
	private String fechaAudi;
	private String horaAudi;
	private String duracionAudi;
	private String salaAudi;
	
	/*
	 * Pago
	 */
	private String bancoPago;
	private String formaPago;
	private String cuantiaPago;
	private String referenciaPago;
	
	//Hay que quitar esto es solo provicional
	private boolean select; 

	public ModelSolicitud(){}
	
	public ModelSolicitud(int id, String fecha, String[] convotante,String[] convocado, String conciliador, String descripcion,
			String estado, String solicitud, String numeroRadicado, String pretenciones,String bancoPago, String formaPago, 
			String cuantiaPago, String referenciaPago, String numeroAudi, String fechaAudi,	String horaAudi, String duracionAudi, String salaAudi) {
		this.id = id;
		this.fecha = fecha;
		this.convotante = convotante;
		this.convocado = convocado;
		this.conciliador = conciliador;
		this.descripcion = descripcion;
		this.estado = estado;
		this.solicitud = solicitud;
		this.numeroRadicado = numeroRadicado;
		this.select = false;
		this.pretenciones = pretenciones;
		
		//Audiencia
		this.numeroAudi = numeroAudi;
		this.fechaAudi = fechaAudi;
		this.horaAudi = horaAudi;
		this.duracionAudi = duracionAudi;
		this.salaAudi = salaAudi;
		
		//Pago		
		this.bancoPago = bancoPago;
		this.formaPago = formaPago;
		this.cuantiaPago = cuantiaPago;
		this.referenciaPago = referenciaPago;
		
	}
	
	public String getPretenciones() {
		return pretenciones;
	}

	public void setPretenciones(String pretenciones) {
		this.pretenciones = pretenciones;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public String getNumeroRadicado() {
		return numeroRadicado;
	}

	public void setNumeroRadicado(String numeroRadicado) {
		this.numeroRadicado = numeroRadicado;
	}

	public String getSolicitud(){
		return this.solicitud;
	}

	public void setSolicitud(String solicitud){
		this.solicitud = solicitud;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String[] getConvotante() {
		return convotante;
	}
	public void setConvotante(String[] convotante) {
		this.convotante = convotante;
	}
	public String[] getConvocado() {
		return convocado;
	}
	public void setConvocado(String[] convocado) {
		this.convocado = convocado;
	}
	public String getConciliador() {
		return conciliador;
	}
	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroAudi() {
		return numeroAudi;
	}

	public void setNumeroAudi(String numeroAudi) {
		this.numeroAudi = numeroAudi;
	}

	public String getFechaAudi() {
		return fechaAudi;
	}

	public void setFechaAudi(String fechaAudi) {
		this.fechaAudi = fechaAudi;
	}

	public String getHoraAudi() {
		return horaAudi;
	}

	public void setHoraAudi(String horaAudi) {
		this.horaAudi = horaAudi;
	}

	public String getDuracionAudi() {
		return duracionAudi;
	}

	public void setDuracionAudi(String duracionAudi) {
		this.duracionAudi = duracionAudi;
	}

	public String getSalaAudi() {
		return salaAudi;
	}

	public void setSalaAudi(String salaAudi) {
		this.salaAudi = salaAudi;
	}

	public String getBancoPago() {
		return bancoPago;
	}

	public void setBancoPago(String bancoPago) {
		this.bancoPago = bancoPago;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getCuantiaPago() {
		return cuantiaPago;
	}

	public void setCuantiaPago(String cuantiaPago) {
		this.cuantiaPago = cuantiaPago;
	}

	public String getReferenciaPago() {
		return referenciaPago;
	}

	public void setReferenciaPago(String referenciaPago) {
		this.referenciaPago = referenciaPago;
	}

	
}
