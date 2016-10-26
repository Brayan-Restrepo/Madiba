package presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 

@ManagedBean
public class ControllerLogin {
	

	public void submit() {	
			
		System.out.println("....");
		    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		
    }
}
