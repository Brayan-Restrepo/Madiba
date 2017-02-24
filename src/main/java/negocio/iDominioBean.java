package negocio;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface iDominioBean {
	
	public List<String> listaDominio(String tipo);
	public String nombreDominio(int id);
}
