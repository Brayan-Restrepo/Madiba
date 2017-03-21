package presentacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author Murad R. Imanbayli <muradimanbayli at gmail.com>
 */
@ViewScoped
@ManagedBean
public class FileUtilities {
	private Part file;
	private List<Part> files;
	
	public Part getFile() {
		System.out.println("getFile");
	    return file;
	}
	
	public void setFile(Part file) {
		if(file != null){
			if(files == null){
				files = new ArrayList<Part>();	
				files.add(file);
			}else{
				files.add(file);
			}
		    this.file = file;
		}
	}

    public void upload(Part file, String path, String name){
    	try{
            InputStream input=file.getInputStream();
            File f=new File(path+"/"+name+getFileExtention(getFileName(file)));
            if(!f.exists()){
                f.createNewFile();
            }
            FileOutputStream output=new FileOutputStream(f);
            byte[] buffer=new byte[1024];
            int length;
            while((length=input.read(buffer))>0){
                output.write(buffer, 0, length);
            }
            
            input.close();
            output.close();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
    
    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); 
            }
        }
        return null;
    }
    
    private String getFileExtention(String fileName) {
        String extention = ".";
        String[] name = fileName.split("[.]");
        extention += name[name.length-1];
        return extention;
    }
    
    public void createFolder(String path, String name){
    	File folder = new File(path+"\\"+name);
    	if(!folder.exists()){
    		folder.mkdirs(); 
        }
    }

	public void download(String path, String name, String extention) {
		String Type = "";
		if(extention.equalsIgnoreCase("jpg") || extention.equalsIgnoreCase("png")){
			Type = "image/"+extention;
		}else {
			if (extention.equalsIgnoreCase("pdf")){
				Type = "application/pdf";
			}else{
				Type = "application/zip";
			}
		}
		
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            externalContext.responseReset();
            externalContext.setResponseContentType(Type);
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\""+name+"."+extention+"\"");
            FileInputStream inputStream = new FileInputStream(new File(path+"/"+name+"."+extention));
            OutputStream outputStream = externalContext.getResponseOutputStream();

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
	
	// -----------------------------------------------------------------------------------
	public void GuardarExcusa(){
		String path = "C:/Conalbos-Madiba/Solicitud #1/Audiencia #1";
		String folderName = "Excusa NombreParte";
		String fileName = "excusaFecha";
		createFolder(path,folderName);
		upload(this.file,path+"/"+folderName,fileName);
	}
	
	public void GuardarExcusas(Long idSolicitud, Long idAudiencia){
		for(int i=0; i<this.files.size(); i++){
			String path = "C:/Conalbos-Madiba/Solicitud #"+idSolicitud+"/Audiencia #"+idAudiencia;
			String folderName = "Excusas";
			String fileName = nombreExcusa(this.files.get(i));
			createFolder(path,folderName);
			upload(this.files.get(i),path+"/"+folderName,fileName);
		}
	}
	
	public String nombreExcusa(Part file){
		String nombre = file.getName();
		nombre = nombre.split("[:]")[nombre.split("[:]").length-1];
		return nombre;
	}
	
}