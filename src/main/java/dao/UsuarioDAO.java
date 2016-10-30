package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Usuario;

public class UsuarioDAO {
	
	@PersistenceContext
	EntityManager manager;
	
    public Usuario consultarUsuario (String nickname){
		return manager.find(Usuario.class, nickname);
	}

}
