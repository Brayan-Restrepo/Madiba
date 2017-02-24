package presentacion;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;

import entidades.Solicitud;
import negocio.iConciliadorBean;
import negocio.iSolicitudBean;



@ManagedBean
public class ControllerSolicitud {

	//Permite validar que los datos solo se pinten la primera vez que se carga la pagina y no todas la veces
	private static boolean inicio = true;

	/**
	 * 
	 */
	@ManagedProperty(value = "#{modelSolicitud}")
	private ModelSolicitud consultaModelSolicitud;
	
	//Contiene la lista de solicitudes con datos quemados
	public List<Solicitud> listaSolicitud;

	/**
	 * si este atributo es verdadero se mostrara la solicitudes en forma de 
	 * Ficha de lo contrario, se mostrara en Tablas
	 */
	private static boolean ficha;

	@EJB
	public iSolicitudBean solicitudBean;

	
	/**
	 * Constructor: arma la lista de solicitudes solo la primera vez
	 */
	public ControllerSolicitud(){

		if(ControllerSolicitud.inicio){
			ControllerSolicitud.ficha = true;
			ControllerSolicitud.inicio = false;
		
		}

	}
	
	public List<Solicitud> allSolicitudes(String estado){
		this.listaSolicitud = this.solicitudBean.allSolicitud(estado);
		return this.listaSolicitud;
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

	public boolean isFicha() {
		return ficha;
	}

	public void setFicha(boolean ficha) {
		ControllerSolicitud.ficha = ficha;
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
		boolean mas = false;
		int size = this.listaSolicitud.size();
		for(int i=id; i<size; i++){
			if(this.listaSolicitud.get(i).getEstado().equalsIgnoreCase(estado)){
				mas = true;
			}
		}
		return mas;
	}
	
	/**
	 * Busca la siguiente solicitud que halla con el mismo estado
	 * @param Se envia el id de la ficha actual
	 * @return Retorna la siguiente solicitud con el mismo estado
	 */
	public Solicitud siguienteFichaEstado(int id, String estado){
		Solicitud solicitud = null;
		int size = this.listaSolicitud.size();
		for(int i=id; i<size; i++){
			if(this.listaSolicitud.get(i).getEstado().equals(estado) && this.listaSolicitud.get(i).getIdSolicitud()!=id){
				solicitud = this.listaSolicitud.get(i);
				break;
			}	
		}
		return solicitud;
	}
	
	public String seleccionarSolicitud(boolean select){
		if(select){
			//return "seleccionar-solicitud animated bounce";
			return "seleccionar-solicitud";
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
		ControllerSolicitud.ficha = !ControllerSolicitud.ficha;
		return ControllerSolicitud.ficha;
	}
	
	public String MascaraHora(int hora){
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
	
	public String Duracion(int horaIni, int horaFin){
		String duracion = (horaFin-horaIni)+"";
		duracion = duracion.substring(0, 1);
		return duracion;
	}
	
}
