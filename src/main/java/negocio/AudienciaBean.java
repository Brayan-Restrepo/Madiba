package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.AudienciaDAO;
import dao.SolicitudDAO;
import entidades.Asistencia;
import entidades.Resultado;
import entidades.Audiencia;

@Stateless
public class AudienciaBean implements iAudienciaBean {
	
	@Inject
	AudienciaDAO audienciaDAO;
	
	@Inject
	SolicitudDAO solicitudDAO;
	
	@Override
	public void addResultado(String tipoResultado, Audiencia audiencia, String conclusion, Long idSolicitud) {
		// TODO Auto-generated method stub
		
		
		Resultado resultado = new Resultado();
		resultado.setTipoResultado(tipoResultado);
		resultado.setConclusion(conclusion);
		resultado.setAudiencia(audiencia);
		this.audienciaDAO.addResultado(resultado);
		
		this.audienciaDAO.actualizarEstadoAudiencia(audiencia.getIdAudiencia(), "FINALIZADA");
		this.solicitudDAO.actualizarEstadoSolicitud(idSolicitud, "AUDIENCIA-FINALIZADA");
	}

	@Override
	public void addAsistencia(int id, Asistencia asistencia) {
		// TODO Auto-generated method stub
		this.audienciaDAO.addAsistencia(id, asistencia);
	}

	@Override
	public void actualizarEstadoAudiencia(Long idAudiencia, String nuevoEstado){
		this.audienciaDAO.actualizarEstadoAudiencia(idAudiencia, nuevoEstado);
	}
}
