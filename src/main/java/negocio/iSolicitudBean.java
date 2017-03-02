package negocio;

import java.util.List;

import javax.ejb.Remote;

import entidades.Asistencia;
import entidades.Resultado;
import entidades.Solicitud;

@Remote
public interface iSolicitudBean {
	
	public List<Solicitud> allSolicitud(String estado);
	public List<Solicitud> findSolicitudes();
	public List<Solicitud> findAudiencias();
	public Solicitud findSolicitud(Long id);
	public void actualizarEstadoSolicitud(Long id,String nuevoEstado);
	
		
}
