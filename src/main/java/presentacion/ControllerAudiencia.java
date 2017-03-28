package presentacion;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Audiencia;
import entidades.Devolucione;
import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iSolicitudBean;

@ManagedBean
@ViewScoped
public class ControllerAudiencia {

	
	public ControllerAudiencia() {

		this.coloresEstado = new HashMap<String, String>();
		this.coloresEstado.put("GRABADA", "info");
		this.coloresEstado.put("PAGADA", "primary");
		this.coloresEstado.put("RADICADA", "warning");
		this.coloresEstado.put("DESIGNACION", "success");
		this.coloresEstado.put("AUDIENCIA-CITACION", "info");
		this.coloresEstado.put("AUDIENCIA-PENDIENTE", "primary");
		this.coloresEstado.put("AUDIENCIA-ENCURSO", "warning");
		this.coloresEstado.put("AUDIENCIA-FINALIZADA", "success");
		this.coloresEstado.put("REGISTRADA", "black");
	}
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	@EJB
	public iAudienciaBean audienciaBean;
	
	@ManagedProperty(value = "#{modelAudiencia}")
	private ModelAudiencia modelAudiencia;
	
	private Map<String, String> coloresEstado;
	
	public ModelAudiencia getModelAudiencia() {
		return modelAudiencia;
	}

	public void setModelAudiencia(ModelAudiencia modelAudiencia) {
		this.modelAudiencia = modelAudiencia;
	}

	public void aplazarAudiencia(){
				
		Long id = this.modelAudiencia.getSelectSolicitud().get(0)+0L;
		List<Audiencia> audiencia = this.solicitudBean.findSolicitud(id).getAudiencias();
		Long idAudiencia = audiencia.get(audiencia.size()-1).getIdAudiencia();
		
		this.audienciaBean.actualizarEstadoAudiencia(idAudiencia, "APLAZADA");
		this.solicitudBean.actualizarEstadoSolicitud(id, "DESIGNACION");
	}
	
	public String seleccionarSolicitud(Long idSolicitud,String estado){
		for(int i=0;i<this.modelAudiencia.getSelectSolicitud().size();i++){
			if(this.modelAudiencia.getSelectSolicitud().get(i)==idSolicitud){
				return "seleccionar-solicitud-"+this.coloresEstado.get(estado);
			}
		}
		return "";
	}
	
	
	public String changeIconSelect(Long idSolicitud){
		
		for(int i=0;i<this.modelAudiencia.getSelectSolicitud().size();i++){
			if(this.modelAudiencia.getSelectSolicitud().get(i)==idSolicitud){
				return "fa-check";
			}
		}
		
		return "fa-square";
	}
	
	public boolean bloquearBoton(String estado1, String estado2){
		
		if(this.modelAudiencia.getSelectSolicitud().size()==0){
			return true;
		}else{
			Long id = this.modelAudiencia.getSelectSolicitud().get(0)+0L;
			String estadoSolicitud = this.solicitudBean.findSolicitud(id).getEstado();
			if(estadoSolicitud.equals(estado1) || estadoSolicitud.equals(estado2) ){
				return false;
			}
		}
	
		return true;
	}
	
	public boolean bloquearBotonAplazar(String estado1, String estado2){
		
		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			Long id = this.modelAudiencia.getSelectSolicitud().get(0)+0L;
			String estadoSolicitud = this.solicitudBean.findSolicitud(id).getEstado();
			if(estadoSolicitud.equals(estado1) || estadoSolicitud.equals(estado2) ){
				return false;
			}
		}
	
