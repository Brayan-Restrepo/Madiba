package presentacion;

import javax.faces.bean.ManagedBean;

import entidades.Conciliador;

@ManagedBean
public class ModelConciliador {
	
	/**
	 * Estos datos se acceden desde el modalAsignar Conciliador
	 */
	private Conciliador conciliador;
	private String tipoDesignacion;
	
	private int id;
	private String cedula;
	private String nombre;
	private String apellido;
	private String telefono;
	private String especialidad;
	private String experiencia;
	private String email;
	private String foto;
	
	public ModelConciliador() {}
	
	public String getTipoDesignacion() {
		return tipoDesignacion;
	}

	public void setTipoDesignacion(String tipoDesignacion) {
		this.tipoDesignacion = tipoDesignacion;
	}

	public Conciliador getConciliador() {
		return conciliador;
	}

	public void setConciliador(Conciliador conciliador) {
		this.conciliador = conciliador;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
		
}
