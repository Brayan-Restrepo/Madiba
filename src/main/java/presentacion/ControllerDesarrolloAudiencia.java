package presentacion;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import entidades.Audiencia;
import entidades.Devolucione;
import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iSolicitudBean;


@ManagedBean
@ViewScoped
public class ControllerDesarrolloAudiencia {
	
	public ControllerDesarrolloAudiencia(){	}
	
	private Solicitud solicitud;
	
	@EJB
	public iAudienciaBean audienciaBean;
	
	@EJB
	public iSolicitudBean solicitudBean;
	
	@ManagedProperty(value = "#{modelDesarrolloAudiencia}")
	private ModelDesarrolloAudiencia modelDesarrolloAudiencia;

	@ManagedProperty(value = "#{modelLogin}")
	private ModelLogin modelLogin;
	
	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public ModelLogin getModelLogin() {
		return modelLogin;
	}

	public void setModelLogin(ModelLogin modelLogin) {
		this.modelLogin = modelLogin;
	}

	public ModelDesarrolloAudiencia getModelDesarrolloAudiencia() {
		return modelDesarrolloAudiencia;
	}

	public void setModelDesarrolloAudiencia(ModelDesarrolloAudiencia modelDesarrolloAudiencia) {
		this.modelDesarrolloAudiencia = modelDesarrolloAudiencia;
	}

	public void cargarAsistencia(Long idAudiencia){
		Audiencia audiencia = this.audienciaBean.findAudienciaResultadoAsistenia(idAudiencia);
		List<String[]> listParte = new ArrayList<String[]>();
		for(int i=0;i<audiencia.getAsistencias().size();i++){
			String[] asistenciaVector = new String[5];
			asistenciaVector[0]=audiencia.getAsistencias().get(i).getParte().getTipoParte();
			asistenciaVector[1]=audiencia.getAsistencias().get(i).getParte().getApellidos()+" "+audiencia.getAsistencias().get(i).getParte().getNombres();
			asistenciaVector[2]=String.valueOf(audiencia.getAsistencias().get(i).getAsistio());
			asistenciaVector[3]=String.valueOf(audiencia.getAsistencias().get(i).getParte().getIdParte());
			asistenciaVector[4]=audiencia.getAsistencias().get(i).getExcusa();
			listParte.add(asistenciaVector);
		}
		this.modelDesarrolloAudiencia.setAsistencia(listParte);
		
	}
	public void cargarDatosDesarrolloAudiencia(Long idAudiencia){
		Audiencia audiencia = this.audienciaBean.findAudienciaResultadoAsistenia(idAudiencia);
		this.cargarAsistencia(idAudiencia);
		if(audiencia.getResultados().size()==1){
			this.modelDesarrolloAudiencia.setAcuerdoParcial(false);
			this.modelDesarrolloAudiencia.setObservacion(audiencia.getResultados().get(0).getConclusion());
			this.modelDesarrolloAudiencia.setTipoResultado(audiencia.getResultados().get(0).getTipoResultado());
		}else if(audiencia.getResultados().size()==2){
			this.modelDesarrolloAudiencia.setAcuerdoParcial(true);
			for(int i=0;i<audiencia.getResultados().size();i++){
				if(audiencia.getResultados().get(i).getTipoResultado().equals("NOACUERDO")){
					this.modelDesarrolloAudiencia.setNoAcuerdo(audiencia.getResultados().get(i).getConclusion());
				}else if(audiencia.getResultados().get(i).getTipoResultado().equals("ACUERDO")){
					this.modelDesarrolloAudiencia.setAcuerdo(audiencia.getResultados().get(i).getConclusion());
				}
			}
			
		}
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
		this.modelDesarrolloAudiencia.setListaAsistencias(listaAsistencias);
	}
	
	
	
