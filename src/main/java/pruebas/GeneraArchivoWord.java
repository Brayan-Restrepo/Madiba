package pruebas;



import org.apache.poi.xwpf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class GeneraArchivoWord {
	
	
	public String replaceTextFound(int id, String nombre, String texto) throws IOException {
		 
		String inputfilepath = "C:/Conalbos-Madiba/plantilla/citacion.docx";
		String outputfilepath = "C:/Conalbos-Madiba/docs/"+id+"_citacion.docx";
		String relativeOutputfilepath = "/docs/"+id+"_citacioin.docx";
		System.out.println(inputfilepath);
		System.out.println(outputfilepath);
		InputStream fs = new FileInputStream(inputfilepath);
		XWPFDocument doc = new XWPFDocument(fs); 
		
		for (XWPFParagraph p : doc.getParagraphs()) {
		    List<XWPFRun> runs = p.getRuns();
		    if (runs != null) {
		        for (XWPFRun r : runs) {
		        	String text = r.getText(0);
		        
		        	if (text != null){
		        		if (text.contains("$NOMBREPARTE")) {
		        			text = text.replace("$NOMBREPARTE", "Cualquie nombre");
		        			r.setText(text, 0);
		        		}
		        }
		    }
		}
		
		FileOutputStream out = new FileOutputStream(outputfilepath);
	    doc.write(out);
	    out.close();
	}
		return relativeOutputfilepath;
  }
}