		return true;
	}	
	
	public boolean bloquearBotonExpedir(){
		if(this.modelAudiencia.getSelectSolicitud().size()==1){

			if(this.solicitudBean.findSolicitudEstado(this.modelAudiencia.getSelectSolicitud().get(0)).equals("AUDIENCIA-FINALIZADA")){
				
				return false;
			}
	
		}
		return true;	
	}
	
	public String nombreBotonActaConstancia(){
		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			Solicitud solicitud = this.solicitudBean.findSolicitud(this.modelAudiencia.getSelectSolicitud().get(0));
			
			if(solicitud.getAudiencias().get(solicitud.getAudiencias().size()-1).getResultados().size()==2){
				return "Acta";
			}else if(solicitud.getAudiencias().get(solicitud.getAudiencias().size()-1).getResultados().size()==1){
				if(solicitud.getAudiencias().get(solicitud.getAudiencias().size()-1).getResultados().get(0).getTipoResultado().equalsIgnoreCase("ACUERDO")){
					return "Acta";
				}else{
					return "Constancia";
				}
			}
		}
		return "Resultado";
	}
	
	public boolean bloquearBotonSubir(){
		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			if(this.solicitudBean.findSolicitudEstado(this.modelAudiencia.getSelectSolicitud().get(0)).equals("AUDIENCIA-FINALIZADA")){
				
				return false;
			}
	
		}
		return true;	
	}
	
	public boolean bloquearBotonDevolucion(){
		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			Solicitud solicitud = this.solicitudBean.findSolicitud(this.modelAudiencia.getSelectSolicitud().get(0));
			if(solicitud.getDevoluciones().size()==1){
				if(solicitud.getEstado().equals("AUDIENCIA-FINALIZADA") && !solicitud.getDevoluciones().get(0).isDevolucion()){
					return false;
				}
			}			
		}
		return true;	
	}

	@ManagedProperty(value = "#{fileUtilities}")
	private FileUtilities fileUtilities;
	
	public FileUtilities getFileUtilities() {
		return fileUtilities;
	}

	public void setFileUtilities(FileUtilities fileUtilities) {
		this.fileUtilities = fileUtilities;
	}

	public void GuardarActa(){
		
		
		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			Long idSolicitud=this.modelAudiencia.getSelectSolicitud().get(0);
			Solicitud solicitud = this.solicitudBean.findSolicitud(idSolicitud);


				if(this.fileUtilities.getFile()!=null){
					String path = "C:/Conalbos-Madiba/Solicitud #"+idSolicitud;
					String folderName = "Acta";
					//String fileName = nombreExcusa(this.files.get(i));
					String fileName = solicitud.getNroRadicado().toString();
					this.fileUtilities.createFolder(path,folderName);
					this.fileUtilities.upload(this.fileUtilities.getFile(),path+"/"+folderName,fileName);
					//Long idParte = Long.valueOf(this.fileUtilities.getNombresFile().get(i));
					//String ruta = path+"/"+folderName+"/"+idParte+this.fileUtilities.getFileExtention(this.fileUtilities.getFileName(this.fileUtilities.getFiles().get(i)));
					//this.audienciaBean.guardarEscusaParte(idAudiencia, idParte, ruta);
					//System.out.println(idAudiencia+"     -      "+idParte+"   -   "+ path+"/"+folderName);
				}

		}
		
	}
	
	public void downloadActa(){

		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			Long idSolicitud=this.modelAudiencia.getSelectSolicitud().get(0);
			Solicitud solicitud = this.solicitudBean.findSolicitud(idSolicitud);
		
			String path = "C:/Conalbos-Madiba/Solicitud #8/Acta/2017013000006.jpg";
			String name = solicitud.getNroRadicado().toString();
			this.fileUtilities.download(path, name);
		}
	}
	
	/**
	 * Devolucion del 70% del valor pagado por la solicotud si solo se realizo 
	 * una Audiencia y hubo Inacistencia
	 */
	public void devolucion(){
		Solicitud solicitud = this.solicitudBean.findSolicitud(this.modelAudiencia.getSelectSolicitud().get(0));
		
		Date fecha = new Date();
		boolean devolucion = true;

		Long idDevolucion = solicitud.getDevoluciones().get(0).getIdDevolucion();
		
		this.solicitudBean.actualizarDevolucion(idDevolucion, devolucion, fecha);
	}
}
