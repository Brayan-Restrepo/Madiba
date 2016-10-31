package negocio;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface iListaConciliadoresBean {

	public List listaConciliadores();
	
	public List listaReparto();
}
