package mx.trillas.ControlEnvio.backend;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

import mx.trillas.ControlEnvio.persistence.dao.GuiaDAO;
import mx.trillas.ControlEnvio.persistence.impl.GuiaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;
import mx.trillas.ControlEnvio.persistence.pojos.Destinatario;
import mx.trillas.ControlEnvio.persistence.pojos.Guia;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;
import mx.trillas.ControlEnvio.persistence.pojos.Origen;
import mx.trillas.ControlEnvio.persistence.pojos.Usuario;

public class CapturarRegistro {
	 
	 private static final String NUMERO_GUIA_PATTERN = "([a-zA-Z]{1}[0-9]{12,44})";
	 private static final String STRING_PATTERN = "([a-zA-Z]){4,45}";
	 
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
	 
	 public static void loadCapturaData(String origen, String depto, String destinatario, String departamento, String mensajeria, String guiaField, String observacion, String otroOrigen, String otroDestinatario) throws Exception {
		 Guia guia = new Guia();
				guia.setDestinatario(new Destinatario(new Departamento(destinatario), destinatario));
				guia.setMensajeria(new Mensajeria(mensajeria));
				guia.setOrigen(new Origen(origen));
				guia.setUsuario(new Usuario());
				guia.setNumero(guiaField);
				guia.setObservacion(observacion);
				guia.setFecha(new Date());
				guia.setOtroorigen(otroOrigen);
				guia.setOtrodestinatario(otroDestinatario);
		capturaDAO.altaGuia(guia);
	 }
}
