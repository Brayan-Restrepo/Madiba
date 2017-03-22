package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
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
	private boolean ficha; 

	private List<Long> selectSolicitud; 
	private String statusSelect = "";
	
	public ModelSolicitud(){
		this.ficha=true;
		this.selectSolicitud=new ArrayList<Long>();
	}
	
	
	public List<Long> getSelectSolicitud() {
		return selectSolicitud;
	}


	public void setSelectSolicitud(List<Long> selectSolicitud) {
		this.selectSolicitud = selectSolicitud;
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

	public String getPretenciones() {
		return pretenciones;
	}

	public void setPretenciones(String pretenciones) {
		this.pretenciones = pretenciones;
	}

	public boolean isFicha() {
		return this.ficha;
	}

	public void setFicha(boolean ficha) {
		this.ficha = ficha;
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
