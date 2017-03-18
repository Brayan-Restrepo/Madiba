package negocio;

import javax.ejb.Remote;

import entidades.Audiencia;

@Remote
public interface iAudienciaBean {
	
	public void addResultado(String tipoResultado, Audiencia audiencia, String conclucion, Long idSolicitud);
	public void addAsistencia(Audiencia audiencia, Long idParte, Boolean valAsistencia);
	public void actualizarEstadoAudiencia(Long idAudiencia, String nuevoEstado);
	public Audiencia findAudienciaResultadoAsistenia(Long idAudiencia);
	public boolean verificarAsistencias(Long idAudiencia);
}
