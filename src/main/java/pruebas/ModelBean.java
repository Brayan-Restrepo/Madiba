package pruebas;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;

 @ManagedBean 
 @ViewScoped  
 public class ModelBean {  
        
      private String firstName;  
      private String lastName;  
        
      public ModelBean() { 
      }  
        
      /**  
       * set submitted values to flash scope and   
       * redirect to confirm view  
       * @return  
       */  
      public String goToConfirmView() {  
           Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();  
           flash.put("firstName", firstName);  
           flash.put("lastName", lastName);  
             
           return "confirm?faces-redirect=true";  
      } 
        
      /**  
       * redirect to confirm view   
       * @return  
       */  
      public String goToInputFormView() {  
           return "inputForm?faces-redirect=true";  
      }  
   
      /**  
       * Called on confirm.xhtml page  
       * here could be some database   
       * processing  
       * @return  
       */  
      public String insertValue() {  
           Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();    
           flash.setKeepMessages(true);    
           pullValuesFromFlash(null);  
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Value inserted"));  
           return "done?faces-redirect=true";  
      }  
        
      /**  
       * System event called before view rendering   
       * used to pull values from flash and set to  
       * bean properties  
       * @param e  
       */  
      public void pullValuesFromFlash(ComponentSystemEvent e) {  
           Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();  
           firstName = (String)flash.get("firstName");  
           lastName = (String)flash.get("lastName");  
      }  
        
             
      public String getFirstName() {  
           return firstName;  
      }  
   
      public void setFirstName(String firstName) {  
           this.firstName = firstName;  
      }  
   
      public String getLastName() {  
           return lastName;  
      }  
   
      public void setLastName(String lastName) {  
           this.lastName = lastName;  
      }  
 }  