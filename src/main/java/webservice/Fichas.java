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

import entidades.Audconcil;
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

	@SuppressWarnings("unchecked")
	List<Audconcil> $fichas(String idConciliador) {
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
		List<Audconcil> audiencias = this.manager.createNamedQuery("Audconcil.findConciliadorFecha").setParameter("identificacion", Long.parseLong(idConciliador)).setParameter("fechaini", fechaInicial).setParameter("fechafin", fechaFinal).getResultList();
		
		return audiencias;
    }
}