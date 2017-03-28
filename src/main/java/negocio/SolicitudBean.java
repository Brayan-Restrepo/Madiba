package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.SolicitudDAO;
import entidades.Devolucione;
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
		this.solicitudDAO.actualizarEstadoSolicitud(id, nuevoEstado);
	}

	@Override
	public String findSolicitudEstado(Long id) {
		// TODO Auto-generated method stub
		return this.solicitudDAO.findSolicitudEstado(id);
	}

	@Override
	public void addDevolucion(Devolucione devolucione) {
		this.solicitudDAO.addDevolucion(devolucione);
		
	}
	
	@Override
	public void actualizarDevolucion(Long idDevolucion, boolean devolucion, Date fecha){
		this.solicitudDAO.actualizarDevolucion(idDevolucion, devolucion, fecha);
	}
	
}
