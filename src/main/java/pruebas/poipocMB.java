package pruebas;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean
@ViewScoped 
public class poipocMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7382507544672573263L;


	private int id;
	

	private String nombre;
	private String texto;
	private StreamedContent file;
	private String ruta;
	private Boolean disabledBtn;
	
	
	@PostConstruct
    public void init() {
		this.disabledBtn=true;
		Random randomGenerator = new Random();
        this.id = randomGenerator.nextInt(1000000);
        
    }
	
	public Boolean getDisabledBtn() {
		return disabledBtn;
	}

	public void setDisabledBtn(Boolean disabledBtn) {
		this.disabledBtn = disabledBtn;
	}

	public void generarArchivo(){
		GeneraArchivoWord docx = new GeneraArchivoWord();
	    String rutaDocumento = null;
	    String nombreDocumento = null;

		
	      
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
	            .getExternalContext().getContext();
		//String realPath = ctx.getRealPath("http://localhost:8080/Madiba");
		
		try {
			
			rutaDocumento = docx.replaceTextFound(this.id, this.nombre, this.texto);
			this.ruta=rutaDocumento;
			FacesMessage msg = new FacesMessage("Completado", "ruta :" + rutaDocumento);
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
	    	
	    	System.out.println("Asignado a ruta el valor "+rutaDocumento);
	     	InputStream stream =((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(rutaDocumento);
	    	System.out.println("Método genera Expediente, inputstream "+ stream);
	    	nombreDocumento=this.id+"_Expediente.docx";
	        this.file = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.wordprocessingml.document",nombreDocumento);
	        this.disabledBtn=false;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("asdasdasdasdasdasdadasd "+rutaDocumento);
				
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}




}
