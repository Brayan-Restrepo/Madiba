package presentacion;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ModelListaConciliadores {
	
	private int turno;
	private int id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String[] especialidades;
	private int experiencia;
	private String email;
	private String foto;
	
	public ModelListaConciliadores() {
		
	}
	
	public ModelListaConciliadores(int turno,int id, String nombre, String apellido, String telefono,String[] especialidades, 
			int experiencia, String email, String foto) {
		this.turno = turno;
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.especialidades = especialidades;
		this.experiencia = experiencia;
		this.email = email;
		this.foto = foto;
	}
	
	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
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

	public String[] getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidad(String[] especialidades) {
		this.especialidades = especialidades;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
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
