package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.Solicitud;
import negocio.iSolicitudBean;

@ManagedBean
@ViewScoped
public class ControllerAudiencia {
	
	private List<String> listaAsistencias;
	
	public List<String> getListaAsistencias() {
		return listaAsistencias;
	}

	public ControllerAudiencia() {
		this.listaAsistencias = new ArrayList<String>();
	}

	public void setListaAsistencias(List<String> listaAsistencias) {
		this.listaAsistencias = listaAsistencias;
	}

	@EJB
	public iSolicitudBean solicitudBean;
	
	public Solicitud findSolicitud(Long id){		
		return this.solicitudBean.findSolicitud(id);
	}
	
	public void AddAsistecia(){		
		this.listaAsistencias.add("");
	}
}
