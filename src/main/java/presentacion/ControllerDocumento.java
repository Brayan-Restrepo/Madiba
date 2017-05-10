package presentacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import entidades.Asistencia;
import entidades.Designacion;
import entidades.Parte;
import entidades.Solicitud;

@ManagedBean
@ViewScoped
public class ControllerDocumento {
	
	@ManagedProperty(value = "#{fileUtilities}")
	private FileUtilities fileUtilities;
		
	public FileUtilities getFileUtilities() {
		return fileUtilities;
	}

	public void setFileUtilities(FileUtilities fileUtilities) {
		this.fileUtilities = fileUtilities;
	}

	public void actasConstancia(Solicitud solicitud) throws IOException{
		int ultimaAudiencia = solicitud.getAudiencias().size()-1;
		String estadoAudiencia = solicitud.getAudiencias().get(ultimaAudiencia).getEstado();
		
		if(estadoAudiencia.equalsIgnoreCase("INASISTENCIA")){
			this.constanciaInasistencia(solicitud);
			String path = "C:/Conalbos-Madiba/docs/ConstanciaInasistencia"+solicitud.getNroRadicado()+".docx";
			String name = "ConstanciaInasistencia"+solicitud.getNroRadicado();
			this.fileUtilities.download(path, name);
		}else{
			if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().size()==1 ){
				if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getTipoResultado().equalsIgnoreCase("ACUERDO")){
					this.actaConciliacion(solicitud);
					String path = "C:/Conalbos-Madiba/docs/Acta"+solicitud.getNroRadicado()+".docx";
					String name = "Acta"+solicitud.getNroRadicado();
					this.fileUtilities.download(path, name);
				}else if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getTipoResultado().equalsIgnoreCase("NOACUERDO")){
					this.constanciaNoAcuerdo(solicitud);
					String path = "C:/Conalbos-Madiba/docs/constanciaNoAcuerdo"+solicitud.getNroRadicado()+".docx";
					String name = "constanciaNoAcuerdo"+solicitud.getNroRadicado();
					this.fileUtilities.download(path, name);
				}else if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getTipoResultado().equalsIgnoreCase("NOCONCILIABLE")){
					this.constanciaNoConciliable(solicitud);
					String path = "C:/Conalbos-Madiba/docs/constanciaNoConciliable"+solicitud.getNroRadicado()+".docx";
					String name = "constanciaNoConciliable"+solicitud.getNroRadicado();
					this.fileUtilities.download(path, name);
				}
			}else if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().size()==2 ){
				this.actaConciliacionParcial(solicitud);
				String path = "C:/Conalbos-Madiba/docs/Acta"+solicitud.getNroRadicado()+".docx";
				String name = "Acta"+solicitud.getNroRadicado();
				this.fileUtilities.download(path, name);
			}
			
		}
	
	}
	
	
	/**
	 * 
	 * @param rutaCitacion la ruta de la citacion con extencion .docx
	 * @param nombreDocumento
	 * @param nombre
	 * @param texto
	 * @throws IOException
	 */
	public void citaciones(String rutaCitacion, String tipoParte, String nombreConvocado, String nombresConvocantes, String asunto, String direccionCitado, String radicado, String fechaAudiencia, String horaAudiencia) throws IOException {
		String inputfilepath = ""; 
		if(tipoParte.equalsIgnoreCase("Convocante")){
			inputfilepath = "C:/Conalbos-Madiba/plantilla/citacionConvocante.docx";
		}else if(tipoParte.equalsIgnoreCase("Convocado")){
			inputfilepath = "C:/Conalbos-Madiba/plantilla/citacionConvocado.docx";
		}
		
		//String outputfilepath = "C:/Conalbos-Madiba/docs/"+nombreDocumento+".docx";
		System.out.println(inputfilepath);
		Date fechaActual = new Date(); 
    	String fechaCitacion = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
    	
		InputStream fs = new FileInputStream(inputfilepath);
		XWPFDocument doc = new XWPFDocument(fs); 
		
		for (XWPFParagraph p : doc.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		        for (XWPFRun r : runs) {
		        	String text = r.getText(0);
		        	 
		        	if (text != null){
			        	if (text.contains("$FECHACITACION")) {
				        	text = text.replace("$FECHACITACION", fechaCitacion);
				        	r.setText(text, 0);
			        	}
			        	if(text.contains("$NOMBRECONVOCADO")) {
			        		text = text.replace("$NOMBRECONVOCADO", nombreConvocado);
			        		r.setText(text, 0);
			        	}
			        	if (text.contains("$DIRECCIONCITADO")) {
				        	text = text.replace("$DIRECCIONCITADO", direccionCitado);
				        	r.setText(text, 0);
			        	}
			        	if(text.contains("$RADICADO")) {
			        		text = text.replace("$RADICADO", radicado);
			        		r.setText(text, 0);
			        	}
			        	if (text.contains("$CONVOCANTES")) {
				        	text = text.replace("$CONVOCANTES", nombresConvocantes);
				        	r.setText(text, 0);
			        	}
			        	if(text.contains("$ASUNTO")) {
			        		text = text.replace("$ASUNTO", asunto);
			        		r.setText(text, 0);
			        	}

			        	if (text.contains("$FECHAAUDIENCIA")) {
				        	text = text.replace("$FECHAAUDIENCIA", fechaAudiencia);
				        	r.setText(text, 0);
			        	}
			        	if(text.contains("$HORAAUDIENCIA")) {
			        		text = text.replace("$HORAAUDIENCIA", horaAudiencia);
			        		r.setText(text, 0);
			        	}
		        	}
		        }
		    }
		
			//FileOutputStream out = new FileOutputStream(outputfilepath);
		    FileOutputStream out = new FileOutputStream(rutaCitacion);
		    doc.write(out);
		    out.close();
		}
	}
	
	
	public void constanciaInasistencia(Solicitud solicitud) throws IOException {
		
		String rutaInasistencia = "C:/Conalbos-Madiba/docs/ConstanciaInasistencia"+solicitud.getNroRadicado()+".docx";
		String inputfilepath = "C:/Conalbos-Madiba/plantilla/inasistencia.docx";
		
		int ultimaAudiencia = solicitud.getAudiencias().size()-1;
		int ultimaAgenda = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().size()-1;
		int ultimaDesignacion = solicitud.getDesignacions().size()-1;
		
		ControllerSolicitud contSolicitud = new ControllerSolicitud();
		Date fechaActual1 = new Date(); 
    	String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual1);
		String fechaSolicitud = new SimpleDateFormat("dd/MM/yyyy").format(solicitud.getFecha());
		String fechaAudiencia = new SimpleDateFormat("dd/MM/yyyy").format(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getFecha());
		String horaAudiencia = contSolicitud.MascaraHora(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getHoraInicial());
		String conciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getNombres()+" "+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getApellidos();
		String conciliadorCC = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getIdentificacion();
		
		String convocantes = "";
		String convocados = "";
		String listaConvocantesConCC = "";
		String listaConvocadosConCC = "";
		for (Parte parte : solicitud.getPartes()) {
			if(parte.getTipoParte().equalsIgnoreCase("Convocante")){
				convocantes += parte.getNombres()+" "+parte.getApellidos()+", ";
				listaConvocantesConCC += parte.getNombres()+" "+parte.getApellidos()+" identificado con la C.C. "+parte.getIdentificacion()+", ";
			}else if(parte.getTipoParte().equalsIgnoreCase("Convocado")){
				convocados += parte.getNombres()+" "+parte.getApellidos()+", ";
				listaConvocadosConCC += parte.getNombres()+" "+parte.getApellidos()+" identificado con la C.C. "+parte.getIdentificacion()+", ";
			}
		}
		
		convocados = convocados.substring(0, convocados.length()-1);
		convocantes = convocantes.substring(0, convocantes.length()-1);

		String inasistencia = "Que transcurrido los tres días establecidos por la ley para justificar la inasistencia, ";
		String convocantesInasistenciaNoExcusa = "";
		String convocadosInasistenciaNoExcusa = "";
		
		String presenteConvocantesConvocados="";
		String presenteConvocantes="";
		String presenteConvocados="";
		String noPresenteParte="";
		
		for (Asistencia asistencia : solicitud.getAudiencias().get(ultimaAudiencia).getAsistencias()) {
			if(asistencia.getParte().getTipoParte().equalsIgnoreCase("Convocante")){
				if(!asistencia.getAsistio()){
					noPresenteParte += asistencia.getParte().getNombres()+" "+asistencia.getParte().getApellidos()+", ";
					if(asistencia.getExcusa().equalsIgnoreCase("")){
						convocantesInasistenciaNoExcusa += asistencia.getParte().getNombres()+" "+asistencia.getParte().getApellidos()+", ";
					}					
				}else{
					presenteConvocantes += asistencia.getParte().getNombres()+" "+asistencia.getParte().getApellidos()+", ";
				}
			}else if(asistencia.getParte().getTipoParte().equalsIgnoreCase("Convocado")){
				if(!asistencia.getAsistio()){
					noPresenteParte += asistencia.getParte().getNombres()+" "+asistencia.getParte().getApellidos()+", ";
					if(asistencia.getExcusa().equalsIgnoreCase("")){
						convocadosInasistenciaNoExcusa +=asistencia.getParte().getNombres()+" "+asistencia.getParte().getApellidos()+", ";
					}
				}else{
					presenteConvocados += asistencia.getParte().getNombres()+" "+asistencia.getParte().getApellidos()+", ";
				}
			}
		}
		if(noPresenteParte.length()>0){
			noPresenteParte += "no se hizo presente a quien se le concede el término de tres días para justificar su inasistencia.";
		}
		if(presenteConvocados.length()>0 || presenteConvocantes.length()>0){
			presenteConvocantesConvocados += "Que llegada la fecha y hora, se hizo presente ";
			if(presenteConvocados.length()>0){
				presenteConvocados = "la parte convocante "+presenteConvocados;
				presenteConvocantesConvocados += presenteConvocados;
			}
			if(presenteConvocantes.length()>0){
				presenteConvocantes = "la parte convocada "+presenteConvocantes;
				presenteConvocantesConvocados += presenteConvocantes;
			}
			presenteConvocantesConvocados = presenteConvocantesConvocados.substring(0, presenteConvocantesConvocados.length()-2);
			presenteConvocantesConvocados += ".";
		}
		if(convocantesInasistenciaNoExcusa.length()>0){
			inasistencia += "el convocante "+convocantesInasistenciaNoExcusa;
		}		
		if(convocadosInasistenciaNoExcusa.length()>0){
			inasistencia += "el convocado "+convocadosInasistenciaNoExcusa;
		}
		
		inasistencia += "no justifico su inasistencia.";
		if(convocantesInasistenciaNoExcusa.length()==0 && convocadosInasistenciaNoExcusa.length()==0){
			inasistencia = "";
		}
		
		System.out.println(inputfilepath);
		InputStream fs = new FileInputStream(inputfilepath);
		XWPFDocument doc = new XWPFDocument(fs); 
		
		for (XWPFParagraph p : doc.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		        for (XWPFRun r : runs) {
		        	String text = r.getText(0);
		        
		        	if (text != null){
		        		if (text.contains("$RADICADO")) {
		        			text = text.replace("$RADICADO", solicitud.getNroRadicado());
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONVOCANTES")) {
		        			text = text.replace("$CONVOCANTES", convocantes);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONVOCADOS")) {
		        			text = text.replace("$CONVOCADOS", convocados);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$ASUNTO")) {
		        			text = text.replace("$ASUNTO", solicitud.getAsunto());
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHASOLICITUD")) {
		        			text = text.replace("$FECHASOLICITUD", fechaSolicitud);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHAAUDIENCIA")) {
		        			text = text.replace("$FECHAAUDIENCIA", fechaAudiencia);
		        			r.setText(text, 0);
		        		}
			        	if(text.contains("$HORAAUDIENCIA")) {
			        		text = text.replace("$HORAAUDIENCIA", horaAudiencia);
			        		r.setText(text, 0);
			        	}
		        		if (text.contains("$INASISTENCIA")) {
		        			text = text.replace("$INASISTENCIA", inasistencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHAACTUAL")) {
		        			text = text.replace("$FECHAACTUAL", fechaActual);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONCILIADOR")) {
		        			text = text.replace("$CONCILIADOR", conciliador);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CCCONCILIADOR")) {
		        			text = text.replace("$CCCONCILIADOR", conciliadorCC);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$LISCONVOCANTESCONCC")) {
		        			text = text.replace("$LISCONVOCANTESCONCC", listaConvocantesConCC);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$LISCONVOCADOSCONCC")) {
		        			text = text.replace("$LISCONVOCADOSCONCC", listaConvocadosConCC);
		        			r.setText(text, 0);
		        		}
		        		

		        		if (text.contains("$PRESENTECONVOCANTESCONVOCADOS")) {
		        			text = text.replace("$PRESENTECONVOCANTESCONVOCADOS", presenteConvocantesConvocados);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$NOPRESENTEPARTE")) {
		        			text = text.replace("$NOPRESENTEPARTE", noPresenteParte);
		        			r.setText(text, 0);
		        		}
		        	}
		        }
		    }
		
		    FileOutputStream out = new FileOutputStream(rutaInasistencia);
		    doc.write(out);
		    out.close();
		}
	}
	
	public void actaConciliacion(Solicitud solicitud) throws IOException {
		 
		String inputfilepath = "C:/Conalbos-Madiba/plantilla/actaConciliacion.docx";
		String rutaActaConciliacion = "C:/Conalbos-Madiba/docs/Acta"+solicitud.getNroRadicado()+".docx";

		int ultimaAudiencia = solicitud.getAudiencias().size()-1;
		int ultimaAgenda = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().size()-1;
		int ultimaDesignacion = solicitud.getDesignacions().size()-1;
		
		ControllerSolicitud contSolicitud = new ControllerSolicitud();
    	String fechaAudiencia = new SimpleDateFormat("dd/MM/yyyy").format(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getFecha());
		String horaAudiencia = contSolicitud.MascaraHora(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getHoraInicial());
		
		
		String radicado = solicitud.getNroRadicado();
		String convocantes = "";
		String convocados = "";
		String asunto = solicitud.getAsunto();
		String identificacionConciliador = "";
		String identificacionConvocado = "";
		String identificacionConvocante = "";
		String nombreConvocados = "";
		String nombreConvocantes = "";
		String acuerdos = solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getConclusion();
		String conciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getNombres()+" "+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getApellidos();
		
		identificacionConciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getNombres()+" "+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getApellidos()+", identificado con C.C No."+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getIdentificacion();
		
		for (Parte parte : solicitud.getPartes()) {
			if(parte.getTipoParte().equalsIgnoreCase("Convocado")){
				convocados += parte.getNombres()+" "+parte.getApellidos()+", ";
				nombreConvocados += parte.getNombres()+" "+parte.getApellidos()+"            ";
				identificacionConvocado += parte.getNombres()+" "+parte.getApellidos()+", identificado con C.C No. "+parte.getIdentificacion()+", ";
			}
			if(parte.getTipoParte().equalsIgnoreCase("Convocante")){
				convocantes += parte.getNombres()+" "+parte.getApellidos()+", ";
				nombreConvocantes += parte.getNombres()+" "+parte.getApellidos()+"            ";
				identificacionConvocante += parte.getNombres()+" "+parte.getApellidos()+", identificado con C.C No. "+parte.getIdentificacion()+", ";
			}
		}
		identificacionConvocado = identificacionConvocado.substring(0, identificacionConvocado.length()-2);
		System.out.println(inputfilepath);
		InputStream fs = new FileInputStream(inputfilepath);
		XWPFDocument doc = new XWPFDocument(fs); 
		
		for (XWPFParagraph p : doc.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		        for (XWPFRun r : runs) {
		        	String text = r.getText(0);
		        
		        	if (text != null){
		        		if (text.contains("$RADICADO")) {
		        			text = text.replace("$RADICADO", radicado);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONVOCANTES")) {
		        			text = text.replace("$CONVOCANTES", convocantes);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONVOCADOS")) {
		        			text = text.replace("$CONVOCADOS", convocados);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$ASUNTO")) {
		        			text = text.replace("$ASUNTO", asunto);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$HORAAUDIENCIA")) {
		        			text = text.replace("$HORAAUDIENCIA", horaAudiencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHAAUDIENCIA")) {
		        			text = text.replace("$FECHAAUDIENCIA", fechaAudiencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONCILIADOR")) {
		        			text = text.replace("$IDENTIFICACIONCONCILIADOR", identificacionConciliador);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONVOCANTE")) {
		        			text = text.replace("$IDENTIFICACIONCONVOCANTE", identificacionConvocante);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONVOCADO")) {
		        			text = text.replace("$IDENTIFICACIONCONVOCADO", identificacionConvocado);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$ACUERDOS")) {
		        			text = text.replace("$ACUERDOS", acuerdos);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONCILIADOR")) {
		        			text = text.replace("$CONCILIADOR", conciliador);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$NOMBRECONVOCANTES")) {
		        			text = text.replace("$NOMBRECONVOCANTES", nombreConvocantes);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$NOMBRECONVOCADOS")) {
		        			text = text.replace("$NOMBRECONVOCADOS", nombreConvocados);
		        			r.setText(text, 0);
		        		}
		        	}
		        }
		    }
		
		    FileOutputStream out = new FileOutputStream(rutaActaConciliacion);
		    doc.write(out);
		    out.close();
		}
	}

	public void actaConciliacionParcial(Solicitud solicitud) throws IOException {
		 
		String inputfilepath = "C:/Conalbos-Madiba/plantilla/actaConciliacionParcial.docx";
		String rutaActaConciliacion = "C:/Conalbos-Madiba/docs/Acta"+solicitud.getNroRadicado()+".docx";

		int ultimaAudiencia = solicitud.getAudiencias().size()-1;
		int ultimaAgenda = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().size()-1;
		int ultimaDesignacion = solicitud.getDesignacions().size()-1;
		
		ControllerSolicitud contSolicitud = new ControllerSolicitud();
    	String fechaAudiencia = new SimpleDateFormat("dd/MM/yyyy").format(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getFecha());
		String horaAudiencia = contSolicitud.MascaraHora(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getHoraInicial());
		
		
		String radicado = solicitud.getNroRadicado();
		String convocantes = "";
		String convocados = "";
		String asunto = solicitud.getAsunto();
		String identificacionConciliador = "";
		String identificacionConvocado = "";
		String identificacionConvocante = "";
		String nombreConvocados = "";
		String nombreConvocantes = "";
		String acuerdos = "";
		String noAcuerdos = "";
		
		//solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getConclusion();
		if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getTipoResultado().equalsIgnoreCase("NOACUERDO")){
			noAcuerdos = solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getConclusion();
		}else if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getTipoResultado().equalsIgnoreCase("ACUERDO")){
			acuerdos = solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(0).getConclusion();
		}
		if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(1).getTipoResultado().equalsIgnoreCase("NOACUERDO")){
			noAcuerdos = solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(1).getConclusion();
		}else if(solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(1).getTipoResultado().equalsIgnoreCase("ACUERDO")){
			acuerdos = solicitud.getAudiencias().get(ultimaAudiencia).getResultados().get(1).getConclusion();
		}
		
		String conciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getNombres()+" "+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getApellidos();
		
		identificacionConciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getNombres()+" "+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getApellidos()+", identificado con C.C No."+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getIdentificacion();
		
		for (Parte parte : solicitud.getPartes()) {
			if(parte.getTipoParte().equalsIgnoreCase("Convocado")){
				convocados += parte.getNombres()+" "+parte.getApellidos()+", ";
				nombreConvocados += parte.getNombres()+" "+parte.getApellidos()+"            ";
				identificacionConvocado += parte.getNombres()+" "+parte.getApellidos()+", identificado con C.C No. "+parte.getIdentificacion()+", ";
			}
			if(parte.getTipoParte().equalsIgnoreCase("Convocante")){
				convocantes += parte.getNombres()+" "+parte.getApellidos()+", ";
				nombreConvocantes += parte.getNombres()+" "+parte.getApellidos()+"            ";
				identificacionConvocante += parte.getNombres()+" "+parte.getApellidos()+", identificado con C.C No. "+parte.getIdentificacion()+", ";
			}
		}
		identificacionConvocado = identificacionConvocado.substring(0, identificacionConvocado.length()-2);
		System.out.println(inputfilepath);
		InputStream fs = new FileInputStream(inputfilepath);
		XWPFDocument doc = new XWPFDocument(fs); 
		
		for (XWPFParagraph p : doc.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		        for (XWPFRun r : runs) {
		        	String text = r.getText(0);
		        
		        	if (text != null){
		        		if (text.contains("$RADICADO")) {
		        			text = text.replace("$RADICADO", radicado);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONVOCANTES")) {
		        			text = text.replace("$CONVOCANTES", convocantes);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONVOCADOS")) {
		        			text = text.replace("$CONVOCADOS", convocados);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$ASUNTO")) {
		        			text = text.replace("$ASUNTO", asunto);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$HORAAUDIENCIA")) {
		        			text = text.replace("$HORAAUDIENCIA", horaAudiencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHAAUDIENCIA")) {
		        			text = text.replace("$FECHAAUDIENCIA", fechaAudiencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONCILIADOR")) {
		        			text = text.replace("$IDENTIFICACIONCONCILIADOR", identificacionConciliador);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONVOCANTE")) {
		        			text = text.replace("$IDENTIFICACIONCONVOCANTE", identificacionConvocante);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONVOCADO")) {
		        			text = text.replace("$IDENTIFICACIONCONVOCADO", identificacionConvocado);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$ACUERDOS")) {
		        			text = text.replace("$ACUERDOS", acuerdos);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$NOACUERDOS")) {
		        			text = text.replace("$NOACUERDOS", noAcuerdos);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONCILIADOR")) {
		        			text = text.replace("$CONCILIADOR", conciliador);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$NOMBRECONVOCANTES")) {
		        			text = text.replace("$NOMBRECONVOCANTES", nombreConvocantes);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$NOMBRECONVOCADOS")) {
		        			text = text.replace("$NOMBRECONVOCADOS", nombreConvocados);
		        			r.setText(text, 0);
		        		}
		        	}
		        }
		    }
		
		    FileOutputStream out = new FileOutputStream(rutaActaConciliacion);
		    doc.write(out);
		    out.close();
		}
	}

	
	public void constanciaNoConciliable(Solicitud solicitud) throws IOException {
		 
		String inputfilepath = "C:/Conalbos-Madiba/plantilla/constanciaNoConciliable.docx";
		String rutaConstanciaNoConciliable = "C:/Conalbos-Madiba/docs/constanciaNoConciliable"+solicitud.getNroRadicado()+".docx";

		int ultimaAudiencia = solicitud.getAudiencias().size()-1;
		int ultimaAgenda = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().size()-1;
		int ultimaDesignacion = solicitud.getDesignacions().size()-1;
		
		ControllerSolicitud contSolicitud = new ControllerSolicitud();
    	String fechaSolicitud = new SimpleDateFormat("dd/MM/yyyy").format(solicitud.getFecha());
		String fechaAudiencia = new SimpleDateFormat("dd/MM/yyyy").format(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getFecha());
		String horaAudiencia = contSolicitud.MascaraHora(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getHoraInicial());
		
		
		String radicado = solicitud.getNroRadicado();
		String convocados = "";
		String convocantes = "";
		String identificacionConvocado = "";
		String identificacionConvocante = "";
		String asunto = solicitud.getAsunto();
		String conciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getNombres()+" "+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getApellidos();
		String identificacionConciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getIdentificacion();
		
		for (Parte parte : solicitud.getPartes()) {
			if (parte.getTipoParte().equalsIgnoreCase("Convocado")) {
				convocados += parte.getNombres()+" "+parte.getApellidos()+", ";
				identificacionConvocado += parte.getNombres()+" "+parte.getApellidos()+", identificado con C.C No. "+parte.getIdentificacion()+", ";
			}
			if (parte.getTipoParte().equalsIgnoreCase("Convocante")) {
				convocantes += parte.getNombres()+" "+parte.getApellidos()+", ";
				identificacionConvocante += parte.getNombres()+" "+parte.getApellidos()+", identificado con C.C No. "+parte.getIdentificacion()+", ";
			}
		}
		identificacionConvocado = identificacionConvocado.substring(0, identificacionConvocado.length()-2);
		
		convocados = convocados.substring(0, convocados.length()-2);
		convocantes = convocantes.substring(0, convocantes.length()-2);
		
		System.out.println(inputfilepath);
		InputStream fs = new FileInputStream(inputfilepath);
		XWPFDocument doc = new XWPFDocument(fs); 
		
		for (XWPFParagraph p : doc.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		        for (XWPFRun r : runs) {
		        	String text = r.getText(0);
		        
		        	if (text != null){
		        		if (text.contains("$RADICADO")) {
		        			text = text.replace("$RADICADO", radicado);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONVOCADOS")) {
		        			text = text.replace("$CONVOCADOS", convocados);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("CONVOCANTES")) {
		        			text = text.replace("$CONVOCANTES", convocantes);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$ASUNTO")) {
		        			text = text.replace("$ASUNTO", asunto);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHASOLICITUD")) {
		        			text = text.replace("$FECHASOLICITUD", fechaSolicitud);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONVOCANTE")) {
		        			text = text.replace("$IDENTIFICACIONCONVOCANTE", identificacionConvocante);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONVOCADO")) {
		        			text = text.replace("$IDENTIFICACIONCONVOCADO", identificacionConvocado);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHAAUDIENCIA")) {
		        			text = text.replace("$FECHAAUDIENCIA", fechaAudiencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$HORAAUDIENCIA")) {
		        			text = text.replace("$HORAAUDIENCIA", horaAudiencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONCILIADOR")) {
		        			text = text.replace("$CONCILIADOR", conciliador);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONCILIADOR")) {
		        			text = text.replace("$IDENTIFICACIONCONCILIADOR", identificacionConciliador);
		        			r.setText(text, 0);
		        		}
		        	}
		        }
		    }
		
		    FileOutputStream out = new FileOutputStream(rutaConstanciaNoConciliable);
		    doc.write(out);
		    out.close();
		}
	}

	public void constanciaNoAcuerdo(Solicitud solicitud) throws IOException {
		 
		String inputfilepath = "C:/Conalbos-Madiba/plantilla/constanciaNoAcuerdo.docx";
		String rutaConstanciaNoAcuerdo = "C:/Conalbos-Madiba/docs/constanciaNoAcuerdo"+solicitud.getNroRadicado()+".docx";

		int ultimaAudiencia = solicitud.getAudiencias().size()-1;
		int ultimaAgenda = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().size()-1;
		int ultimaDesignacion = solicitud.getDesignacions().size()-1;
		
		ControllerSolicitud contSolicitud = new ControllerSolicitud();
    	String fechaSolicitud = new SimpleDateFormat("dd/MM/yyyy").format(solicitud.getFecha());
		String fechaAudiencia = new SimpleDateFormat("dd/MM/yyyy").format(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getFecha());
		String horaAudiencia = contSolicitud.MascaraHora(solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getHoraInicial());
		
		
		String radicado = solicitud.getNroRadicado();
		String convocados = "";
		String convocantes = "";
		String identificacionConvocado = "";
		String identificacionConvocante = "";
		String asunto = solicitud.getAsunto();
		String conciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getNombres()+" "+solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getApellidos();
		String identificacionConciliador = solicitud.getDesignacions().get(ultimaDesignacion).getConciliador().getIdentificacion();
		
		for (Parte parte : solicitud.getPartes()) {
			if (parte.getTipoParte().equalsIgnoreCase("Convocado")) {
				convocados += parte.getNombres()+" "+parte.getApellidos()+", ";
				identificacionConvocado += parte.getNombres()+" "+parte.getApellidos()+", identificado con C.C No. "+parte.getIdentificacion()+", ";
			}
			if (parte.getTipoParte().equalsIgnoreCase("Convocante")) {
				convocantes += parte.getNombres()+" "+parte.getApellidos()+", ";
				identificacionConvocante += parte.getNombres()+" "+parte.getApellidos()+", identificado con C.C No. "+parte.getIdentificacion()+", ";
			}
		}
		identificacionConvocado = identificacionConvocado.substring(0, identificacionConvocado.length()-2);
		
		convocados = convocados.substring(0, convocados.length()-2);
		convocantes = convocantes.substring(0, convocantes.length()-2);
		
		System.out.println(inputfilepath);
		InputStream fs = new FileInputStream(inputfilepath);
		XWPFDocument doc = new XWPFDocument(fs); 
		
		for (XWPFParagraph p : doc.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		        for (XWPFRun r : runs) {
		        	String text = r.getText(0);
		        
		        	if (text != null){
		        		if (text.contains("$RADICADO")) {
		        			text = text.replace("$RADICADO", radicado);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONVOCADOS")) {
		        			text = text.replace("$CONVOCADOS", convocados);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("CONVOCANTES")) {
		        			text = text.replace("$CONVOCANTES", convocantes);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$ASUNTO")) {
		        			text = text.replace("$ASUNTO", asunto);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHASOLICITUD")) {
		        			text = text.replace("$FECHASOLICITUD", fechaSolicitud);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONVOCANTE")) {
		        			text = text.replace("$IDENTIFICACIONCONVOCANTE", identificacionConvocante);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONVOCADO")) {
		        			text = text.replace("$IDENTIFICACIONCONVOCADO", identificacionConvocado);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$FECHAAUDIENCIA")) {
		        			text = text.replace("$FECHAAUDIENCIA", fechaAudiencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$HORAAUDIENCIA")) {
		        			text = text.replace("$HORAAUDIENCIA", horaAudiencia);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$CONCILIADOR")) {
		        			text = text.replace("$CONCILIADOR", conciliador);
		        			r.setText(text, 0);
		        		}
		        		if (text.contains("$IDENTIFICACIONCONCILIADOR")) {
		        			text = text.replace("$IDENTIFICACIONCONCILIADOR", identificacionConciliador);
		        			r.setText(text, 0);
		        		}
		        	}
		        }
		    }
		
		    FileOutputStream out = new FileOutputStream(rutaConstanciaNoAcuerdo);
		    doc.write(out);
		    out.close();
		}
	}

}
