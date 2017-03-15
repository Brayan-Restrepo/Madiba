package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Conciliador;
import entidades.Reparto;
import entidades.Solicitud;

public class ConciliadorDAO {
	
	@PersistenceContext
	EntityManager manager;


	@SuppressWarnings("unchecked")
	public List<Conciliador> allConciliador(){
		//List<Conciliador> conciliadores = this.manager.createQuery("FROM Conciliador").getResultList();
		List<Conciliador> conciliadores = this.manager.createNamedQuery("Conciliador.findAll").getResultList();
		for(int i=0;i<conciliadores.size();i++){
			conciliadores.get(i).getConciliadorEspecialidads().size();
			conciliadores.get(i).getRepartos().size();
			System.out.println("Turno-> "+conciliadores.get(i).getRepartos().get(0).getTurno()+" id-> "+conciliadores.get(i).getIdConciliador());
		}
		
		return conciliadores;
		
	}
	
	public List<Conciliador> allConciliadorReparto(){
		@SuppressWarnings("unchecked")
		List<Conciliador> conciliadores = this.manager.createNamedQuery("Conciliador.findAllReparto").getResultList();
		for(int i=0;i<conciliadores.size();i++){
			conciliadores.get(i).getConciliadorEspecialidads().size();
			conciliadores.get(i).getRepartos().size();
		}
		return conciliadores;
	}
	
	public Conciliador consultarConciliador (Integer idConciliador){
		
		return manager.find(Conciliador.class, idConciliador);
	}
	
	@SuppressWarnings("unchecked")
	public List<Reparto> allReparto(){
		return this.manager.createNamedQuery("Reparto.findAll").getResultList();
	}
	
	public void removeAllReparto(List<Reparto> reparto){
		for(int i=0;i<reparto.size();i++){			
			this.manager.remove(reparto.get(i));
		}
	}
	
	public Conciliador reparto(Long idSolicitud,List<Reparto> reparto){
		Solicitud solicitud = this.manager.find(Solicitud.class, idSolicitud);
		if(solicitud.getDesignacions().get(solicitud.getDesignacions().size()-1).getTipoDesignacion().equals("Reparto")){
			
			for(int i=1;i<reparto.size();i++){
				reparto.get(i).setTurno(i+0L);
				this.manager.persist(reparto.get(i));
			}
			
			reparto.get(0).setTurno(reparto.size()+0L);
			this.manager.persist(reparto.get(0));
			
			
			solicitud.getDesignacions().get(solicitud.getDesignacions().size()-1).setConciliador(reparto.get(0).getConciliador());
			solicitud.setEstado("DESIGNACION");
					
			return reparto.get(0).getConciliador();
		}else{
			int j = 0;
			int corimiento=0;
			for(int i=0;i<reparto.size();i++){
				if(solicitud.getDesignacions().get(solicitud.getDesignacions().size()-1).getConciliador().getIdConciliador()==reparto.get(i).getConciliador().getIdConciliador()){
					j=i;
					corimiento=1;
				}else{
					reparto.get(i).setTurno(i+1L-corimiento);
					this.manager.persist(reparto.get(i));
				}
			}
			reparto.get(j).setTurno(reparto.size()+1L-corimiento);
			this.manager.persist(reparto.get(j));
			
			
			solicitud.getDesignacions().get(solicitud.getDesignacions().size()-1).setConciliador(reparto.get(j).getConciliador());
			solicitud.setEstado("DESIGNACION");
					
			return reparto.get(j).getConciliador();
		}
		
	}
}
