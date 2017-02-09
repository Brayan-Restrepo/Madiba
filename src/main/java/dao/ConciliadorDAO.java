package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Conciliador;

public class ConciliadorDAO {
	
	@PersistenceContext
	EntityManager manager;


	@SuppressWarnings("unchecked")
	public List<Conciliador> allConciliador(){
		//List<Conciliador> conciliadores = this.manager.createQuery("FROM Conciliador").getResultList();
		List<Conciliador> conciliadores = this.manager.createNamedQuery("Conciliador.findAll").getResultList();
		return conciliadores;
		
	}
	
	public Conciliador consultarConciliador (Integer idConciliador){
		
		return manager.find(Conciliador.class, idConciliador);
	}
}
