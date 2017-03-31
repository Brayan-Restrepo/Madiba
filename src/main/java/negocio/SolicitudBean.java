package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.SolicitudDAO;
import entidades.Actas_Conciliacione;
import entidades.Copia;
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
	public List<Solicitud> findAudiencias(String role, Long idConciliador){
		// TODO Auto-generated method stub
		return this.solicitudDAO.findAudiencias(role, idConciliador);
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

	@Override
	public List<Solicitud> findSolicitudesFiltroParteFecha(Date fechaInicial, Date fechaFinal, String ccParte,
			String tipoParte) {
		return this.solicitudDAO.findSolicitudesFiltroParteFecha(fechaInicial, fechaFinal, ccParte, tipoParte);
	}

	@Override
	public List<Solicitud> findSolicitudesFiltroConciliadorFecha(Date fechaInicial, Date fechaFinal,
			String identificacion) {
		
		return this.solicitudDAO.findSolicitudesFiltroConciliadorFecha(fechaInicial, fechaFinal, identificacion);
	}

	@Override
	public List<Solicitud> findAudienciasFiltroParteFecha(String role, Long idConciliador, Date fechaInicial,
			Date fechaFinal, String identificacion, String tipoParte) {		
		return this.solicitudDAO.findAudienciasFiltroParteFecha(role, idConciliador, fechaInicial, fechaFinal, identificacion, tipoParte);
	}

	@Override
	public List<Solicitud> findAudienciasFiltroConciliadorFecha(String role, Long idConciliador, Date fechaInicial,
			Date fechaFinal, String identificacion) {
		return this.solicitudDAO.findAudienciasFiltroConciliadorFecha(role, idConciliador, fechaInicial, fechaFinal, identificacion);
	}

	@Override
	public void guardarActaConstancia(Actas_Conciliacione actaConstancia) {
		this.solicitudDAO.guardarActaConstancia(actaConstancia);		
	}

	@Override
	public void guardarCopia(Copia copia) {
		this.solicitudDAO.guardarCopia(copia);
	}
	
}
