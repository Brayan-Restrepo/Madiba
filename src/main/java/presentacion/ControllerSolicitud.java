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
import javax.faces.event.AjaxBehaviorEvent;

import entidades.Pago;
import entidades.Solicitud;
import negocio.iAudienciaBean;
import negocio.iSolicitudBean;



@ManagedBean
@ViewScoped
public class ControllerSolicitud {

	/**
	 * 
	 */
	@ManagedProperty(value = "#{modelSolicitud}")
	private ModelSolicitud consultaModelSolicitud;
	
	@ManagedProperty(value = "#{modelLogin}")
	private ModelLogin modelLogin;
	
	@ManagedProperty(value = "#{modelBusqueda}")
	private ModelBusqueda modelBusqueda;
	
	//Contiene la lista de solicitudes con datos quemados
	public List<Solicitud> listaSolicitud;

	@EJB
	public iSolicitudBean solicitudBean;
	
	@EJB
	public iAudienciaBean audienciaBean;
	
	private Map<String, String> coloresEstado;
			
	public ModelBusqueda getModelBusqueda() {
		return modelBusqueda;
	}

	public void setModelBusqueda(ModelBusqueda modelBusqueda) {
		this.modelBusqueda = modelBusqueda;
	}

	public ModelLogin getModelLogin() {
		return modelLogin;
	}

