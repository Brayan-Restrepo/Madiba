package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ModelAudiencia {

	private boolean ficha; 

	private List<Long> selectSolicitud; 
	private String statusSelect = "";

	public ModelAudiencia(){
		
		this.ficha=true;
		this.selectSolicitud=new ArrayList<Long>();
		//this.valorAsistencias = new Long[2];
	}
	
	public boolean isFicha() {
		return ficha;
	}

	public void setFicha(boolean ficha) {
		this.ficha = ficha;
	}

	public List<Long> getSelectSolicitud() {
		return selectSolicitud;
	}

	public void setSelectSolicitud(List<Long> selectSolicitud) {
		this.selectSolicitud = selectSolicitud;
	}

	public void addSelectSolicitud(Long id, String estado) {
		System.out.println("Entre al click select "+id);
		if(!statusSelect.equals(estado)){
			statusSelect = estado;
			this.selectSolicitud = new ArrayList<Long>();
		}
		if(estado.equalsIgnoreCase("AUDIENCIA-CITACION") || estado.equalsIgnoreCase("AUDIENCIA-FINALIZADA")){
			boolean isSelect = false;
			int position = 0;
			
			for(int i=0;i<this.selectSolicitud.size();i++){
				if(this.selectSolicitud.get(i)==id){
					isSelect = true;
					position = i;
					break;
				}
			}
			if(isSelect){
				this.selectSolicitud.remove(position);
			}else{
				this.selectSolicitud.add(id);
			}
			System.out.println(isSelect);
		}
		else{
			if(this.selectSolicitud.size()>0){
				Long idSolicitudLista = this.selectSolicitud.get(0);
				this.selectSolicitud = new ArrayList<Long>();
				if(idSolicitudLista != id){
					this.selectSolicitud.add(id);
				}
			}else{
				this.selectSolicitud = new ArrayList<Long>();
				this.selectSolicitud.add(id);
				statusSelect = estado;
			}
		}
		System.out.println(	this.selectSolicitud.toString());
	}
	
}
