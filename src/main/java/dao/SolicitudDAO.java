package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
			
		
		}
		return solicitud;
		
	}
	
}
