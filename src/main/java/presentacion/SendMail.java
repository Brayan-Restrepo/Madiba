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
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

@ManagedBean 
public class SendMail
{
	@Resource(mappedName="java:jboss/mail/Gmail")
    private Session mailSession;
 
    public void emailJboss() {
        {
 
            try    {
            	BodyPart texto = new MimeBodyPart();
            	texto.setText("Texto del mensaje");
            	
            	BodyPart adjunto = new MimeBodyPart();
            	adjunto.setDataHandler(new DataHandler(new FileDataSource("C:/Users/Brayan Restrepo/Pictures/Prologa.doc")));
            	adjunto.setFileName("Prologa.doc");
            	
            	MimeMultipart multiParte = new MimeMultipart();

            	multiParte.addBodyPart(texto);
            	multiParte.addBodyPart(adjunto);
            	
            	MimeMessage m = new MimeMessage(mailSession);
                Address from = new InternetAddress("conalbos.madiba@gmail.com");
                Address[] to = new InternetAddress[] {new InternetAddress("juanseca94@gmail.com") };
 
               m.setFrom(from);
                m.setRecipients(Message.RecipientType.TO, to);
                m.setSubject("JBoss AS 7 Mail");
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
