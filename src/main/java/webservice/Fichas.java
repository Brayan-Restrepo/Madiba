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
    		@QueryParam("idConcil") String idConciliador,
    		@QueryParam("estado") String estado
    ) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Credentials", "true")
			    .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
			    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			    .entity($fichas(idConciliador,estado))
			    .build();
    }

	@SuppressWarnings("unchecked")
	List<Audconcil> $fichas(String idConciliador, String estado) {
		List<Audconcil> audiencias = this.manager.createNamedQuery("Audconcil.findConciliadorFecha").setParameter("identificacion", Long.parseLong(idConciliador)).setParameter("estado", estado+"%").getResultList();
		return audiencias;
    }
}