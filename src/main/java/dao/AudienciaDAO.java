package dao;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Asistencia;
import entidades.Audiencia;
import entidades.Parte;
import entidades.Resultado;

public class AudienciaDAO {
	
	@PersistenceContext
	EntityManager manager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addAsistencia(Asistencia asistencia, Long idParte){
		Parte parte = this.manager.find(Parte.class, idParte);
		asistencia.setParte(parte);
		Long id = (Long)this.manager.createNamedQuery("Asistencia.countAll").getResultList().get(0);
		asistencia.setIdAsistencia(id+1);
		this.manager.persist(asistencia);
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
	
	/**
	 * Busca la Audiencia y retorna la audiencia con la lista de asitencia y resultado
	 * @param idAudiencia
	 * @return
	 */
	public Audiencia findAudienciaResultadoAsistenia(Long idAudiencia){
		Audiencia audiencia = this.manager.find(Audiencia.class, idAudiencia);
		audiencia.getResultados().size();
		audiencia.getAsistencias().size();
		return audiencia;
	}
	
	/**
	 * Verifica si en una Audiencia hay registros de Asistencias
	 * Si hay Registro -> True
	 * No hay Registro -> False
	 * @param idAudiencia
	 * @return
	 */
	public boolean verificarAsistencias(Long idAudiencia){
		Audiencia audiencia = this.manager.find(Audiencia.class, idAudiencia);
		if(audiencia.getAsistencias().size()==0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Guarda la Ruta de la escusa de la inacistencia de la Parte
	 * @param idAudiencia 
	 * @param idParte
	 * @param excusa Ruta de la escusa
	 */
	public void guardarEscusaParte(Long idAudiencia, Long idParte, String excusa){
		Audiencia audiencia = this.manager.find(Audiencia.class, idAudiencia);
		
		for (int i = 0; i < audiencia.getAsistencias().size(); i++) {
			if(audiencia.getAsistencias().get(i).getParte().getIdParte()==idParte && !audiencia.getAsistencias().get(i).getAsistio()){
				audiencia.getAsistencias().get(i).setExcusa(excusa);
				System.out.println("Entrar");
			}
		}
	}
}
