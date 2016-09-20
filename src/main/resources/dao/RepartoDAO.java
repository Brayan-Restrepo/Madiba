package dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Reparto;

public class RepartoDAO {
	@PersistenceContext
	EntityManager manager;
	
	public Reparto consultarReparto (String cedula){
		return manager.find(Reparto.class, cedula);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearReparto(Reparto reparto){
		manager.persist(reparto);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarReparto(Reparto reparto){
		manager.remove(reparto);
	}
	
	public void actualizarReparto(Reparto reparto){
		Reparto repartoEnBD = manager.find(Reparto.class, reparto.getCedula());
		libroEnBD.setTitulo(libro.getTitulo());
		libroEnBD.setAutor(libro.getAutor());
		libroEnBD.setArea(libro.getArea());
		libroEnBD.setEstudiantePrestado(libro.getEstudiantePrestado());
		libroEnBD.setEtiquetaReserva(libro.getEtiquetaReserva());
		libroEnBD.setFechaLimiteEntrega(libro.getEtiquetaReserva());
	}
}
