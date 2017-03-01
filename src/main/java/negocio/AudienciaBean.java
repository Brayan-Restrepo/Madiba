package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.AudienciaDAO;
import dao.SolicitudDAO;
import entidades.Asistencia;
import entidades.Resultado;
import entidades.Solicitud;

@Stateless
public class AudienciaBean implements iAudienciaBean {
	
	@Inject
	AudienciaDAO audienciaDAO;
	
	@Override
	public void addResultado(String tipoResultado, Solicitud solicitud, String conclusion) {
		// TODO Auto-generated method stub
		Long idResultado = 1L;
		Resultado resultado = new Resultado();
		resultado.setIdResultado(idResultado);
		resultado.setTipoResultado(tipoResultado);
		resultado.setConclusion(conclusion);
		resultado.setSolicitud(solicitud);
		this.audienciaDAO.addResultado(resultado);
	}

	@Override
	public void addAsistencia(int id, Asistencia asistencia) {
		// TODO Auto-generated method stub
		this.audienciaDAO.addAsistencia(id, asistencia);
	}	
}
