package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Dominio;

public class DominioDAO {
	
	@PersistenceContext
	EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<String> listaDominio(String tipo){
		List<String> domidio = this.manager.createNamedQuery("Dominio.findType").setParameter("tipo", tipo).getResultList();
		return domidio;
		
	}
	
	@SuppressWarnings("unchecked")
	public String findName(int id){
		String nombre = this.manager.find(Dominio.class,id).getNombre();
		return nombre;
		
	}
}
