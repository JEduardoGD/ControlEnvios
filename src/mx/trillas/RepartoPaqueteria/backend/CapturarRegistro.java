package mx.trillas.RepartoPaqueteria.backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import mx.trillas.RepartoPaqueteria.persistence.dao.GuiaDAO;
import mx.trillas.RepartoPaqueteria.persistence.impl.GuiaDAODBImpl;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Guia;

public class CapturarRegistro {
	 
	 private static final String NUMERO_GUIA_PATTERN = "([a-zA-Z0-9]{3,44})";
	 private static final String STRING_PATTERN = "([a-zA-ZpáéíóúÁÉÍÓÚ\\sñÑ.,]){3,45}";
	 
	 private static Logger logger = Logger.getLogger(CapturarRegistro.class);
	 
	 private static GuiaDAO capturaDAO = new GuiaDAODBImpl();
	 
	 public static boolean checkStructData(String guia, String origen, String depto, String destinatario){
	 
		if (checkStructNumeroGuia(guia)) {
			 return true;
		} else {
			logger.error("El numero guia ingresado no contiene la estructura requerida.");
		}
		
		if (checkString(origen)) {
			 return true;
		} else {
			logger.error("El origen ingresado no contiene la estructura requerida.");
		} 
				
		if (checkString(depto)) {
			 return true;
		} else {
			logger.error("El departamento ingresado no contiene la estructura requerida.");
		}
		
		if (checkString(destinatario)) {
			 return true;
		} else {
			logger.error("El destinatario ingresado no contiene la estructura requerida.");
		}
		
		return false;
	 }
 
	 public static boolean checkStructNumeroGuia(String guia){

		  Pattern pattern;
		  Matcher matcher;
	 
		  pattern = Pattern.compile(NUMERO_GUIA_PATTERN);
		  matcher = pattern.matcher(guia);
		  
		  return matcher.matches();
	 }
	
	 public static boolean checkString(String phrase){
		  
		  Pattern pattern;
		  Matcher matcher;
	 
		  pattern = Pattern.compile(STRING_PATTERN);
		  matcher = pattern.matcher(phrase);
		  
		  return matcher.matches();
	 }
	 
	 public static void loadCapturaData(Guia guia) throws Exception {
		try {
			 capturaDAO.altaGuia(guia);
		} catch (Exception e) {
			throw e;
		}
	 }
}