	/**
	 * 	Se Añade El resultado de La Audiencia Solo si es Finalzada (Fin da las Audiecias) 
	 * 
	 * @param estdoAudiencia -> FINALIZADA, APLAZADA, SUSPENDIDA
	 */
	public void addResultado(String estdoAudiencia){
		Boolean hayAsistencia = this.hayAsistencias();
		Boolean hayInasistencia = this.hayInasistencias();

		//La Ultima Audiencia de esa Solicitud. Una Solicitud puede tener Varias Audiencias
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;
		
		if(hayAsistencia && hayInasistencia){//SI HAY INASISTENCIAS SE FINALIZA LA CONCILIACION
			this.audienciaBean.actualizarEstadoAudiencia(this.solicitud.getAudiencias().get(lastAudiencia).getIdAudiencia(), "INASISTENCIA");
			
			this.solicitudBean.actualizarEstadoSolicitud(this.solicitud.getIdSolicitud(), "AUDIENCIA-FINALIZADA");
			
			this.audienciaBean.addResultado("INASISTENCIA", this.solicitud.getAudiencias().get(lastAudiencia), "INASISTENCIA", this.solicitud.getIdSolicitud());
			if(this.solicitud.getAudiencias().get(lastAudiencia).getAudienciaNum()==1){
				
				Devolucione devolucione = new Devolucione();
				//Date fechaActual = new Date();
				//devolucione.setFecha(fechaActual);
				devolucione.setValor(this.solicitud.getValorPagar()*0.7);
				devolucione.setDevolucion(false);
				devolucione.setSolicitud(this.solicitud);
				
				this.solicitudBean.addDevolucion(devolucione);
			}
		}else{
			//Tipo de Resultado - Acuerdo - No Acuerdo - No Conciliabre
			String tipoResultado = this.modelDesarrolloAudiencia.getTipoResultado();
			//La Conclucion de La Audiencia
			String observacion = this.modelDesarrolloAudiencia.getObservacion();
	
			// Si es Acuerdo Parcial Hay Acuerdos y Desacuerdos
			String acuerdos = this.modelDesarrolloAudiencia.getAcuerdo();
			String noacuerdos = this.modelDesarrolloAudiencia.getNoAcuerdo();
			
	
			System.out.println(this.modelDesarrolloAudiencia.isAcuerdoParcial());
			//Sia hay un Acuerdo Parcial, es decir Hay Acuerdos pero tambien desacuerdos
			if(estdoAudiencia.equals("FINALIZADA")){
				if(this.modelDesarrolloAudiencia.isAcuerdoParcial()){
					if((acuerdos!="" && acuerdos!=null) && (noacuerdos!="" && noacuerdos!=null)){					
						this.audienciaBean.actualizarEstadoAudiencia(this.solicitud.getAudiencias().get(lastAudiencia).getIdAudiencia(), "FINALIZADA");
	
						this.solicitudBean.actualizarEstadoSolicitud(this.solicitud.getIdSolicitud(), "AUDIENCIA-FINALIZADA");
						
						this.audienciaBean.addResultado("NOACUERDO", this.solicitud.getAudiencias().get(lastAudiencia), noacuerdos, this.solicitud.getIdSolicitud());
						this.audienciaBean.addResultado("ACUERDO", this.solicitud.getAudiencias().get(lastAudiencia), acuerdos, this.solicitud.getIdSolicitud());
					}
				}else{
					if((observacion!="" && observacion!=null) && tipoResultado!=null && tipoResultado!=""){
						this.audienciaBean.actualizarEstadoAudiencia(this.solicitud.getAudiencias().get(lastAudiencia).getIdAudiencia(), "FINALIZADA");
						
						this.solicitudBean.actualizarEstadoSolicitud(this.solicitud.getIdSolicitud(), "AUDIENCIA-FINALIZADA");
						
						this.audienciaBean.addResultado(tipoResultado, this.solicitud.getAudiencias().get(lastAudiencia), observacion, this.solicitud.getIdSolicitud());
					}
				}
			}
		}
		//return "listaaudiencias";
	}
	
	public void reprogramarAudiencia(){
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;
		this.solicitudBean.actualizarEstadoSolicitud(this.solicitud.getIdSolicitud(), "DESIGNACION");
		this.audienciaBean.actualizarEstadoAudiencia(this.solicitud.getAudiencias().get(lastAudiencia).getIdAudiencia(), "REPROGRAMADA");
	}
	
	public void suspenderAudiencia(){
		System.out.println("-------------------> "+"DESIGNACION");
		this.solicitudBean.actualizarEstadoSolicitud(this.solicitud.getIdSolicitud(), "DESIGNACION");
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;
		this.audienciaBean.actualizarEstadoAudiencia(this.solicitud.getAudiencias().get(lastAudiencia).getIdAudiencia(), "SUSPENDIDA");
		
		String observacion = this.modelDesarrolloAudiencia.getObservacion();
		this.audienciaBean.addResultadoSuspender("SUSPENDIDA", this.solicitud.getAudiencias().get(lastAudiencia), observacion, this.solicitud.getIdSolicitud());
	}
	
