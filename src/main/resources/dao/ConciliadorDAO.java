package dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Conciliador;

public class ConciliadorDAO {
	@PersistenceContext
	EntityManager manager;
	
	public Conciliador consultarConciliador (String id){
		return manager.find(Conciliador.class, id);
	}
}