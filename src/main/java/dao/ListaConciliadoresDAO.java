package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Conciliador;
import entidades.Reparto;

public class ListaConciliadoresDAO {
	
	public ListaConciliadoresDAO(){
	}
	
	@PersistenceContext
	EntityManager manager;
	
    public Reparto consultarReparto (int turno){
		return manager.find(Reparto.class, turno);
	}
    
    public Conciliador consultarConciliador (int id_conciliador){
		return manager.find(Conciliador.class, id_conciliador);
	}
    
}
