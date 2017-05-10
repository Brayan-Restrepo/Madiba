package webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import entidades.Solicitud;

@Path("/fichas")
public class Fichas {

	@PersistenceContext
	EntityManager manager;
	
	@POST
	@Produces({"application/json"})
    public Response fichas(
    		@QueryParam("idConcil") String idConciliador
    ) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Credentials", "true")
			    .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
			    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			    .entity($fichas(idConciliador))
			    .build();
    }

	String $fichas(String idConciliador) {

    	return audienciasConciliador(idConciliador);

    }
    
    @SuppressWarnings("unchecked")
	public String audienciasConciliador(String idConciliador){	
    	Date fechaActual = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fechaActual);
    	calendar.add(Calendar.MONTH, -3); 
    	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicial = null;
		Date fechaFinal = null;
		try {
			 fechaInicial = formatoDelTexto.parse(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
			 fechaFinal = formatoDelTexto.parse(new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
		} catch (ParseException ex) {
		     ex.printStackTrace();
		}
		
		/*List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findSolicitudesFiltroConciliadorFecha").setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal).setParameter("identificacion", idConciliador).getResultList();
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
		}*/
		
		List<Solicitud> solicitud = this.manager.createNamedQuery("Solicitud.findAll").setParameter("estado", "%").getResultList();
		
		return solicitud.get(0).getPartes().get(0).getApellidos()+"";
	}
}