package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Conciliador;
import entidades.Especialidad;
import entidades.Reparto;

public class ListaConciliadoresDAO {
	
	public ListaConciliadoresDAO(){
	}
	
	@PersistenceContext
	EntityManager manager;
	
    public Reparto consultarReparto (String turno){
		return manager.find(Reparto.class, turno);
	}
    
    public Especialidad consultarEspecialidad (String id_especialidad){
		return manager.find(Especialidad.class, id_especialidad);
	}
    
    public Conciliador consultarConciliador (String id_conciliador){
		return manager.find(Conciliador.class, id_conciliador);
	}
    
}