	/**
	 * 	Se Añade El resultado de La Audiencia Solo si es Finalzada (Fin da las Audiecias) 
	 * 
	 * @param estdoAudiencia -> FINALIZADA, APLAZADA, SUSPENDIDA
	 */
	public void guardarAsistencia(){
		int lastAudiencia = this.solicitud.getAudiencias().size()-1;
		Audiencia audiencia = this.solicitud.getAudiencias().get(lastAudiencia);
		for(int i=0;i<this.modelDesarrolloAudiencia.getListaAsistencias().size();i++){
			Long idParte = this.modelDesarrolloAudiencia.getListaAsistencias().get(i)[0];
			Boolean valAsistencia = false;
			if(this.modelDesarrolloAudiencia.getListaAsistencias().get(i)[1] == 1L) valAsistencia = true;
			this.audienciaBean.addAsistencia(audiencia,idParte,valAsistencia);
		}
		this.GuardarExcusas();
	}
	
	
	public void addAsistencia(Long idParte){
		List<Long[]> listaAsistencias = new ArrayList<Long[]>();
		Long valor = 0L;
		int posicion = 0;
		for(int i=0;i<this.modelDesarrolloAudiencia.getListaAsistencias().size();i++){
			if(this.modelDesarrolloAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelDesarrolloAudiencia.getListaAsistencias().get(i)[1];
				posicion = i;
			}
			listaAsistencias.add(this.modelDesarrolloAudiencia.getListaAsistencias().get(i));
		}
		if(valor==2L){
			listaAsistencias.get(posicion)[1] = 0L;
			System.out.println("Entre 2");
		}else{
			listaAsistencias.get(posicion)[1] = valor+1L;
			System.out.println("Entre 0, 1");
		}
		
		this.modelDesarrolloAudiencia.setListaAsistencias(listaAsistencias);
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
	
	/**
	 ******************************************************************
	 ******************Aciones Botones********************************
	 ******************************************************************
	 */
	public boolean hayAsistencias(){
		Long idAudiencia = this.solicitud.getAudiencias().get(this.solicitud.getAudiencias().size()-1).getIdAudiencia();
		Audiencia audiencia = this.audienciaBean.findAudienciaResultadoAsistenia(idAudiencia);
		if(audiencia.getAsistencias().size()==0){
			return false;
		}
		return true;
	}
	
	/**
	 * SI hay inasistencia return true
	 * @return
	 */
	public boolean hayInasistencias(){
		Long idAudiencia = this.solicitud.getAudiencias().get(this.solicitud.getAudiencias().size()-1).getIdAudiencia();
		Audiencia audiencia = this.audienciaBean.findAudienciaResultadoAsistenia(idAudiencia);
		Boolean inasistencia = false;
		for(int i=0; i<audiencia.getAsistencias().size(); i++){
			if(!audiencia.getAsistencias().get(i).getAsistio()){
				inasistencia = true;
				break;
			}
		}
		return inasistencia;
	}
	
	public boolean bloquearComponete(){
		Boolean hayAsistencia = this.hayAsistencias();
		Boolean hayInasistencia = this.hayInasistencias();
		if(hayAsistencia==false || hayInasistencia){
			return true;
		}
		return false;
	}

	public Boolean activarBotonGuardarExcusa(){
		if(this.hayAsistencias() && this.hayInasistencias()){
			return false;
		}else{
			return true;
		}
		
	}
	
	public Boolean activarBotonReprogramar(){
		if(this.hayAsistencias() && this.hayInasistencias()){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * Activa el Boton finalizar DesarrolloAudiencia
	 */
	public boolean activarBotonFinalizar(){
		Boolean hayAsistencia = this.hayAsistencias();
		Boolean hayInasistencia = this.hayInasistencias();
		if(hayAsistencia && hayInasistencia){
			return false;
		}
		if(this.modelDesarrolloAudiencia.isAcuerdoParcial()){
			return false;
		}
		if(this.modelDesarrolloAudiencia.getTipoResultado()=="" || this.modelDesarrolloAudiencia.getTipoResultado()==null){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * Activa el Boton Suspender DesarrolloAudiencia
	 */
	public boolean activarBotonSuspender(){
		Boolean hayAsistencia = this.hayAsistencias();
		Boolean hayInasistencia = this.hayInasistencias();
		if(hayAsistencia==false || hayInasistencia){
			return true;
		}else{
			if(this.modelDesarrolloAudiencia.isAcuerdoParcial()){
				return true;
			}
			if(this.modelDesarrolloAudiencia.getTipoResultado()=="" || modelDesarrolloAudiencia.getTipoResultado()==null){
				return false;
			}else{
				return true;
			}
		}
	}
	
	/**
	 * Bloquea El boton Desarrollar audiencia de la vista ListaAudiencias 
	 * @param selectSolicitud
	 * @return
	 */
	public boolean bloquearBotonDesarrollarAudiencia(List<Long> selectSolicitud){
		
		if(selectSolicitud.size()==0){
			return true;
		}else{
			Long id = selectSolicitud.get(0)+0L;
			Solicitud solicitud = this.solicitudBean.findSolicitud(id);
			if(solicitud.getEstado().equals("AUDIENCIA-ENCURSO") ){
				if(this.modelLogin.getRole().equals("conalbos")){
				//	System.out.println("True");
					return true;
				}else{
					//System.out.println("False");
					return false;
				}
			}
		}
	
		return true;
	}

	public String changeIcon(Long idParte){
		String icono="fa-square";
		Long valor = 0L;
		for(int i=0;i<this.modelDesarrolloAudiencia.getListaAsistencias().size();i++){
			if(this.modelDesarrolloAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelDesarrolloAudiencia.getListaAsistencias().get(i)[1];
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
		for(int i=0;i<this.modelDesarrolloAudiencia.getListaAsistencias().size();i++){
			if(this.modelDesarrolloAudiencia.getListaAsistencias().get(i)[0]==idParte){
				valor = this.modelDesarrolloAudiencia.getListaAsistencias().get(i)[1];
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
	
	
}
