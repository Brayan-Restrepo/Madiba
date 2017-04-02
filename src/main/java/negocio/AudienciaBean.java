package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.AudienciaDAO;
import dao.SolicitudDAO;
import entidades.Asistencia;
import entidades.Resultado;
import entidades.Audiencia;
import entidades.Pago;

@Stateless
public class AudienciaBean implements iAudienciaBean {
	
	@Inject
	AudienciaDAO audienciaDAO;
	
	@Inject
	SolicitudDAO solicitudDAO;
	
	@Override
	public void addResultado(String tipoResultado, Audiencia audiencia, String conclusion, Long idSolicitud, double nuevaCuantia) {
		Resultado resultado = new Resultado();
		resultado.setTipoResultado(tipoResultado);
		resultado.setConclusion(conclusion);
		resultado.setAudiencia(audiencia);
		resultado.setNuevaCuantia(nuevaCuantia);
		this.audienciaDAO.addResultado(resultado);
		
	}
	
	@Override
	public void addSobreCosto(Pago pago){
		this.audienciaDAO.addSobreCosto(pago);
	}
	
	@Override
	public void addResultadoSuspender(String tipoResultado, Audiencia audiencia, String conclusion, Long idSolicitud) {
		Resultado resultado = new Resultado();
		resultado.setTipoResultado(tipoResultado);
		resultado.setConclusion(conclusion);
		resultado.setAudiencia(audiencia);
		resultado.setNuevaCuantia(0);
		this.audienciaDAO.addResultado(resultado);
	}
	
	@Override
	public void addAsistencia(Audiencia audiencia, Long idParte, Boolean valAsistencia) {
		Asistencia asistencia = new Asistencia();
		asistencia.setAsistio(valAsistencia);
		asistencia.setExcusa("");
		asistencia.setAudiencia(audiencia);
		this.audienciaDAO.addAsistencia(asistencia,idParte);
	}

	@Override
	public void actualizarEstadoAudiencia(Long idAudiencia, String nuevoEstado){
		this.audienciaDAO.actualizarEstadoAudiencia(idAudiencia, nuevoEstado);
	}

	@Override
	public Audiencia findAudienciaResultadoAsistenia(Long idAudiencia) {

		return this.audienciaDAO.findAudienciaResultadoAsistenia(idAudiencia);
	}

	@Override
	public boolean verificarAsistencias(Long idAudiencia) {
		return this.audienciaDAO.verificarAsistencias(idAudiencia);
	}

	@Override
	public void guardarEscusaParte(Long idAudiencia, Long idParte, String excusa) {
		this.audienciaDAO.guardarEscusaParte(idAudiencia, idParte, excusa);
		
	}
}