	public void setModelLogin(ModelLogin modelLogin) {
		this.modelLogin = modelLogin;
	}

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
		this.coloresEstado.put("FINALIZADA-SOBRECOSTO", "black");
		this.coloresEstado.put("DESIGNACION-SOBRECOSTO", "black");
		this.coloresEstado.put("REGISTRADA", "black");
	}
	
	/*
	public List<Solicitud> allSolicitudes(String estado){
		this.listaSolicitud = this.solicitudBean.allSolicitud(estado);
		return this.listaSolicitud;
	}
	*/

	public void findSolicitudes(){
		String numero = this.modelBusqueda.getNumero();
		String tipoFiltro = this.modelBusqueda.getTipoFiltro();

		Date fechaActual = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fechaActual);
    	calendar.add(Calendar.MONTH, -3);   
    	String fechFn = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
    	String fechIn = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
    	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicial = null;
		Date fechaFinal = null;
		try {
			 fechaInicial = formatoDelTexto.parse(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
			 fechaFinal = formatoDelTexto.parse(new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
		} catch (ParseException ex) {
		     ex.printStackTrace();
		}
		
		
		if((this.modelBusqueda.getFechaInicio() == null || this.modelBusqueda.getFechaInicio().equals("")) && 
				(this.modelBusqueda.getFechaFinal() == null || this.modelBusqueda.getFechaFinal().equals("")) && 
				(numero == null || numero.equals(""))){
			this.listaSolicitud = this.solicitudBean.findSolicitudes(fechaInicial, fechaFinal);
		}else{
			if(numero == null || numero.equals("")){
				this.listaSolicitud = this.solicitudBean.findSolicitudes(fechaInicial, fechaFinal);
			}else{
				if((this.modelBusqueda.getFechaInicio() != null && !this.modelBusqueda.getFechaInicio().equals("")) && 
						(this.modelBusqueda.getFechaFinal() != null && !this.modelBusqueda.getFechaFinal().equals(""))){
					try {
						 fechaInicial = formatoDelTexto.parse(this.modelBusqueda.getFechaInicio());
						 fechaFinal = formatoDelTexto.parse(this.modelBusqueda.getFechaFinal());
					} catch (ParseException ex) {
					     ex.printStackTrace();
					}
					if(tipoFiltro.equals("Conciliador")){
						this.listaSolicitud = this.solicitudBean.findSolicitudesFiltroConciliadorFecha(fechaInicial, fechaFinal, numero);
					}else {
						if(tipoFiltro.equals("Radicado")){
							this.listaSolicitud = this.solicitudBean.findSolicitudesFiltroRadicadoFecha(fechaInicial, fechaFinal, numero);
						}else {
							this.listaSolicitud = this.solicitudBean.findSolicitudesFiltroParteFecha(fechaInicial, fechaFinal, numero, tipoFiltro);
						}
					}
				}else {
					if(tipoFiltro.equals("Conciliador")){
						this.listaSolicitud = this.solicitudBean.findSolicitudesFiltroConciliadorFecha(fechaInicial, fechaFinal, numero);
					}else {
						if(tipoFiltro.equals("Radicado")){
							this.listaSolicitud = this.solicitudBean.findSolicitudesFiltroRadicadoFecha(fechaInicial, fechaFinal, numero);
						}else {
							this.listaSolicitud = this.solicitudBean.findSolicitudesFiltroParteFecha(fechaInicial, fechaFinal, numero, tipoFiltro);
						}
					}
				}
			}
		}
		
		// Resetea los inputs
		this.modelBusqueda.setNumero("");
		this.modelBusqueda.setTipoFiltro("Radicado");
		this.modelBusqueda.setFechaInicio(fechIn);
		this.modelBusqueda.setFechaFinal(fechFn);
		this.modelBusqueda.setMensaje(false);
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
		
		if(estado.equalsIgnoreCase("AUDIENCIA")){
			for(int i=0; i<size; i++){
				if(this.listaSolicitud.get(i).getEstado().equals("AUDIENCIA-CITACION") || this.listaSolicitud.get(i).getEstado().equals("AUDIENCIA-PENDIENTE") || 
						this.listaSolicitud.get(i).getEstado().equals("AUDIENCIA-ENCURSO")){
					listaPorEstado.add(this.listaSolicitud.get(i));
				}
			}
		}else{
			for(int i=0; i<size; i++){
				if(this.listaSolicitud.get(i).getEstado().equals(estado)){
					listaPorEstado.add(this.listaSolicitud.get(i));
				}
			}
		}
		return listaPorEstado;
	}
	
	public List<Solicitud> solicitudesPorPagar(){
		List<Solicitud> listaPorEstado = new ArrayList<Solicitud>();
		int size = this.listaSolicitud.size();
		
		for(int i=0; i<size; i++){
			if(this.listaSolicitud.get(i).getEstado().equals("GRABADA") || this.listaSolicitud.get(i).getEstado().equals("FINALIZADA-SOBRECOSTO") || 
					this.listaSolicitud.get(i).getEstado().equals("DESIGNACION-SOBRECOSTO")){
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
	
	public String seleccionarSolicitud(Solicitud auxSolicitud,String estado){
		for(int i=0;i<this.consultaModelSolicitud.getSelectSolicitud().size();i++){
			if(this.consultaModelSolicitud.getSelectSolicitud().get(i).getIdSolicitud()==auxSolicitud.getIdSolicitud()){
				return "seleccionar-solicitud-"+this.coloresEstado.get(estado);
			}
		}
		return "";
	}
	
	public void verString(Object ver){
		System.out.println("---------------------------------> "+ver);
	}
	
	public double valorSobrecosto(){
		if(this.consultaModelSolicitud.getSelectSolicitud().size()==1){
			if(this.consultaModelSolicitud.getSelectSolicitud().get(0).getEstado().equalsIgnoreCase("FINALIZADA-SOBRECOSTO") || this.consultaModelSolicitud.getSelectSolicitud().get(0).getEstado().equalsIgnoreCase("DESIGNACION-SOBRECOSTO")){
		
				for (int i = 0; i < this.consultaModelSolicitud.getSelectSolicitud().get(0).getPagos().size(); i++) {
					if(this.consultaModelSolicitud.getSelectSolicitud().get(0).getPagos().get(i).getEstado().equalsIgnoreCase("SNOPAGADO")){
						return this.consultaModelSolicitud.getSelectSolicitud().get(0).getPagos().get(i).getValor();
					}
				}
			}
			
		}
		return 0;
	}
	
	public void guardarPago(Solicitud auxSolicitud){
		
		Date fechaActual = new Date();
		Pago pago = new Pago();
		Long idPago = auxSolicitud.getPagos().get(auxSolicitud.getPagos().size()-1).getIdPago();
		
		for (int i = 0; i < auxSolicitud.getPagos().size(); i++) {
			if(auxSolicitud.getPagos().get(i).getEstado().equalsIgnoreCase("SNOPAGADO")){
				idPago = auxSolicitud.getPagos().get(i).getIdPago();
			}
		}
		pago.setIdPago(idPago);
		pago.setEstado("SPAGADO");
		pago.setFecha(fechaActual);
		pago.setFormaPago(2);
		pago.setSolicitud(auxSolicitud);
		
		this.solicitudBean.guardarPago(pago);
		
		if(auxSolicitud.getEstado().equalsIgnoreCase("FINALIZADA-SOBRECOSTO")){
			this.solicitudBean.actualizarEstadoSolicitud(auxSolicitud.getIdSolicitud(), "AUDIENCIA-FINALIZADA");
		}else if(auxSolicitud.getEstado().equalsIgnoreCase("DESIGNACION-SOBRECOSTO")){
			this.solicitudBean.actualizarEstadoSolicitud(auxSolicitud.getIdSolicitud(), "DESIGNACION");
			
		}
	
	}
	
	public boolean cambiarFichaTabla(){
		this.consultaModelSolicitud.setFicha(!this.consultaModelSolicitud.isFicha());
		return this.consultaModelSolicitud.isFicha();
	}
	
	public Long mascaraValor(Double auxValor){
		long valor= auxValor.longValue();
		
		return valor;
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
	
	public boolean bloquearModalSobrecosto(){
		
		if(this.consultaModelSolicitud.getSelectSolicitud().size()==1){
			if(this.consultaModelSolicitud.getSelectSolicitud().get(0).getEstado().equalsIgnoreCase("FINALIZADA-SOBRECOSTO") || this.consultaModelSolicitud.getSelectSolicitud().get(0).getEstado().equalsIgnoreCase("DESIGNACION-SOBRECOSTO")){
				return true;
			}
		}
		return false;
	}
	
	public boolean bloquearBoton(String estado1, String estado2){
		
			if(this.consultaModelSolicitud.getSelectSolicitud().size()==0){
				return true;
			}else{
				String estadoSolicitud = this.consultaModelSolicitud.getSelectSolicitud().get(0).getEstado();
				if(estadoSolicitud.equals(estado1) || estadoSolicitud.equals(estado2) ){
					return false;
				}
			}
		
		return true;
	}
		
	public boolean bloquearBotonRegistrarPago(){
		if(this.consultaModelSolicitud.getSelectSolicitud().size()==1){
			if(this.consultaModelSolicitud.getSelectSolicitud().get(0).getEstado().equalsIgnoreCase("FINALIZADA-SOBRECOSTO") || this.consultaModelSolicitud.getSelectSolicitud().get(0).getEstado().equalsIgnoreCase("DESIGNACION-SOBRECOSTO")){ 
				return false;
			}
		}
		return true;
	}
	
	public String changeIconSelect(Solicitud auxSolicitud){
		
		for(int i=0;i<this.consultaModelSolicitud.getSelectSolicitud().size();i++){
			if(this.consultaModelSolicitud.getSelectSolicitud().get(i).getIdSolicitud()==auxSolicitud.getIdSolicitud()){
				return "fa-check";
			}
		}
		
		return "fa-square";
	}
}
