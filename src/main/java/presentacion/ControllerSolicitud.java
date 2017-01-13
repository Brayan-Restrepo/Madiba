package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean
public class ControllerSolicitud {

	//Permite validar que los datos solo se pinten la primera vez que se carga la pagina y no todas la veces
	private static boolean inicio = true;

	@ManagedProperty(value = "#{modelPago}")
	private ModelPago consultaModelPago;

	
	//Contiene la lista de solicitudes con datos quemados
	public static List<ModelSolicitud> listaSolicitud = new ArrayList<ModelSolicitud>();
	/**
	 * Los pagos de una solicitud
	 */
	public static List<ModelPago> listaPago = new ArrayList<ModelPago>();

	/**
	 * si este atributo es verdadero se mostrara la solicitudes en forma de 
	 * Ficha de lo contrario, se mostrara en Tablas
	 */
	private static boolean ficha;
	
	/**
	 * Constructor: arma la lista de solicitudes solo la primera vez
	 */
	public ControllerSolicitud(){

		if(ControllerSolicitud.inicio){
			this.ficha = true;
			ControllerSolicitud.inicio = false;
			ControllerSolicitud.listaPago.add(new ModelPago(3, "BanColombia", "Consignacion", "$200.000", "678-234-643"));
			ControllerSolicitud.listaPago.add(new ModelPago(4, "BanBogota", "Consignacion", "$150.000", "970-684-463"));
			ControllerSolicitud.listaPago.add(new ModelPago(5, "BanBogota", "PSE", "$250.000", "152-823-128"));
			
			//Arma la lista de conciliadores
			String[] convocante3 = {"Luis Alberto Rodriguez"};
			String[] convocado3 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					1,"24/12/2016",convocante3,convocado3,"87655543","Breve descripcion...","Liquidar","Solicitud","","3'500.000"));
			
