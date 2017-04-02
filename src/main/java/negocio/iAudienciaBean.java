package negocio;

import javax.ejb.Remote;

import entidades.Audiencia;
import entidades.Pago;

@Remote
public interface iAudienciaBean {
	
	public void addResultado(String tipoResultado, Audiencia audiencia, String conclucion, Long idSolicitud, double nuevaCuantia);
	public void addSobreCosto(Pago pago);
	
	public void addAsistencia(Audiencia audiencia, Long idParte, Boolean valAsistencia);
	public void actualizarEstadoAudiencia(Long idAudiencia, String nuevoEstado);
	public Audiencia findAudienciaResultadoAsistenia(Long idAudiencia);
	public boolean verificarAsistencias(Long idAudiencia);
	public void guardarEscusaParte(Long idAudiencia, Long idParte, String excusa);
	public void addResultadoSuspender(String tipoResultado, Audiencia audiencia, String conclucion, Long idSolicitud);
	
}
