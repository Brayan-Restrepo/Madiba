package presentacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import entidades.Audiencia;
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
	
	private Solicitud solicitud;

	public ModelAudiencia getModelAudiencia() {
		return modelAudiencia;
	}

	public void setModelAudiencia(ModelAudiencia modelAudiencia) {
		this.modelAudiencia = modelAudiencia;
	}
	
	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	/**
	 * Consulta en la DB la Solicitud de la Audiencia
	 * @param id -> idSolicitud
	 * @return Solicitud
	 */
	public void findSolicitud(Long id){
		this.solicitud = this.solicitudBean.findSolicitud(id);
	}
	
	public void cargarDatosSolicitud(Long id){
		this.solicitud = this.solicitudBean.findSolicitud(id);
		List<Long[]> listaAsistencias= new ArrayList<Long[]>();
		for(int i=0;i<this.solicitud.getPartes().size();i++){
			Long[] valAsistencia= new Long[2];
			valAsistencia[0] = this.solicitud.getPartes().get(i).getIdParte();
			valAsistencia[1] = 0L;
			listaAsistencias.add(valAsistencia);
		}
		this.modelAudiencia.setListaAsistencias(listaAsistencias);
	}
	
	/**
	 * 	Se Añade El resultado de La Audiencia Solo si es Finalzada (Fin da las Audiecias) 
	 * 
	 * @param estdoAudiencia -> FINALIZADA, APLAZADA, SUSPENDIDA
	 */
	public void addResultado(String estdoAudiencia){
		
		//Tipo de Resultado - Acuerdo - No Acuerdo - No Conciliabre
		String tipoResultado = this.modelAudiencia.getTipoResultado();
		//La Conclucion de La Audiencia
		String observacion = this.modelAudiencia.getObservacion();

		// Si es Acuerdo Parcial Hay Acuerdos y Desacuerdos
		String acuerdos = this.modelAudiencia.getAcuerdo();
		String noacuerdos = this.modelAudiencia.getNoAcuerdo();
		
		//La Ultima Audiencia de esa Solicitud. Una Solicitud puede tener Varias Audiencias
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;

		System.out.println(this.modelAudiencia.isAcuerdoParcial());
		//Sia hay un Acuerdo Parcial, es decir Hay Acuerdos pero tambien desacuerdos
		if(estdoAudiencia.equals("FINALIZADA")){
			if(this.modelAudiencia.isAcuerdoParcial()){
				if((acuerdos!="" && acuerdos!=null) && (noacuerdos!="" && noacuerdos!=null)){
					this.audienciaBean.addResultado("NOACUERDO", this.solicitud.getAudiencias().get(lastAudiencia), noacuerdos, this.solicitud.getIdSolicitud());
					this.audienciaBean.addResultado("ACUERDO", this.solicitud.getAudiencias().get(lastAudiencia), acuerdos, this.solicitud.getIdSolicitud());
				}
			}else{
				if((observacion!="" && observacion!=null) && tipoResultado!=null && tipoResultado!=""){
					this.audienciaBean.addResultado(tipoResultado, this.solicitud.getAudiencias().get(lastAudiencia), observacion, this.solicitud.getIdSolicitud());
				}
			}
		}
		//return "listaaudiencias";
	}
	
	public void suspenderAudiencia(){
		System.out.println("-------------------> "+"DESIGNACION");
		this.solicitudBean.actualizarEstadoSolicitud(this.solicitud.getIdSolicitud(), "DESIGNACION");
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;
		this.audienciaBean.actualizarEstadoAudiencia(this.solicitud.getAudiencias().get(lastAudiencia).getIdAudiencia(), "SUSPENDIDA");
		
		String observacion = this.modelAudiencia.getObservacion();
		this.audienciaBean.addResultadoSuspender("SUSPENDIDA", this.solicitud.getAudiencias().get(lastAudiencia), observacion, this.solicitud.getIdSolicitud());
	}

	public void aplazarAudiencia(){
				
		Long id = this.modelAudiencia.getSelectSolicitud().get(0)+0L;
		List<Audiencia> audiencia = this.solicitudBean.findSolicitud(id).getAudiencias();
		Long idAudiencia = audiencia.get(audiencia.size()-1).getIdAudiencia();
		
		this.audienciaBean.actualizarEstadoAudiencia(idAudiencia, "APLAZADA");
		this.solicitudBean.actualizarEstadoSolicitud(id, "DESIGNACION");
	}
	
	/**
	 * 	Se Añade El resultado de La Audiencia Solo si es Finalzada (Fin da las Audiecias) 
	 * 
	 * @param estdoAudiencia -> FINALIZADA, APLAZADA, SUSPENDIDA
	 */
	public void guardarAsistencia(){
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;
		Audiencia audiencia = this.solicitud.getAudiencias().get(lastAudiencia);
		for(int i=0;i<this.modelAudiencia.getListaAsistencias().size();i++){
			Long idParte = this.modelAudiencia.getListaAsistencias().get(i)[0];
			Boolean valAsistencia = false;
			if(this.modelAudiencia.getListaAsistencias().get(i)[1] == 1L) valAsistencia = true;
			this.audienciaBean.addAsistencia(audiencia,idParte,valAsistencia);
		}
		this.GuardarExcusas();
	}
	
	
	public void addAsistencia(Long idParte){
		List<Long[]> listaAsistencias = new ArrayList<Long[]>();
		Long valor = 0L;
		int posicion = 0;
		for(int i=0;i<this.modelAudiencia.getListaAsistencias().size();i++){
			if(this.modelAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelAudiencia.getListaAsistencias().get(i)[1];
				posicion = i;
			}
			listaAsistencias.add(this.modelAudiencia.getListaAsistencias().get(i));
		}
		if(valor==2L){
			listaAsistencias.get(posicion)[1] = 0L;
			System.out.println("Entre 2");
		}else{
			listaAsistencias.get(posicion)[1] = valor+1L;
			System.out.println("Entre 0, 1");
		}
		
		this.modelAudiencia.setListaAsistencias(listaAsistencias);
	}
	
	public String changeIcon(Long idParte){
		String icono="fa-square";
		Long valor = 0L;
		for(int i=0;i<this.modelAudiencia.getListaAsistencias().size();i++){
			if(this.modelAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelAudiencia.getListaAsistencias().get(i)[1];
				break;
			}
		}
		//System.out.println("asdasdasdasdasdasdasds  "+valor);
		if(valor==0){
			icono = "fa-square";
		}
		if(valor==1){
			icono = "fa-check";
		}
		if(valor==2){
			icono = "fa-close";
		}
		return icono;
	}
	
	public String changeColorButton(Long idParte){
		String color="warning";
		Long valor = 0L;
		for(int i=0;i<this.modelAudiencia.getListaAsistencias().size();i++){
			if(this.modelAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelAudiencia.getListaAsistencias().get(i)[1];
				break;
			}
		}
		if(valor==0){
			color = "warning";
		}
		if(valor==1){
			color = "success";
		}
		if(valor==2){
			color = "danger";
		}
		return color;
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
	
	/**
	 * Activa el Boton finalizar DesarrolloAudiencia
	 */
	public boolean activarBotonFinalizar(){
		if(this.modelAudiencia.isAcuerdoParcial()){
			return false;
		}
		if(this.modelAudiencia.getTipoResultado()=="" || modelAudiencia.getTipoResultado()==null){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * Activa el Boton Suspender DesarrolloAudiencia
	 */
	public boolean activarBotonSuspender(Boolean hayAsistencia, Boolean hayInasistencia){
		if(hayAsistencia){
			return false;
		}else{
			if(hayInasistencia){
				return true;
			}else{
				if(this.modelAudiencia.isAcuerdoParcial()){
					return true;
				}
				if(this.modelAudiencia.getTipoResultado()=="" || modelAudiencia.getTipoResultado()==null){
					return false;
				}else{
					return true;
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	

	@ManagedProperty(value = "#{fileUtilities}")
	private FileUtilities fileUtilities;
	
	public FileUtilities getFileUtilities() {
		return fileUtilities;
	}

	public void setFileUtilities(FileUtilities fileUtilities) {
		this.fileUtilities = fileUtilities;
	}

	public void GuardarExcusas(){
		Long idSolicitud=this.solicitud.getIdSolicitud();
		Long idAudiencia=this.solicitud.getAudiencias().get(this.solicitud.getAudiencias().size()-1).getIdAudiencia();
		
		if(this.fileUtilities.getNombresFile().size()==this.fileUtilities.getFiles().size()){
			for(int i=0; i<this.fileUtilities.getFiles().size(); i++){
				if(this.fileUtilities.getFiles().get(i)!=null){
					String path = "C:/Conalbos-Madiba/Solicitud #"+idSolicitud+"/Audiencia #"+idAudiencia;
					String folderName = "Excusas";
					//String fileName = nombreExcusa(this.files.get(i));
					String fileName = this.fileUtilities.getNombresFile().get(i);
					this.fileUtilities.createFolder(path,folderName);
					this.fileUtilities.upload(this.fileUtilities.getFiles().get(i),path+"/"+folderName,fileName);
					Long idParte = Long.valueOf(this.fileUtilities.getNombresFile().get(i));
					String ruta = path+"/"+folderName+"/"+idParte+this.fileUtilities.getFileExtention(this.fileUtilities.getFileName(this.fileUtilities.getFiles().get(i)));
					this.audienciaBean.guardarEscusaParte(idAudiencia, idParte, ruta);
					//System.out.println(idAudiencia+"     -      "+idParte+"   -   "+ path+"/"+folderName);
				}
			}
		}
	}
	public String nombreExcusa(Part file){
		String nombre = file.getName();
		nombre = nombre.split("[:]")[nombre.split("[:]").length-1];
		return nombre;
	}
}
