package presentacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import entidades.Conciliador;
import negocio.iConciliadorBean;

@ManagedBean
@SessionScoped
public class ModelBusqueda {
	
	private String fechaInicio;
    private String fechaFinal;
    private String cc;
    private String tipoParte;

	private List<SelectItem> conciliadores;

	private boolean mensaje = false;
	
	@EJB
	public iConciliadorBean conciliadorBean;

	public ModelBusqueda(){
		Date fechaActual = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fechaActual);
    	calendar.add(Calendar.MONTH, -3);   
	
		this.fechaFinal = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
		this.fechaInicio = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
		this.tipoParte = "Radicado";
    }
	
	@PostConstruct
	public void initConciliadores(){
		conciliadores = new ArrayList<SelectItem>();
		List<Conciliador> listaConciliadores = conciliadorBean.allConciliador();
		for (int i = 0; i < listaConciliadores.size(); i++) {
			conciliadores.add(new SelectItem(listaConciliadores.get(i).getIdentificacion(), listaConciliadores.get(i).getNombres()+" "+listaConciliadores.get(i).getApellidos()));
		}
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
	    
	public boolean isMensaje() {
		return mensaje;
	}

	public void setMensaje(boolean mensaje) {
		this.mensaje = mensaje;
	}
	
	public void valFechIn(){	
		Date fechaActual = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fechaActual);
    	calendar.add(Calendar.MONTH, -3);
		
		if( this.fechaInicio==null || this.fechaInicio.equals("")){
			this.fechaInicio = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
		}
	}
	
	public void valFechFn(){
		Date fechaActual = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fechaActual);
    	calendar.add(Calendar.MONTH, -3);   
    	
		if( this.fechaFinal==null || this.fechaFinal.equals("")){
			this.fechaFinal = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
		}
	}
	
	public List<SelectItem> getConciliadores() {
		return conciliadores;
	}

	public void setConciliadores(List<SelectItem> conciliadores) {
		this.conciliadores = conciliadores;
	}
}
