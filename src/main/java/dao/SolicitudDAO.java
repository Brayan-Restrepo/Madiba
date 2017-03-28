package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Devolucione;
import entidades.Solicitud;


public class SolicitudDAO {
	
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Solicitud> allSolicitud(String estado){

		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findAll").setParameter("estado", estado+"%").getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
			}
		}
		return solicitud;
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findSolicitudes(){

		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findSolicitudes").getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();
			solicitud.get(i).getAudiencias().size();
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
			}
		}
		return solicitud;
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findAudiencias(){

		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findAudiencias").getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getDevoluciones().size();
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
			}
		}
		return solicitud;
	}
	
	@SuppressWarnings("unchecked")
	public Solicitud findSolicitud(Long id){
		Solicitud solicitud = this.manager.find(Solicitud.class,id);		
		solicitud.getPartes().size();
		solicitud.getPagos().size();
		solicitud.getDesignacions().size();
		solicitud.getAudiencias().size();
		solicitud.getDevoluciones().size();
		for(int j=0;j<solicitud.getAudiencias().size();j++){
			solicitud.getAudiencias().get(j).getAgendas().size();
			solicitud.getAudiencias().get(j).getAsistencias().size();
			solicitud.getAudiencias().get(j).getResultados().size();
		}
		
		return solicitud;
	}
	
	public String findSolicitudEstado(Long id){
		return this.manager.find(Solicitud.class,id).getEstado();
	}
	
	/**
	 * Actualiza El estado de la Solicitud
	 * @param idSolicitud
	 * @param nuevoEstado
	 */
	public void actualizarEstadoSolicitud(Long idSolicitud, String nuevoEstado){
		System.out.println(nuevoEstado);
		Solicitud solicitud = this.manager.find(Solicitud.class, idSolicitud);
		solicitud.setEstado(nuevoEstado);
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addDevolucion(Devolucione devolucione){
		this.manager.persist(devolucione);
	}
	
	public void actualizarDevolucion(Long idDevolucion, boolean devolucion, Date fecha){
		Devolucione devolucione = this.manager.find(Devolucione.class, idDevolucion);
		devolucione.setDevolucion(devolucion);
		devolucione.setFecha(fecha);
	}
}
