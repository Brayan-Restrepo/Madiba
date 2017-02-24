package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Usuario;

public class LoginDAO {
	
	@PersistenceContext
	EntityManager manager;
	
    public Usuario consultarUsuario (String nickname){
		return manager.find(Usuario.class, nickname);
	}

}
