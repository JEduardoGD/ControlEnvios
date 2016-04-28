package mx.trillas.ControlEnvio.backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import mx.trillas.ControlEnvio.persistence.dao.MensajeriaDAO;
import mx.trillas.ControlEnvio.persistence.impl.MensajeriaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

public class MensajeriaBackend {

	 private static final String STRING_PATTERN = "([a-zA-ZpáéíóúÁÉÍÓÚ\\s]){3,45}";
	 private static final String RIGHT_PATTERN = "^[a-zA-Z]*";
	 
	 private static Logger logger = Logger.getLogger(MensajeriaBackend.class);
	 
	 private static MensajeriaDAO mensajeriaDAO = new MensajeriaDAODBImpl();
	 
	 public static boolean checkString(String phrase){
		  
		  Pattern pattern;
		  Matcher matcher;
	 
		  pattern = Pattern.compile(STRING_PATTERN);
		  matcher = pattern.matcher(phrase);
		  
		  return matcher.matches();
	 }
	  public static boolean checkRightString(String phrase){
		  
		  Pattern pattern;
		  Matcher matcher;
	 
		  pattern = Pattern.compile(RIGHT_PATTERN);
		  matcher = pattern.matcher(phrase);
		  
		  return matcher.matches();
	 }
	 public static void loadMensajeriaData(String nombreMensajeria) throws Exception{
		 Mensajeria mensajeria = null;
		 mensajeria  = new Mensajeria();
		 
		 mensajeria.setNombre(nombreMensajeria);
		 
		 try {
			 mensajeriaDAO.altaMensajeria(mensajeria);
		} catch (Exception e) {
			throw e;
		}
	 }
}
