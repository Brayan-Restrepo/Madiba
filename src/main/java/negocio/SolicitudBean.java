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
	public void actualizarEstadoSolicitud(Long id, String nuevoEstado){
		System.out.println(nuevoEstado);
		this.solicitudDAO.actualizarEstadoSolicitud(id, nuevoEstado);
	}
	
}
