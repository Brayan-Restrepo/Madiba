package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.SolicitudDAO;
import entidades.Solicitud;

@Stateless
public class SolicitudBean implements iSolicitudBean {
	
	@Inject
	SolicitudDAO solicitudDAO;
	
	@Override
	public List<Solicitud> allSolicitud(String estado) {		
		return this.solicitudDAO.allSolicitud(estado);
	}

}
