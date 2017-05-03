package webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/webservice")
public class WebServicePrueba {
	
	@POST
	@Produces({"application/json"})
    public Response factorial(
    		@QueryParam("user") String user,
    		@QueryParam("pass") String pass
    ) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Credentials", "true")
			    .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
			    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			    .entity($login(user,pass))
			    .build();
    }

    String $login(String user, String pass) {
    	String resultado = "";
    	if (user.equalsIgnoreCase("conalbos") && pass.equalsIgnoreCase("madiba")) {
    		resultado = "VALIDO";
		}else{
			resultado = "INVALIDO";
		}
        
    	return  "{" 
				+ "\"resultado\":\""+resultado+"\","
				+ "\"dato\":\""+user+"\""
				+ "}";
    }
}