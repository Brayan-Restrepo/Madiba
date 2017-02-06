package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Conciliador;

public class ConciliadorDAO {
	
	@PersistenceContext
	EntityManager manager;
	
	public Conciliador consultarConciliador (Integer idConciliador){
		return manager.find(Conciliador.class, idConciliador);
	}
}
