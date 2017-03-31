package presentacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Actas_Conciliacione;
import entidades.Audiencia;
import entidades.Copia;
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
	
	@ManagedProperty(value = "#{modelLogin}")
	private ModelLogin modelLogin;
	
	@ManagedProperty(value = "#{modelBusqueda}")
	private ModelBusqueda modelBusqueda;
	
	private Map<String, String> coloresEstado;
	
	//Contiene la lista de solicitudes con datos quemados
	public List<Solicitud> listaSolicitud;

	
	public ModelLogin getModelLogin() {
		return modelLogin;
	}

	public void setModelLogin(ModelLogin modelLogin) {
		this.modelLogin = modelLogin;
	}

	public ModelBusqueda getModelBusqueda() {
		return modelBusqueda;
	}

	public void setModelBusqueda(ModelBusqueda modelBusqueda) {
		this.modelBusqueda = modelBusqueda;
	}

	public List<Solicitud> getListaSolicitud() {
		return listaSolicitud;
	}

	public void setListaSolicitud(List<Solicitud> listaSolicitud) {
		this.listaSolicitud = listaSolicitud;
	}

	public ModelAudiencia getModelAudiencia() {
		return modelAudiencia;
	}

	public void setModelAudiencia(ModelAudiencia modelAudiencia) {
		this.modelAudiencia = modelAudiencia;
	}

	
	
	/**
	 * 
	 */
	public void findAudiencias(){
		String ccParte = this.modelBusqueda.getCc();
		String tipoParte = this.modelBusqueda.getTipoParte();
		
		if((this.modelBusqueda.getFechaInicio() == null || this.modelBusqueda.getFechaInicio().equals("")) && 
				(this.modelBusqueda.getFechaFinal() == null || this.modelBusqueda.getFechaFinal().equals("")) && 
				(ccParte == null || ccParte.equals(""))){
			this.listaSolicitud = this.solicitudBean.findAudiencias(this.modelLogin.getRole(), this.modelLogin.getIdConciliador());
		}else{
			if(ccParte == null || ccParte.equals("")){
				this.listaSolicitud = this.solicitudBean.findAudiencias(this.modelLogin.getRole(), this.modelLogin.getIdConciliador());
			}else{
				if((this.modelBusqueda.getFechaInicio() != null && !this.modelBusqueda.getFechaInicio().equals("")) && 
						(this.modelBusqueda.getFechaFinal() != null && !this.modelBusqueda.getFechaFinal().equals(""))){
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaInicial = null;
					Date fechaFinal = null;
					try {
						 fechaInicial = formatoDelTexto.parse(this.modelBusqueda.getFechaInicio());
						 fechaFinal = formatoDelTexto.parse(this.modelBusqueda.getFechaFinal());
					} catch (ParseException ex) {
					     ex.printStackTrace();
					}
					if(tipoParte.equals("Conciliador")){
						this.listaSolicitud = this.solicitudBean.findAudienciasFiltroConciliadorFecha(this.modelLogin.getRole(), this.modelLogin.getIdConciliador(),fechaInicial, fechaFinal, ccParte);
					}else {
						this.listaSolicitud = this.solicitudBean.findAudienciasFiltroParteFecha(this.modelLogin.getRole(), this.modelLogin.getIdConciliador(),fechaInicial, fechaFinal, ccParte, tipoParte);
					}
				}else {
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaInicial = null;
					Date fechaFinal = null;
					
					Date fechaActual = new Date();
			    	Calendar calendar = Calendar.getInstance();
			    	calendar.setTime(fechaActual);
			    	calendar.add(Calendar.MONTH, -3); 
					try {
						 fechaInicial = formatoDelTexto.parse(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
						 fechaFinal = formatoDelTexto.parse(new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
					} catch (ParseException ex) {
					     ex.printStackTrace();
					}
					if(tipoParte.equals("Conciliador")){
						this.listaSolicitud = this.solicitudBean.findAudienciasFiltroConciliadorFecha(this.modelLogin.getRole(), this.modelLogin.getIdConciliador(),fechaInicial, fechaFinal, ccParte);
					}else {
						this.listaSolicitud = this.solicitudBean.findAudienciasFiltroParteFecha(this.modelLogin.getRole(), this.modelLogin.getIdConciliador(),fechaInicial, fechaFinal, ccParte, tipoParte);
					}
				}
			}
		}
	}
	
	/**
	 * Filtra las solicitudes por su estado
	 * 
	 * @param El estado de la Solicitud (Grabada -> Pagada -> Radicada -> Designacion -> Audiencia)
	 * @return
	 */
	
	public List<Solicitud> solicitudesPorEstado(String estado){
		List<Solicitud> listaPorEstado = new ArrayList<Solicitud>();
		int size = this.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(this.listaSolicitud.get(i).getEstado().equals(estado)){
				listaPorEstado.add(this.listaSolicitud.get(i));
			}
		}
		return listaPorEstado;
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
			Solicitud solicitud=this.solicitudBean.findSolicitud(this.modelAudiencia.getSelectSolicitud().get(0));
			
			if(solicitud.getEstado().equals("AUDIENCIA-FINALIZADA") && solicitud.getActasConciliaciones().size()==0){
				return false;
			}
	
		}
		return true;	
	}
	
	public boolean bloquearBotonExpedirCopia(){
		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			Solicitud solicitud=this.solicitudBean.findSolicitud(this.modelAudiencia.getSelectSolicitud().get(0));
			
			if(solicitud.getEstado().equals("AUDIENCIA-FINALIZADA") && solicitud.getActasConciliaciones().size()==1){
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
					
					String folderName="Resultado";
					
					if(solicitud.getAudiencias().get(solicitud.getAudiencias().size()-1).getResultados().size()==2){
						folderName= "Acta";
					}else if(solicitud.getAudiencias().get(solicitud.getAudiencias().size()-1).getResultados().size()==1){
						if(solicitud.getAudiencias().get(solicitud.getAudiencias().size()-1).getResultados().get(0).getTipoResultado().equalsIgnoreCase("ACUERDO")){
							folderName= "Acta";
						}else{
							folderName= "Constancia";
						}
					}
					
					String path = "C:/Conalbos-Madiba/Solicitud #"+idSolicitud;
					//String folderName = "Resultado";
					//String fileName = nombreExcusa(this.files.get(i));
					String fileName = solicitud.getNroRadicado().toString();
					this.fileUtilities.createFolder(path,folderName);
					this.fileUtilities.upload(this.fileUtilities.getFile(),path+"/"+folderName,fileName);
					
					Actas_Conciliacione actaConstancia = new Actas_Conciliacione();
					
					String ruta = path+"/"+folderName+"/"+fileName+this.fileUtilities.getFileExtention(this.fileUtilities.getFileName(this.fileUtilities.getFile()));
					Date fecha = new Date();
					
					actaConstancia.setFechaExpedicion(fecha);
					actaConstancia.setRuta(ruta);
					actaConstancia.setLimiteCopias(3);
					actaConstancia.setSolicitud(solicitud);
					actaConstancia.setRuta(ruta);
					actaConstancia.setTipo(folderName);
					
					this.solicitudBean.guardarActaConstancia(actaConstancia);
				}

		}
		
	}
	
	public void downloadActa(){

		if(this.modelAudiencia.getSelectSolicitud().size()==1){
			
			Long idSolicitud=this.modelAudiencia.getSelectSolicitud().get(0);
			Solicitud solicitud = this.solicitudBean.findSolicitud(idSolicitud);
			
			//Solo se permite descargar 3 veces
			if(solicitud.getActasConciliaciones().get(0).getCopias().size() < solicitud.getActasConciliaciones().get(0).getLimiteCopias()){
				String path = solicitud.getActasConciliaciones().get(0).getRuta();
				
				String name = solicitud.getNroRadicado().toString();
				this.fileUtilities.download(path, name);
				
				Copia copia = new Copia();
				Date fecha = new Date();
				copia.setActasConciliacione(solicitud.getActasConciliaciones().get(0));
				copia.setFechaGeneracion(fecha);
				copia.setNumCopia(solicitud.getActasConciliaciones().get(0).getCopias().size()+1);
				this.solicitudBean.guardarCopia(copia);
			}
			
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
