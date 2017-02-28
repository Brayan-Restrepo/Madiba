package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Dominio;
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
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findSolicitudes(){

		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findSolicitudes").getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();
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
		}
		return solicitud;
	}
	
	@SuppressWarnings("unchecked")
	public Solicitud findSolicitud(Long id){
		Solicitud solicitud = this.manager.find(Solicitud.class,id);
		
		solicitud.getPartes().size();
		solicitud.getPagos().size();
		solicitud.getDesignacions().size();
		
		return solicitud;
	}
}