			String[] convocante4 = {"Luis Alberto Rodriguez"};
			String[] convocado4 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					2,"17/12/2016",convocante4,convocado4,"","Breve descripcion...","Liquidar","Reparto","","2'500.000"));

			String[] convocante = {"Luis Alberto Rodriguez","Luis Alberto Alberto Rodriguez","Luis Alberto Rodriguez"};
			String[] convocado = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					3,"13/12/2016",convocante,convocado,"","Breve descripcion...","Radicar","Reparto","","4'000.000"));
			
			String[] convocante2 = {"Luis Alberto Rodriguez"};
			String[] convocado2 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					4,"08/12/2016",convocante2,convocado2,"2345673409","Breve descripcion...","Radicar","Solicitud","","3'700.000"));
						
			String[] convocante5 = {"Luis Alberto Rodriguez","Luis Alberto Rodriguez","Luis Alberto Rodriguez"};
			String[] convocado5 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					5,"03/12/2016",convocante5,convocado5,"","Breve descripcion...","Radicar","Reparto","","7'500.000"));
			
			String[] convocante6 = {"Luis Alberto Rodriguez"};
			String[] convocado6 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					6,"01/12/2016",convocante6,convocado6,"","Breve descripcion...","Audiencia","Reparto","23122016001","4'800.000"));
			
		}

	}
			
	public boolean isFicha() {
		return ficha;
	}

	public void setFicha(boolean ficha) {
		this.ficha = ficha;
	}

	public ModelPago getConsultaModelPago() {
		return consultaModelPago;
	}

	public void setConsultaModelPago(ModelPago consultaModelPago) {
		this.consultaModelPago = consultaModelPago;
	}

	public List<ModelSolicitud> getListaSolicitud() {
		return ControllerSolicitud.listaSolicitud;
	}

	/**
	 * Filtra las solicitudes por su estado
	 * @param estado
	 * @return
	 */
	public List<ModelSolicitud> solicitudesPorEstado(String estado){
		List<ModelSolicitud> listaPorEstado = new ArrayList<ModelSolicitud>();
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equals(estado)){
				listaPorEstado.add(ControllerSolicitud.listaSolicitud.get(i));
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
		String[] estados = {"Liquidar","Radicar","Audiencia","Registrar","Finalizado"};
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getId() == id){
				String estadoActual = ControllerSolicitud.listaSolicitud.get(i).getEstado();
				String nuevoEstado = "";
				for(int j=0; j<estados.length; j++){
					if(estados[j].equals(estadoActual)){
						nuevoEstado = estados[j+1];
					}
				}
				ControllerSolicitud.listaSolicitud.get(i).setEstado(nuevoEstado);
				//Designa el conciliador y actualiza la lista
				if(estadoActual.equals("Radicar")){
					ControllerConciliador conciliador = new ControllerConciliador();
					conciliador.designarConciliador(ControllerSolicitud.listaSolicitud.get(i).getConciliador());
				}
			}
		}
	}

	//Define el color de la ficha segun su estado
	public String colorFicha(int id, String tipoComponente){
		String[][] matrizColores = {{"Liquidar","info"}, //Azul claro
							  {"Radicar","primary"}, //Azul rey
							  {"Audiencia","warning"}, //Naranja
							  {"Registrar","success"}, //Verde
							  {"Finalizado","default"}}; //Gris
		String color = "";
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=0; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getId() == id){
				String estadoActual = ControllerSolicitud.listaSolicitud.get(i).getEstado();
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
	public ModelSolicitud siguienteFicha(int id){
		return ControllerSolicitud.listaSolicitud.get(id+1);
	}

	/**
	 * Busca si hay mas fichas con el mismo estado
	 * @param Se envia el estado por el cual se desea buscar
	 * @return Retorna falso si no hay mas, true si encontro otra
	 */
	public boolean masFichas(int id, String estado){
		boolean mas = false;
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=id; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equalsIgnoreCase(estado)){
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
	public ModelSolicitud siguienteFichaEstado(int id, String estado){
		ModelSolicitud solicitud = null;
		int size = ControllerSolicitud.listaSolicitud.size();
		for(int i=id; i<size; i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equals(estado) && ControllerSolicitud.listaSolicitud.get(i).getId()!=id){
				solicitud = ControllerSolicitud.listaSolicitud.get(i);
				break;
			}	
		}
		return solicitud;
	}
	
	public String seleccionarSolicitud(boolean select){
		if(select){
			return "seleccionar-solicitud animated bounce";
		}
		return "";
	}
	
	public void verString(Object ver){
		System.out.println("---------------------------------> "+ver);
	}
	
	public ModelSolicitud liquidar(){
		for(int i=0;i<ControllerSolicitud.listaSolicitud.size();i++ ){
			if(ControllerSolicitud.listaSolicitud.get(i).isSelect() && ControllerSolicitud.listaSolicitud.get(i).getEstado().equals("Liquidar")){
				return ControllerSolicitud.listaSolicitud.get(i);
			}
		}
		return null;
	}
	
	public ModelPago datosLiquidar(int id){
		for(int i=0;i<ControllerSolicitud.listaPago.size();i++ ){
			if(ControllerSolicitud.listaPago.get(i).getSolicitud()==id){
				return ControllerSolicitud.listaPago.get(i);
			}
		}
		return null;
	}
	
	public String classLiquidarHidden(){
		
		for(int i=0;i<ControllerSolicitud.listaSolicitud.size();i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equals("Liquidar") && ControllerSolicitud.listaSolicitud.get(i).isSelect()){
				return "";
			}
		}
		return "hidden";
	}
	
	public void guardarPago(int id){
		this.consultaModelPago.setSolicitud(id);
		ControllerSolicitud.listaPago.add(this.consultaModelPago);
		
		for(int i=0;i<ControllerSolicitud.listaSolicitud.size();i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getId()==id && ControllerSolicitud.listaSolicitud.get(i).getEstado().equals("Liquidar")){
				ControllerSolicitud.listaSolicitud.get(i).setEstado("Radicar");
				ControllerSolicitud.listaSolicitud.get(i).setSelect(false);
			}
		}
		
	}
	
	public boolean cambiarFichaTabla(){
		System.out.println(this.ficha);
		this.ficha = !this.ficha;
		System.out.println(this.ficha);
		return this.ficha;
	}
}
