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

	/**
	 * 
	 */
	@ManagedProperty(value = "#{modelSolicitud}")
	private ModelSolicitud consultaModelSolicitud;
	
	//Contiene la lista de solicitudes con datos quemados
	public static List<ModelSolicitud> listaSolicitud = new ArrayList<ModelSolicitud>();

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
			ControllerSolicitud.ficha = true;
			ControllerSolicitud.inicio = false;
			
			//Arma la lista de conciliadores
			String[] convocante3 = {"Luis Alberto Rodriguez"};
			String[] convocado3 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					1,"24/12/2016",convocante3,convocado3,"87655543","Breve descripcion...","Grabada","Solicitud","","3'500.000","","","","","","","","",""));
			
			String[] convocante4 = {"Luis Alberto Rodriguez"};
			String[] convocado4 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					2,"17/12/2016",convocante4,convocado4,"","Breve descripcion...","Grabada","Reparto","","2'500.000","","","","","","","","",""));

			String[] convocante = {"Luis Alberto Rodriguez","Luis Alberto Alberto Rodriguez","Luis Alberto Rodriguez"};
			String[] convocado = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					3,"13/12/2016",convocante,convocado,"","Breve descripcion...","Pagada","Reparto","","4'000.000","BanColombia", "Consignacion", "$200.000", "678-234-643","","","","",""));
			
			String[] convocante2 = {"Luis Alberto Rodriguez"};
			String[] convocado2 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					4,"08/12/2016",convocante2,convocado2,"2345673409","Breve descripcion...","Pagada","Solicitud","","3'700.000","BanBogota", "Consignacion", "$150.000", "970-684-463","","","","",""));
						
			String[] convocante5 = {"Luis Alberto Rodriguez","Luis Alberto Rodriguez","Luis Alberto Rodriguez"};
			String[] convocado5 = {"Alfonso López"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					5,"03/12/2016",convocante5,convocado5,"","Breve descripcion...","Pagada","Reparto","","7'500.000","BanBogota", "PSE", "$250.000", "152-823-128","","","","",""));
			
			String[] convocante6 = {"Luis Alberto Rodriguez"};
			String[] convocado6 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					6,"01/12/2016",convocante6,convocado6,"","Breve descripcion...","Radicada","Reparto","23122016001","4'800.000","BanBogota", "PSE", "$250.000", "152-823-128","","","","",""));
			

			String[] convocante7 = {"Luis Alberto Rodriguez"};
			String[] convocado7 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					7,"01/12/2016",convocante7,convocado7,"3212020399","Breve descripcion...","Designacion","Solicitud","23122016002","2'300.000","BanBogota", "PSE", "$250.000", "152-823-128","","","","",""));
			

			String[] convocante8 = {"Luis Alberto Rodriguez"};
			String[] convocado8 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					8,"01/12/2016",convocante8,convocado8,"","Breve descripcion...","Designacion","Reparto","23122016003","1'600.000","BanBogota", "PSE", "$250.000", "152-823-128","","","","",""));
			
			String[] convocante9 = {"Luis Alberto Rodriguez"};
			String[] convocado9 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					9,"01/12/2016",convocante9,convocado9,"","Breve descripcion...","Audiencia","Reparto","23122016004","1'600.000","BanBogota", "PSE", "$250.000", "152-823-128","","","","",""));
			
			String[] convocante10 = {"Luis Alberto Rodriguez"};
			String[] convocado10 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					10,"01/12/2016",convocante10,convocado10,"","Breve descripcion...","Audiencia","Reparto","23122016005","1'600.000","BanBogota", "PSE", "$250.000", "152-823-128","","","","",""));
			
			String[] convocante11 = {"Luis Alberto Rodriguez"};
			String[] convocado11 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					11,"01/12/2016",convocante11,convocado11,"87655543","Breve descripcion...","por Citacion","Reparto","23122016005","1'600.000","BanBogota", "PSE", "$250.000", "152-823-128","1","25/02/2017","10:00 am","2 horas","3"));
			
			String[] convocante12 = {"Luis Alberto Rodriguez"};
			String[] convocado12 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					12,"01/12/2016",convocante12,convocado12,"87655543","Breve descripcion...","Pendientes","Reparto","23122016005","1'600.000","BanBogota", "PSE", "$250.000", "152-823-128","1","26/02/2017","3:00pm","2 horas","1"));
			
			String[] convocante13 = {"Luis Alberto Rodriguez"};
			String[] convocado13 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					13,"01/12/2016",convocante13,convocado13,"87655543","Breve descripcion...","En Curso","Reparto","23122016005","1'600.000","BanBogota", "PSE", "$250.000", "152-823-128","3","25/02/2017","10:00 am","2 horas","3"));
			
			String[] convocante14 = {"Luis Alberto Rodriguez"};
			String[] convocado14 = {"Alfonso Lopez"};
			ControllerSolicitud.listaSolicitud.add(new ModelSolicitud(
					14,"01/12/2016",convocante14,convocado14,"87655543","Breve descripcion...","Finalizadas","Reparto","23122016005","1'600.000","BanBogota", "PSE", "$250.000", "152-823-128","2","26/02/2017","3:00pm","2 horas","1"));
		}

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

	public List<ModelSolicitud> getListaSolicitud() {
		return ControllerSolicitud.listaSolicitud;
	}

	/**
	 * Filtra las solicitudes por su estado
	 * 
	 * @param El estado de la Solicitud (Grabada -> Pagada -> Radicada -> Designacion -> Audiencia)
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
		String[] estados = {"Grabada","Pagada","Radicada","Designacion","Audiencia"};
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
				if(estadoActual.equals("Designacion")){
					ControllerConciliador conciliador = new ControllerConciliador();
					conciliador.designarConciliador(ControllerSolicitud.listaSolicitud.get(i).getConciliador());
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
			{"Grabada","info"}, //Azul claro
			{"Pagada","primary"}, //Azul rey
			{"Radicada","warning"}, //Naranja
			{"Designacion","success"}, //verde
			{"Audiencia","success"},
			
			{"por Citacion","warning"}, //Azul claro
			{"Pendientes","info"}, //Azul rey
			{"En Curso","primary"}, //Naranja
			{"Finalizadas","success"}, //verde
		}; //Verde
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
			//return "seleccionar-solicitud animated bounce";
			return "seleccionar-solicitud";
		}
		return "";
	}
	
	public void verString(Object ver){
		System.out.println("---------------------------------> "+ver);
	}
	
	public ModelSolicitud liquidar(){
		for(int i=0;i<ControllerSolicitud.listaSolicitud.size();i++ ){
			if(ControllerSolicitud.listaSolicitud.get(i).isSelect() && ControllerSolicitud.listaSolicitud.get(i).getEstado().equals("Grabada")){
				return ControllerSolicitud.listaSolicitud.get(i);
			}
		}
		return null;
	}
	public ModelSolicitud designarConciliador(){
		for(int i=0;i<ControllerSolicitud.listaSolicitud.size();i++ ){
			if(ControllerSolicitud.listaSolicitud.get(i).isSelect() && ControllerSolicitud.listaSolicitud.get(i).getEstado().equals("Radicada")){
				return ControllerSolicitud.listaSolicitud.get(i);
			}
		}
		return null;
	}
		
	public String classEstadoHidden(String estado){	
		for(int i=0;i<ControllerSolicitud.listaSolicitud.size();i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equals(estado) && ControllerSolicitud.listaSolicitud.get(i).isSelect()){
				return "";
			}
		}
		return "hidden";
	}
	
	
	public boolean estadoSelect(String estado){	
		for(int i=0;i<ControllerSolicitud.listaSolicitud.size();i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getEstado().equals(estado) && ControllerSolicitud.listaSolicitud.get(i).isSelect()){
				return false;
			}
		}
		return true;
	}
	
	public void guardarPago(int id){
		
		for(int i=0;i<ControllerSolicitud.listaSolicitud.size();i++){
			if(ControllerSolicitud.listaSolicitud.get(i).getId()==id && ControllerSolicitud.listaSolicitud.get(i).getEstado().equals("Grabada")){
				ControllerSolicitud.listaSolicitud.get(i).setEstado("Pagada");
				String bancoPago = this.consultaModelSolicitud.getBancoPago();
				String cuantiaPago = this.consultaModelSolicitud.getCuantiaPago();
				String formaPago = this.consultaModelSolicitud.getFormaPago();
				//String referenciaPago
				ControllerSolicitud.listaSolicitud.get(i).setBancoPago(bancoPago);
				ControllerSolicitud.listaSolicitud.get(i).setCuantiaPago(cuantiaPago);
				ControllerSolicitud.listaSolicitud.get(i).setFormaPago(formaPago);
				//ControllerSolicitud.listaSolicitud.get(i).setReferenciaPago(referenciaPago);
				
				ControllerSolicitud.listaSolicitud.get(i).setSelect(false);
			}
		}
		
	}
	
	public boolean cambiarFichaTabla(){
		ControllerSolicitud.ficha = !ControllerSolicitud.ficha;
		return ControllerSolicitud.ficha;
	}
	
}
