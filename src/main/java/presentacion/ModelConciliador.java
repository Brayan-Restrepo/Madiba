package presentacion;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ModelConciliador {
	
	private int id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String especialidad;
	private String experiencia;
	private String email;
	private String foto;
	
	public ModelConciliador() {
		
	}
	
	public ModelConciliador(int id, String nombre, String apellido, String telefono,
			String especialidad, String experiencia, String email, String foto) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.especialidad = especialidad;
		this.experiencia = experiencia;
		this.email = email;
		this.foto = foto;
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
