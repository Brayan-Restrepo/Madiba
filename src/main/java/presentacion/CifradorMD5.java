package presentacion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CifradorMD5 {
	
	private String hash(String clear) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
	        byte[] b = md.digest(clear.getBytes()); 
	        int size = b.length;
	        StringBuffer h = new StringBuffer(size); 
	        for (int i = 0; i < size; i++) { 
	            int u = b[i]&255; // unsigned conversion 
	            if (u<16) { 
	                h.append("0"+Integer.toHexString(u)); 
	            } else { 
	                h.append(Integer.toHexString(u)); 
	            } 
	        } 
	        return h.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return null; 
    } 

    public String MD5(String palabra) { 
        String pe = ""; 
        try { 
            pe = hash(palabra); 
        } catch (Exception e) { 
            throw new Error("Error: Al encriptar el password");     
        } 
        return pe; 
    }
}
