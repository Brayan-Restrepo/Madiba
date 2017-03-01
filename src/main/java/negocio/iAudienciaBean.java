package negocio;

import javax.ejb.Remote;

import entidades.Asistencia;
import entidades.Solicitud;

@Remote
public interface iAudienciaBean {
	
	public void addResultado(String tipoResultado, Solicitud solicitud, String conclucion);
	public void addAsistencia(int id, Asistencia asistencia);
}
