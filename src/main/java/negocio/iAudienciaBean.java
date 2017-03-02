package negocio;

import javax.ejb.Remote;

import entidades.Asistencia;
import entidades.Audiencia;

@Remote
public interface iAudienciaBean {
	
	public void addResultado(String tipoResultado, Audiencia audiencia, String conclucion, Long idSolicitud);
	public void addAsistencia(int id, Asistencia asistencia);
	public void actualizarEstadoAudiencia(Long idAudiencia, String nuevoEstado);
}
