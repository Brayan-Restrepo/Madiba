package negocio;

import java.util.List;

import javax.ejb.Remote;

import entidades.Solicitud;

@Remote
public interface iSolicitudBean {
	
	public List<Solicitud> allSolicitud(String estado);
	
}
