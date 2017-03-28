package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entidades.Devolucione;
import entidades.Solicitud;

@Remote
public interface iSolicitudBean {
	
	public List<Solicitud> allSolicitud(String estado);
	public List<Solicitud> findSolicitudes();
	public List<Solicitud> findAudiencias();
	public Solicitud findSolicitud(Long id);
	public void actualizarEstadoSolicitud(Long id,String nuevoEstado);
	
	public String findSolicitudEstado(Long id);
	public void addDevolucion(Devolucione devolucione);
	public void actualizarDevolucion(Long idDevolucion, boolean devolucion, Date fecha);
}
