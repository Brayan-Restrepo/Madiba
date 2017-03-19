package presentacion;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import entidades.Parte;
import entidades.Solicitud;
import negocio.iSolicitudBean;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean 
public class SendMail
{
    @Resource(mappedName="java:jboss/mail/Gmail")
    private Session mailSession;
 
    @EJB
    private iSolicitudBean solicitudBean;
    
    public void enviarCitacion(List<Long> idSolicitud){
    	
    	for (Long long1 : idSolicitud) {
    		
			Solicitud solicitud = this.solicitudBean.findSolicitud(long1);
			
			Date fechaActual = new Date();
	    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	    	String cadenaFecha = formato.format(fechaActual);
	    	
			int ultimaAudiencia =solicitud.getAudiencias().size()-1;
			int ultimaAgenda = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().size()-1;
			String fechaAudiencia = solicitud.getAudiencias().get(ultimaAudiencia).getAgendas().get(ultimaAgenda).getFecha().toString();
			
			if(fechaAudiencia.equals(cadenaFecha)){
				this.solicitudBean.actualizarEstadoSolicitud(long1, "AUDIENCIA-ENCURSO");
			}else{
				this.solicitudBean.actualizarEstadoSolicitud(long1, "AUDIENCIA-PENDIENTE");
			}
			
			//Envio de citacion al Conciliador
    		//this.emailCitacion(solicitud.getDesignacions().get(solicitud.getDesignacions().size()-1).getConciliador().getCorreo(), "Conciliador");

    		List<Parte> parte = solicitud.getPartes();
			for (Parte parte2 : parte) {
				this.emailCitacion(parte2.getCorreo(), parte2.getTipoParte());
			}
		}
    	
    }
    
    /**
     * 
     * @param emailParte El email
     * @param rol El rol al que se le envia Conciliado, convocante, Convocado
     */
    public void emailCitacion(String emailParte, String rol) {
        {
 
            try    {
            	BodyPart texto = new MimeBodyPart();
            	texto.setText("Señor "+rol);
            	
            	BodyPart adjunto = new MimeBodyPart();
            	//adjunto.setDataHandler(new DataHandler(new FileDataSource("C:/Users/Brayan Restrepo/Pictures/Prologa.doc")));
            	//adjunto.setFileName("Prologa.doc");
            	
        		File file = new File("C:/Users/Brayan Restrepo/workspace/Madiba/src/main/webapp/resources/img/conalbos.png");
        	    DataSource source = new FileDataSource(file);
        	    
            	adjunto.setDataHandler(new DataHandler(source));
            	adjunto.setFileName("conalbos.png");
            	
            	MimeMultipart multiParte = new MimeMultipart();

            	multiParte.addBodyPart(texto);
            	multiParte.addBodyPart(adjunto);
            	
            	MimeMessage m = new MimeMessage(mailSession);
                Address from = new InternetAddress("conalbos.madiba@gmail.com");
                Address[] to = new InternetAddress[] {new InternetAddress(emailParte) };
 
                m.setFrom(from);
                m.setRecipients(Message.RecipientType.TO, to);
                m.setSubject("Citacion de Audiencia - Conalbos");
               /* m.setSentDate(new java.util.Date());
                m.setContent("Mail sent from JBoss AS 7","text/plain");
                */
                m.setContent(multiParte);
                Transport.send(m);
                System.out.println("Mail sent!");
            }
            catch (javax.mail.MessagingException e)
            {
                e.printStackTrace();
                System.out.println("Error in Sending Mail: "+e);
            }
        }
    }
}
