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
@ManagedBean
@ViewScoped
public class FileUtilities {
	
	public FileUtilities(){
		this.nombresFile = new ArrayList<String>();
		this.files = new ArrayList<Part>();
	}
	
	
	private Part file;
	private List<Part> files;
	
	private List<String> nombresFile;
	private Long nombreFile;
	
	public Long getNombreFile() {
		return nombreFile;
	}

	public void setNombreFile(Long nombreFile) {
		
		this.nombreFile = nombreFile;
		System.out.println("setNambreFile ->"+nombreFile);
		this.nombresFile.add(this.nombreFile+"");
	}

	public Part getFile() {
		System.out.println("getFile");
	    return file;
	}
	
	public void setFile(Part file) {
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		    this.file = file;
			files.add(file);
	}
	
    public List<Part> getFiles() {
		return files;
	}

	public void setFiles(List<Part> files) {
		this.files = files;
	}

	public List<String> getNombresFile() {
		return nombresFile;
	}

	public void setNombresFile(List<String> nombresFile) {
		this.nombresFile = nombresFile;
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
    
    public String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); 
            }
        }
        return null;
    }
    
    public String getFileExtention(String fileName) {
        String extention = ".";
        String[] name = fileName.split("[.]");
        extention += name[name.length-1];
        return extention;
    }
    public String getFileExtentionSinPunto(String fileName) {
        String extention = "";
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
    

	public void download(String path, String name) {
		String Type = "";
		//String name="18";
		String extention=getFileExtentionSinPunto(path);
		
		if(extention.equalsIgnoreCase("jpg") || extention.equalsIgnoreCase("png")){
			Type = "image/"+extention;
		}else {
			if (extention.equalsIgnoreCase("pdf")){
				Type = "application/pdf";
			}else if (extention.equalsIgnoreCase("docx")){
				Type = "application/docx";
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
            FileInputStream inputStream = new FileInputStream(new File(path));
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

	
	public void download2(String path, String name, String extention) {
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
	/*
		String path = "C:/Conalbos-Madiba/Solicitud #1/Audiencia #1";
		String folderName = "Excusa NombreParte";
		String fileName = "excusaFecha";
		createFolder(path,folderName);
		upload(this.file,path+"/"+folderName,fileName);
	}
	
	public void GuardarExcusas(){
		Long idSolicitud=5L;
		Long idAudiencia=7L;
		if(this.nombresFile.size()==this.files.size()){
			for(int i=0; i<this.files.size(); i++){
				if(this.files.get(i)!=null){
					String path = "C:/Conalbos-Madiba/Solicitud #"+idSolicitud+"/Audiencia #"+idAudiencia;
					String folderName = "Excusas";
					//String fileName = nombreExcusa(this.files.get(i));
					String fileName = this.nombresFile.get(i);
					createFolder(path,folderName);
					upload(this.files.get(i),path+"/"+folderName,fileName);
				}
			}
		}else {
			System.out.println(this.nombresFile.size()+" ERROR  "+this.files.size());
		}
	}
	public String nombreExcusa(Part file){
		String nombre = file.getName();
		nombre = nombre.split("[:]")[nombre.split("[:]").length-1];
		return nombre;
	}
	*/
	
	
}