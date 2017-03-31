package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entidades.Actas_Conciliacione;
import entidades.Devolucione;
import entidades.Solicitud;

@Remote
public interface iSolicitudBean {
	
	public List<Solicitud> allSolicitud(String estado);
	
	
	public Solicitud findSolicitud(Long id);
	public void actualizarEstadoSolicitud(Long id,String nuevoEstado);
	
	public String findSolicitudEstado(Long id);
	public void addDevolucion(Devolucione devolucione);
	public void actualizarDevolucion(Long idDevolucion, boolean devolucion, Date fecha);
	
	public void guardarActaConstancia(Actas_Conciliacione actaConstancia);
	
	public List<Solicitud> findSolicitudes();
	public List<Solicitud> findSolicitudesFiltroParteFecha(Date fechaInicial, Date fechaFinal, String ccParte, String tipoParte);
	public List<Solicitud> findSolicitudesFiltroConciliadorFecha(Date fechaInicial, Date fechaFinal, String identificacion);
	
	public List<Solicitud> findAudiencias(String role, Long idConciliador);
	public List<Solicitud> findAudienciasFiltroParteFecha(String role, Long idConciliador, Date fechaInicial, Date fechaFinal, String identificacion, String tipoParte);
	public List<Solicitud> findAudienciasFiltroConciliadorFecha(String role, Long idConciliador, Date fechaInicial, Date fechaFinal, String identificacion);
}
