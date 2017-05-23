package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.Solicitud;

@ManagedBean
@ViewScoped
public class ModelAudiencia {

	private boolean ficha; 

	private List<Solicitud> selectSolicitud; 
	private String statusSelect = "";

	public ModelAudiencia(){
		
		this.ficha=true;
		this.selectSolicitud=new ArrayList<Solicitud>();
		//this.valorAsistencias = new Long[2];
	}
	
	public boolean isFicha() {
		return ficha;
	}

	public void setFicha(boolean ficha) {
		this.ficha = ficha;
	}

	public List<Solicitud> getSelectSolicitud() {
		return selectSolicitud;
	}

	public void setSelectSolicitud(List<Solicitud> selectSolicitud) {
		this.selectSolicitud = selectSolicitud;
	}

	public void addSelectSolicitud(Solicitud auxSolicitud, String estado) {
		if(!statusSelect.equals(estado)){
			statusSelect = estado;
			this.selectSolicitud = new ArrayList<Solicitud>();
		}
		if(estado.equalsIgnoreCase("AUDIENCIA-CITACION") || estado.equalsIgnoreCase("AUDIENCIA-FINALIZADA")){
			boolean isSelect = false;
			int position = 0;
			
			for(int i=0;i<this.selectSolicitud.size();i++){
				if(this.selectSolicitud.get(i)==auxSolicitud){
					isSelect = true;
					position = i;
					break;
				}
			}
			if(isSelect){
				this.selectSolicitud.remove(position);
			}else{
				this.selectSolicitud.add(auxSolicitud);
			}
		}
		else{
			if(this.selectSolicitud.size()>0){
				Long idSolicitudLista = this.selectSolicitud.get(0).getIdSolicitud();
				this.selectSolicitud = new ArrayList<Solicitud>();
				if(idSolicitudLista != auxSolicitud.getIdSolicitud()){
					this.selectSolicitud.add(auxSolicitud);
				}
			}else{
				this.selectSolicitud = new ArrayList<Solicitud>();
				this.selectSolicitud.add(auxSolicitud);
				statusSelect = estado;
			}
		}
	}
	
}
