package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Actas_Conciliacione;
import entidades.Copia;
import entidades.Devolucione;
import entidades.Pago;
import entidades.Resultado;
import entidades.Solicitud;


public class SolicitudDAO {
	
	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Solicitud> allSolicitud(String estado){

		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findAll").setParameter("estado", estado+"%").getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			
			
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findSolicitudes(Date fechaInicial, Date fechaFinal){

		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findSolicitudes").setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}

	@SuppressWarnings("unchecked")
	public List<Solicitud> findSolicitudesFiltroParteFecha(Date fechaInicial, Date fechaFinal, String ccParte, String tipoParte){
		
		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findSolicitudesFiltroParteFecha").setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).setParameter("identificacion", ccParte).setParameter("tipoParte", tipoParte).getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findSolicitudesFiltroConciliadorFecha(Date fechaInicial, Date fechaFinal, String identificacion){
		
		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findSolicitudesFiltroConciliadorFecha").setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).setParameter("identificacion", identificacion).getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}
	
	/**
	 * Filtra tosdas las solicitudes por el numero de radicado y la ordena la fecha de solicitud
	 * @param nroRadicado fracmento del numero rdicado 201705 --->20170500094
	 * @return Lista de todas la solicitudes
	 */
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findSolicitudesFiltroRadicado(String nroRadicado){
		
		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findSolicitudesFiltroRadicado").setParameter("nroRadicado", nroRadicado+"%").getResultList();
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findAudiencias(String role, Long idConciliador, Date fechaInicial, Date fechaFinal){
		
		List<Solicitud> solicitud;
		if(role.equalsIgnoreCase("Conciliador")){
			solicitud = this.manager.createNamedQuery("Solicitud.findAudienciasConciliador").setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).setParameter("idConciliador", idConciliador).getResultList();
		}else{
			solicitud = this.manager.createNamedQuery("Solicitud.findAudiencias").setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).getResultList();
		}
		
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findAudienciasFiltroParteFecha(String role, Long idConciliador, Date fechaInicial, Date fechaFinal, String identificacion, String tipoParte){
		
		List<Solicitud> solicitud;
		if(role.equalsIgnoreCase("Conciliador")){
			//Falta Filtros
			solicitud = this.manager.createNamedQuery("Solicitud.findAudienciasConciliadorFiltroParteFecha").setParameter("idConciliador", idConciliador).setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).setParameter("identificacion", identificacion).setParameter("tipoParte", tipoParte).getResultList();
		}else{
			solicitud = this.manager.createNamedQuery("Solicitud.findAudienciasFiltroParteFecha").setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).setParameter("identificacion", identificacion).setParameter("tipoParte", tipoParte).getResultList();
		}
		
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findAudienciasFiltroConciliadorFecha(String role, Long idConciliador, Date fechaInicial, Date fechaFinal, String identificacion){
		
		List<Solicitud> solicitud;
		if(role.equalsIgnoreCase("Conciliador")){
			//Falta Filtros
			solicitud = this.manager.createNamedQuery("Solicitud.findAudienciasConciliador").setParameter("idConciliador", idConciliador).getResultList();
		}else{
			solicitud = this.manager.createNamedQuery("Solicitud.findAudienciasFiltroConciliadorFecha").setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).setParameter("identificacion", identificacion).getResultList();
		}
		
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}
	
	/**
	 * Filtra tosdas las solicitudes por el numero de radicado y la ordena la fecha de solicitud
	 * @param role Rol del Solicitante
	 * @param idConciliador el id del Conciliador en caso de serlo 
	 * @param nroRadicado numero de radicado de la solicitud
	 * @return lista de la solicitudes
	 */
	@SuppressWarnings("unchecked")
	public List<Solicitud> findAudienciasFiltroRadicado(String role, Long idConciliador, String nroRadicado){
		
		List<Solicitud> solicitud;
		if(role.equalsIgnoreCase("Conciliador")){
			//Falta Filtros
			solicitud = this.manager.createNamedQuery("Solicitud.findAudienciasConciliadorFiltroRadicado").setParameter("idConciliador", idConciliador).setParameter("nroRadicado", nroRadicado+"%").getResultList();
		}else{
			solicitud = this.manager.createNamedQuery("Solicitud.findAudienciasFiltroRadicado").setParameter("nroRadicado", nroRadicado+"%").getResultList();
		}
		
		for(int i=0;i<solicitud.size();i++){
			solicitud.get(i).getPartes().size();
			solicitud.get(i).getPagos().size();
			solicitud.get(i).getDesignacions().size();			
			solicitud.get(i).getAudiencias().size();
			solicitud.get(i).getActasConciliaciones().size();			
			solicitud.get(i).getDevoluciones().size();
			
			for(int j=0;j<solicitud.get(i).getActasConciliaciones().size();j++){
				solicitud.get(i).getActasConciliaciones().get(j).getCopias().size();
			}
			for(int j=0;j<solicitud.get(i).getAudiencias().size();j++){				
				solicitud.get(i).getAudiencias().get(j).getAgendas().size();
				solicitud.get(i).getAudiencias().get(j).getAsistencias().size();
				solicitud.get(i).getAudiencias().get(j).getResultados().size();
			}
		}
		return solicitud;
	}
	
	
	@SuppressWarnings("unchecked")
	public Solicitud findSolicitud(Long id){
		Solicitud solicitud = this.manager.find(Solicitud.class,id);		
		
		solicitud.getPartes().size();
		solicitud.getPagos().size();
		solicitud.getDesignacions().size();
		solicitud.getAudiencias().size();
		solicitud.getDevoluciones().size();
		solicitud.getActasConciliaciones().size();
		
		for(int j=0;j<solicitud.getActasConciliaciones().size();j++){
			solicitud.getActasConciliaciones().get(j).getCopias().size();
		}
		for(int j=0;j<solicitud.getAudiencias().size();j++){
			solicitud.getAudiencias().get(j).getAgendas().size();
			solicitud.getAudiencias().get(j).getAsistencias().size();
			solicitud.getAudiencias().get(j).getResultados().size();
		}
		
		return solicitud;
	}
	
	public String findSolicitudEstado(Long id){
		return this.manager.find(Solicitud.class,id).getEstado();
	}
	
	/**
	 * Actualiza El estado de la Solicitud
	 * @param idSolicitud
	 * @param nuevoEstado
	 */
	public void actualizarEstadoSolicitud(Long idSolicitud, String nuevoEstado){
		Solicitud solicitud = this.manager.find(Solicitud.class, idSolicitud);
		solicitud.setEstado(nuevoEstado);
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addDevolucion(Devolucione devolucione){
		this.manager.persist(devolucione);
	}
	
	public void actualizarDevolucion(Long idDevolucion, boolean devolucion, Date fecha){
		Devolucione devolucione = this.manager.find(Devolucione.class, idDevolucion);
		devolucione.setDevolucion(devolucion);
		devolucione.setFecha(fecha);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void guardarActaConstancia(Actas_Conciliacione actaConstancia){
		this.manager.persist(actaConstancia);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void guardarCopia(Copia copia){
		this.manager.persist(copia);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void guardarPago(Pago auxPago){
		Pago pago = this.manager.find(Pago.class, auxPago.getIdPago());
		
		pago.setFecha(auxPago.getFecha());
		pago.setFormaPago(auxPago.getFormaPago());
		pago.setEstado(auxPago.getEstado());
	}
}
