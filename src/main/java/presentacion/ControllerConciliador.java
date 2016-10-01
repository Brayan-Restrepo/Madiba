package presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.util.List;
import java.util.ArrayList;

@ManagedBean
public class ControllerConciliador {

	@ManagedProperty(value = "#{modelConciliador}")
	private ModelConciliador conciliador;

	/**Este atributo va a ser true unicamente cuando se carga por priera vez la pagina
	* al igresar el constructor queda falso y nunca mas va a ser true.
	* con el obejtivo que solo se carge los datos de la lista solo una vez.
	*/ 
	private static boolean inicio = true;
	public static List<ModelConciliador> listaConciliador = new ArrayList<ModelConciliador>();

	public ControllerConciliador() {
		// Es true unicamente cuando se ejecuta la aplicacion por primera vez y
		
		if (ControllerConciliador.inicio) {
			
			// inicio queda falso
			ControllerConciliador.inicio=false;
			
			ControllerConciliador.listaConciliador = new ArrayList<ModelConciliador>();
			ControllerConciliador.listaConciliador.add(new ModelConciliador(1,
					"MARISOL", "BARAJAS TORRES", "3212040399", "Civil",
					"7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(2,
					"GUILLERMO LEON", "CHAVES BUSTOS", "3212040399", "Civil",
					"7 años", "emilioa2005@yahoo.es", "boy-3.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(3,
					"GUSTAVO", "JARAMILLO ZULUAGA", "3212040399", "Civil",
					"7 años", "gustavojaramillozuluaga@hotmail.com",
					"boy-1.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(4,
					"CARLOS ARTURO", "LEON ARDILA", "40444465", "Familia",
					"5 años", "dimarce2511@hotmail.com", "boy-2.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(5,
					"ROSA DEL PILAR", "MÁRQUEZ SARMIENTO", "3212040399",
					"Civil", "7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(6,
					"ADELINA", "PABON MIRAMÓN", "3212040399", "Civil",
					"7 años", "emilioa2005@yahoo.es", "boy-5.png"));			
			ControllerConciliador.listaConciliador.add(new ModelConciliador(7,
					"GLORIA ISABEL", "PEÑA TAMAYO", "40444465", "Familia",
					"5 años", "dimarce2511@hotmail.com", "dj.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(8,
					"MARIA JOSEFINA", "PORRAS DE MONCALEANO", "3212040399",
					"Civil", "7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(9,
					"RAUL ARMANDO", "PRIETO GARCIA", "3212040399", "Civil",
					"7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			ControllerConciliador.listaConciliador.add(new ModelConciliador(10,
					"ROSA DEL CARMEN", "QUEVEDO CELIS", "40444465", "Familia",
					"5 años", "dimarce2511@hotmail.com", "boy-4.png"));			
			ControllerConciliador.listaConciliador.add(new ModelConciliador(11,
					"MARTHA JEANNETHE", "RAMIREZ ORTIZ", "40444465", "Familia",
					"5 años", "dimarce2511@hotmail.com", "dj.png"));			
			ControllerConciliador.listaConciliador.add(new ModelConciliador(12,
					"SANDRA PATRICIA", "VELASQUEZ PARRADO", "3212040399",
					"Civil", "7 años", "emilioa2005@yahoo.es", "boy-5.png"));
			
		}
	}

	public ModelConciliador getConciliador() {
		return conciliador;
	}

	public void setConciliador(ModelConciliador conciliador) {
		this.conciliador = conciliador;
	}

	/**
	 * Optiene el atributo para mostrarlo en pantalla no es necesario este metodo
	 * @return
	 */
	public boolean getInicio() {
		return ControllerConciliador.inicio;
	}
	
	/**
	 *  la lista de conciliadores que se muestra en la ventana listaconciliadores
	 * @return listaConciliador 
	 */
	public List listaConciliador() {
		return ControllerConciliador.listaConciliador;
	}

	/**
	 * pasa el primer conciliador de ultimas
	 */
	public void reparto() {

		// se elimina el primer conciliador y se adiciona al final de la lista
		ModelConciliador mCon = ControllerConciliador.listaConciliador.get(0);
		ControllerConciliador.listaConciliador.remove(0);
		ControllerConciliador.listaConciliador.add(mCon);

		// Se le asigna el orden de la lista a los conciliadores
		int size = ControllerConciliador.listaConciliador.size();
		for (int i = 0; i < size; i++) {
			ControllerConciliador.listaConciliador.get(i).setId(i + 1);
		}

	}

}
