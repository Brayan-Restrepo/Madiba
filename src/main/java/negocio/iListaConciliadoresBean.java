package negocio;

import javax.ejb.Remote;

@Remote
public interface iListaConciliadoresBean {

	public String buscarConciliador(int id_conciliador);
}
