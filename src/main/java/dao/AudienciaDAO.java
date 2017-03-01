package dao;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Asistencia;
import entidades.Audiencia;
import entidades.Resultado;

public class AudienciaDAO {
	
	@PersistenceContext
	EntityManager manager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addAsistencia(int id, Asistencia asistencia){
		Audiencia audiencia = this.manager.find(Audiencia.class, id);
		audiencia.addAsistencia(asistencia);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addResultado(Resultado resultado){
		this.manager.persist(resultado);
	}
}
