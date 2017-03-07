package presentacion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import entidades.Audiencia;
import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iConciliadorBean;
import negocio.iSolicitudBean;



@ManagedBean
@ViewScoped
public class ControllerSolicitud {

	/**
	 * 
	 */
	@ManagedProperty(value = "#{modelSolicitud}")
	private ModelSolicitud consultaModelSolicitud;
	
	//Contiene la lista de solicitudes con datos quemados
	public List<Solicitud> listaSolicitud;

	@EJB
	public iSolicitudBean solicitudBean;
	
	@EJB
	public iAudienciaBean audienciaBean;
	
	private Map<String, String> coloresEstado;
	

	
	/**
	 * Constructor: 
	 */
	public ControllerSolicitud(){
		System.out.println("Constructor Inicializado");
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
	
	/*
	public List<Solicitud> allSolicitudes(String estado){
		this.listaSolicitud = this.solicitudBean.allSolicitud(estado);
		return this.listaSolicitud;
	}
	*/
	public void findSolicitudes(){
		this.listaSolicitud = this.solicitudBean.findSolicitudes();
	}
	public void findAudiencias(){
		this.listaSolicitud = this.solicitudBean.findAudiencias();
	}
		
	public List<Solicitud> getListaSolicitud() {
		return listaSolicitud;
	}


	public void setListaSolicitud(List<Solicitud> listaSolicitud) {
		this.listaSolicitud = listaSolicitud;
	}
	
	public ModelSolicitud getConsultaModelSolicitud() {
		return consultaModelSolicitud;
	}

	public void setConsultaModelSolicitud(ModelSolicitud consultaModelSolicitud) {
		this.consultaModelSolicitud = consultaModelSolicitud;
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
	
	
	/**
	 * Cambia el estado de la solicitud despues de ser gestionada
	 * @param evento
	 * @param id
	 */
	
	public void cambiarEstado(AjaxBehaviorEvent evento, int id){
		String[] estados = {"Grabada","Pagada","Radicada","Designacion","Audiencia"};
		int size = this.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(this.listaSolicitud.get(i).getIdSolicitud() == id){
				String estadoActual = this.listaSolicitud.get(i).getEstado();
				String nuevoEstado = "";
				for(int j=0; j<estados.length; j++){
					if(estados[j].equals(estadoActual)){
						nuevoEstado = estados[j+1];
					}
				}
				this.listaSolicitud.get(i).setEstado(nuevoEstado);
				//Designa el conciliador y actualiza la lista
				if(estadoActual.equals("Designacion")){
					ControllerConciliador conciliador = new ControllerConciliador();
					//conciliador.designarConciliador(this.listaSolicitud.get(i).getConciliador());
				}
			}
		}
	}

	/**
	 * Define el color de la ficha segun su estado
	 * @param id
	 * @param tipoComponente
	 * @return
	 */
	// YA no se Esta Utilizando
	public String colorFicha(int id, String tipoComponente){
		String[][] matrizColores = {
			{"GRABADA","info"}, //Azul claro
			{"PAGADA","primary"}, //Azul rey
			{"RADICADA","warning"}, //Naranja
			{"DESIGNACION","success"}, //verde
			{"AUDIENCIA-CITACION","success"},
			{"AUDIENCIA-PENDIENTE","success"},
			{"AUDIENCIA-ENCURSO","success"},
			{"AUDIENCIA-FINALIZADA","success"},
						
		}; //Verde
		String color = "";
		int size = this.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(this.listaSolicitud.get(i).getIdSolicitud() == id){
				String estadoActual = this.listaSolicitud.get(i).getEstado();
				for(int j=0; j<matrizColores.length; j++){
					if(matrizColores[j][0].equals(estadoActual)){
						color = tipoComponente+"-"+matrizColores[j][1];
					}
				}
			}
		}
		return color;
	}

	/**
	 * Retorna la solicitud siguiente del mismo estado
	 * de acuerdo al parametro id.
	 *
	 * @param id Es el id de la Ficha Actual
	 * @return La siguiente Solicitud con el mismo estado de id
	 */
	public Solicitud siguienteFicha(int id){
		return this.listaSolicitud.get(id+1);
	}

