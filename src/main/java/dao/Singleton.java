package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Singleton {

	private static EntityManager conexion;
	
	private Singleton (){
		
	}
	
	public static EntityManager getInstance(){
		if(Singleton.conexion == null){
			conexion = Persistence.createEntityManagerFactory("MadibaPU").createEntityManager();
		}
		return conexion;
	}
}
