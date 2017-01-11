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
	private boolean select; 

	public ModelSolicitud(int id, String fecha, String[] convotante,String[] convocado, String conciliador, String descripcion,
			String estado, String solicitud, String numeroRadicado) {
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

}
