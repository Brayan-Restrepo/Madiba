package webservice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import entidades.Conciliador;
import entidades.Usuario;
import presentacion.CifradorMD5;

@Path("/login")
public class Login {

	@PersistenceContext
	EntityManager manager;
	
	@POST
	@Produces({"application/json"})
    public Response login(
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
    	String validacion = "INVALIDO";
    	String rol = "";
    	String idConcil = "";
    	String nombreConcil = "";
    	String imgConcil = "";

    	CifradorMD5 cifrador = new CifradorMD5();
    	Usuario usuarioBD = manager.find(Usuario.class, user.toLowerCase());
		if(usuarioBD != null){
			if(usuarioBD.getPassword().equals(cifrador.MD5(pass))){
				validacion = "VALIDO";
				rol = usuarioBD.getRole();
				if(usuarioBD.getIdConciliador()!=null){
					idConcil = usuarioBD.getIdConciliador().toString();
					Conciliador conciliadorBD = manager.find(Conciliador.class, Long.parseLong(idConcil));
					String[] nombres = conciliadorBD.getNombres().split(" ");
					String[] apellidos = conciliadorBD.getApellidos().split(" ");
					nombreConcil = nombres[0]+" "+apellidos[0];
					imgConcil = conciliadorBD.getFoto();
				}
			}
		}
        
    	return  "{" 
				+ "\"resultado\":\""+validacion+"\","
				+ "\"rol\":\""+rol+"\","
				+ "\"idConcil\":\""+idConcil+"\","
				+ "\"nombreConcil\":\""+nombreConcil+"\","
				+ "\"imgConcil\":\""+imgConcil+"\""
				+ "}";
    }
    
}