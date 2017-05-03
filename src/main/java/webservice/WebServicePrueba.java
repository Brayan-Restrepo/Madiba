package webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import entidades.Conciliador;
import negocio.iConciliadorBean;


@Path("/webservice")
public class WebServicePrueba {
	

	
	@POST
	@Produces({"application/json"})
    public Response factorial(
    		@QueryParam("user") String user,
    		@QueryParam("pass") String pass
    ) {
		List<Conciliador> c = new ArrayList<Conciliador>();
		Conciliador cons = new Conciliador();
		cons.setApellidos("fffff");
		c.add(cons);
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Credentials", "true")
			    .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
			     .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			     .entity(c)
			     .build();
		//return $login(user,pass);
        /*return Response.ok($login(user,pass))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();*/
    }

    String $login(String user, String pass) {
    	String resultado = "";
    	if (user.equalsIgnoreCase("conalbos") && pass.equalsIgnoreCase("madiba")) {
    		resultado = "VALIDO";
		}else{
			resultado = "INVALIDO";
		}
        
    	return    "{" 
    			+ "\"resultado\":"+resultado+","
    			+ "\"dato\":"+user
    			+ "}";
    }
}