	/**
	 * Busca si hay mas fichas con el mismo estado
	 * @param Se envia el estado por el cual se desea buscar
	 * @return Retorna falso si no hay mas, true si encontro otra
	 */
	public boolean masFichas(int id, String estado){
		
		int size = this.listaSolicitud.size();
		for(int i=id-1; i<size; i++){
			if(this.listaSolicitud.get(i).getEstado().equalsIgnoreCase(estado)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca la siguiente solicitud que halla con el mismo estado
	 * @param Se envia el id de la ficha actual
	 * @return Retorna la siguiente solicitud con el mismo estado
	 */
	public Solicitud siguienteFichaEstado(int id, String estado){
		
		int size = this.listaSolicitud.size();
		for(int i=id; i<size; i++){
			if(this.listaSolicitud.get(i).getEstado().equals(estado)){
				return this.listaSolicitud.get(i);
			}	
		}
		return null;
	}
	
	public String seleccionarSolicitud(Long idSolicitud,String estado){
		for(int i=0;i<this.consultaModelSolicitud.getSelectSolicitud().size();i++){
			if(this.consultaModelSolicitud.getSelectSolicitud().get(i)==idSolicitud){
				return "seleccionar-solicitud-"+this.coloresEstado.get(estado);
			}
		}
		return "";
	}
	
	public void verString(Object ver){
		System.out.println("---------------------------------> "+ver);
	}
	
	public Solicitud liquidar(){
		/*for(int i=0;i<this.listaSolicitud.size();i++ ){
			if(this.listaSolicitud.get(i).isSelect() && this.listaSolicitud.get(i).getEstado().equals("Grabada")){
				return this.listaSolicitud.get(i);
			}
		}*/
		return null;
	}
	public Solicitud designarConciliador(){
		/*
		for(int i=0;i<this.listaSolicitud.size();i++ ){
			if(this.listaSolicitud.get(i).isSelect() && this.listaSolicitud.get(i).getEstado().equals("Radicada")){
				return this.listaSolicitud.get(i);
			}
		}*/
		return null;
	}
		
	public String classEstadoHidden(String estado){	
		/*for(int i=0;i<this.listaSolicitud.size();i++){
			if(this.listaSolicitud.get(i).getEstado().equals(estado) && this.listaSolicitud.get(i).isSelect()){
				return "";
			}
		}*/
		return "hidden";
	}
	
	
	public boolean estadoSelect(String estado){	
		/*for(int i=0;i<this.listaSolicitud.size();i++){
			if(this.listaSolicitud.get(i).getEstado().equals(estado) && this.listaSolicitud.get(i).isSelect()){
				return false;
			}
		}*/
		return true;
	}
	
	public void guardarPago(int id){
		
		for(int i=0;i<this.listaSolicitud.size();i++){
			if(this.listaSolicitud.get(i).getIdSolicitud()==id && this.listaSolicitud.get(i).getEstado().equals("Grabada")){
				this.listaSolicitud.get(i).setEstado("Pagada");
				String bancoPago = this.consultaModelSolicitud.getBancoPago();
				String cuantiaPago = this.consultaModelSolicitud.getCuantiaPago();
				String formaPago = this.consultaModelSolicitud.getFormaPago();
				//String referenciaPago
				/*
				ControllerSolicitud.listaSolicitud.get(i).setBancoPago(bancoPago);
				ControllerSolicitud.listaSolicitud.get(i).setCuantiaPago(cuantiaPago);
				ControllerSolicitud.listaSolicitud.get(i).setFormaPago(formaPago);
				//ControllerSolicitud.listaSolicitud.get(i).setReferenciaPago(referenciaPago);
				*/
				
				//ControllerSolicitud.listaSolicitud.get(i).setSelect(false);
			}
		}
		
	}
	
	public boolean cambiarFichaTabla(){
		this.consultaModelSolicitud.setFicha(!this.consultaModelSolicitud.isFicha());
		System.out.println(this.consultaModelSolicitud.isFicha());
		return this.consultaModelSolicitud.isFicha();
	}
	
	public String MascaraHora(Integer hora){
		String horaInicial = hora+"";
		String horaFinal = "";
		if(horaInicial.length()==4){
			horaFinal = horaInicial.substring(0,2)+":"+horaInicial.substring(2);
		}
		if(horaInicial.length()==3){
			horaFinal = "0"+horaInicial.substring(0,1)+":"+horaInicial.substring(1);
		}
		return horaFinal;
	}
	
	public String Duracion(Integer horaIni, Integer horaFin){
		String duracion = (horaFin-horaIni)+"";
		duracion = duracion.substring(0, 1);
		return duracion;
	}
	
	public String colorSolicitudEstado(String estado){
		return this.coloresEstado.get(estado);
	}
	
	public boolean bloquearBoton(String estado1, String estado2){
		
			if(this.consultaModelSolicitud.getSelectSolicitud().size()==0){
				return true;
			}else{
				Long id = this.consultaModelSolicitud.getSelectSolicitud().get(0)+0L;
				String estadoSolicitud = this.solicitudBean.findSolicitud(id).getEstado();
				if(estadoSolicitud.equals(estado1) || estadoSolicitud.equals(estado2) ){
					return false;
				}
			}
		
		return true;
	}
	
	public void aplazarAudiencia(){
				
		Long id = this.consultaModelSolicitud.getSelectSolicitud().get(0)+0L;
		List<Audiencia> audiencia = this.solicitudBean.findSolicitud(id).getAudiencias();
		Long idAudiencia = audiencia.get(audiencia.size()-1).getIdAudiencia();
		
		this.audienciaBean.actualizarEstadoAudiencia(idAudiencia, "APLAZADA");
		this.solicitudBean.actualizarEstadoSolicitud(id, "DESIGNACION");
	}
	
	public String changeIconSelect(Long idSolicitud){
		
		for(int i=0;i<this.consultaModelSolicitud.getSelectSolicitud().size();i++){
			if(this.consultaModelSolicitud.getSelectSolicitud().get(i)==idSolicitud){
				return "fa-check";
			}
		}
		
		return "fa-square";
	}
}
