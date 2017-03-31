package presentacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ModelBusqueda {
	
	private String fechaInicio;
    private String fechaFinal;
    private String cc;
    private String tipoParte;

    public ModelBusqueda(){
		Date fechaActual = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fechaActual);
    	calendar.add(Calendar.MONTH, -3);   
	
		this.fechaFinal = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
		this.fechaInicio = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
		this.tipoParte = "Convocante";
    }

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getTipoParte() {
		return tipoParte;
	}

	public void setTipoParte(String tipoParte) {
		this.tipoParte = tipoParte;
	}
	    
    
}
