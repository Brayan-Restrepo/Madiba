package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;
import java.util.ArrayList;

@ManagedBean
public class ControllerConciliador {

	@ManagedProperty(value = "#{modelConciliador}")
	private ModelConciliador conciliador;

	//Permite validar que los datos solo se pinten la primera vez que se carga la pagina y no todas la veces
	private static boolean inicio = true;
	
	//Contiene la lista de conciliadores con datos quemados
	public static List<ModelConciliador> listaConciliador = new ArrayList<ModelConciliador>();

	/**
	 * Constructor carga la lista de conciliadores con datos quemados
	 */
	public ControllerConciliador() {
		if (ControllerConciliador.inicio) {
			ControllerConciliador.inicio=false;
			
			ControllerConciliador.listaConciliador = new ArrayList<ModelConciliador>();
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					1, "1234235689","MARISOL", "BARAJAS TORRES", "3212040399", "Civil","7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					2, "2345673409","GUILLERMO LEON", "CHAVES BUSTOS", "3212040399", "Civil","7 años", "emilioa2005@yahoo.es", "boy-3.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					3, "1234563222","GUSTAVO", "JARAMILLO ZULUAGA", "3212040399", "Civil","7 años", "gustavojaramillozuluaga@hotmail.com","boy-1.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					4, "87655543","CARLOS ARTURO", "LEON ARDILA", "40444465", "Familia","5 años", "dimarce2511@hotmail.com", "boy-2.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					5, "3212020399","ROSA DEL PILAR", "MARQUEZ SARMIENTO", "3212040399","Civil", "7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					6, "3212780399","ADELINA", "PABON MIRAMON", "3212040399", "Civil","7 años", "emilioa2005@yahoo.es", "boy-5.png"));			
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					7, "3212790399","GLORIA ISABEL", "PEÑA TAMAYO", "40444465", "Familia","5 años", "dimarce2511@hotmail.com", "dj.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					8, "23567824","MARIA JOSEFINA", "PORRAS DE MONCALEANO", "3212040399","Civil", "7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					9, "3236440399","RAUL ARMANDO", "PRIETO GARCIA", "3212040399", "Civil","7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					10, "3212110399","ROSA DEL CARMEN", "QUEVEDO CELIS", "40444465", "Familia","5 años", "dimarce2511@hotmail.com", "boy-4.png"));			
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					11, "404445465","MARTHA JEANNETHE", "RAMIREZ ORTIZ", "40444465", "Familia","5 años", "dimarce2511@hotmail.com", "dj.png"));			
			ControllerConciliador.listaConciliador.add(new ModelConciliador(
					12, "321220399","SANDRA PATRICIA", "VELASQUEZ PARRADO", "3212040399","Civil", "7 años", "emilioa2005@yahoo.es", "boy-5.png"));
		}
	}

	public ModelConciliador getConciliador() {
		return conciliador;
	}

	public void setConciliador(ModelConciliador conciliador) {
		this.conciliador = conciliador;
	}

	public List<ModelConciliador> listaConciliador() {
		return ControllerConciliador.listaConciliador;
	}

	/**
	 * Decide si el conciliador sera designado por solicitud o por reparto
	 * @param conciliador contiene el tipo de designacion
	 */
	public void designarConciliador(String conciliador){
		if(conciliador.equals("Por Reparto")){
			reparto();
		}else{
			solicitado(conciliador);
		}
	}
	
	/**
	 * Designa el conciliador que este esperando el turno en la lista de reparto y lo manda al final de la cola
	 */
	public void reparto() {
		ModelConciliador mCon = ControllerConciliador.listaConciliador.get(0);
		ControllerConciliador.listaConciliador.remove(0);
		ControllerConciliador.listaConciliador.add(mCon);

		//Asigna los nuevos turnos
		int size = ControllerConciliador.listaConciliador.size();
		for (int i = 0; i < size; i++) {
			ControllerConciliador.listaConciliador.get(i).setId(i + 1);
		}
	}
	
	/**
	 * Designa el conciliador que fue seleccionado y lo manda al final de la cola
	 * @param cedula es la identificacion del conciliador requerido
	 */
	public void solicitado(String cedula) {
		//Buscar el conciliador y lo manda a la cola
		int size = ControllerConciliador.listaConciliador.size();
		for (int i = 0; i < size; i++) {
			if(ControllerConciliador.listaConciliador.get(i).getCedula().equals(cedula)){
				ModelConciliador mCon = ControllerConciliador.listaConciliador.get(i);
				ControllerConciliador.listaConciliador.remove(i);
				ControllerConciliador.listaConciliador.add(mCon);
			}
		}
		
		//Asigna los nuevos turnos
		for (int i = 0; i < size; i++) {
			ControllerConciliador.listaConciliador.get(i).setId(i + 1);
		}
	}
	
	/**
	 * Busca el nombre del conciliador por su cedula
	 * @param cedula es la identificacion del conciliador requerido
	 * @return Retorna el nombre del conciliador
	 */
	public String buscarConciliador(String cedula){
		String nombre = "";
		if(cedula.equals("Por Reparto")){
			return "Por Reparto";
		}
		int size = ControllerConciliador.listaConciliador.size();
		for(int i=0; i<size; i++){
			if(ControllerConciliador.listaConciliador.get(i).getCedula().equals(cedula)){
				//nombre = ControllerConciliador.listaConciliador.get(i).getApellido()+" "+ControllerConciliador.listaConciliador.get(i).getNombre();
				nombre = ControllerConciliador.listaConciliador.get(i).getNombre();
				break;
			}
		}
		return nombre;
	}

}
