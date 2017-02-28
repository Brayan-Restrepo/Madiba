package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.SolicitudDAO;
import entidades.Asistencia;
import entidades.Resultado;
import entidades.Solicitud;

@Stateless
public class SolicitudBean implements iSolicitudBean {
	
	@Inject
	SolicitudDAO solicitudDAO;
	
	@Override
	public List<Solicitud> allSolicitud(String estado) {		
		return this.solicitudDAO.allSolicitud(estado);
	}

	@Override
	public List<Solicitud> findSolicitudes() {
		// TODO Auto-generated method stub
		return this.solicitudDAO.findSolicitudes();
	}

	@Override
	public List<Solicitud> findAudiencias() {
		// TODO Auto-generated method stub
		return this.solicitudDAO.findAudiencias();
	}
	
	@Override
	public Solicitud findSolicitud(Long id) {
		// TODO Auto-generated method stub
		return this.solicitudDAO.findSolicitud(id);
	}

	@Override
	public void addResultado(String tipoResultado, Solicitud solicitud, String conclusion) {
		// TODO Auto-generated method stub
		Long idResultado = 1L;
		Resultado resultado = new Resultado();
		resultado.setIdResultado(idResultado);
		resultado.setTipoResultado(tipoResultado);
		resultado.setConclusion(conclusion);
		resultado.setSolicitud(solicitud);
		this.solicitudDAO.addResultado(resultado);
	}

	@Override
	public void addAsistencia(Asistencia asistencia) {
		// TODO Auto-generated method stub
		this.solicitudDAO.addAsistencia(asistencia);
	}

}
