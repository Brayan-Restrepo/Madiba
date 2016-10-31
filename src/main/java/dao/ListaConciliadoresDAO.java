package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    @SuppressWarnings("unchecked")
	public List<Conciliador> listaConciliadores(){ 
        Query consultaSQL = manager.createQuery("FROM Conciliador ORDER BY apellidos ASC");
		List<Conciliador> listaConciliadores = consultaSQL.getResultList();

        return listaConciliadores; 
    }
    
    @SuppressWarnings("unchecked")
	public List<Reparto> listaReparto(){ 
        Query consultaSQL = manager.createQuery("FROM Reparto");
		List<Reparto> listaReparto = consultaSQL.getResultList();

        return listaReparto; 
    }  
}
