package dao;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Asistencia;
import entidades.Audiencia;
import entidades.Resultado;
import entidades.Solicitud;

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
		Long res = (Long)this.manager.createNamedQuery("Resultado.countAll").getResultList().get(0);
		resultado.setIdResultado(res+1);
		this.manager.persist(resultado);
	}
	
	
	/**
	 * Actualiza El estado de la Audiencia
	 * @param idAudiencia
	 * @param nuevoEstado
	 */
	public void actualizarEstadoAudiencia(Long idAudiencia, String nuevoEstado){
		Audiencia audiencia = this.manager.find(Audiencia.class, idAudiencia);
		audiencia.setEstado(nuevoEstado);
	}
}
