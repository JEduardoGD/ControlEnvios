package mx.trillas.ControlEnvio.backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.trillas.ControlEnvio.persistence.dao.OrigenesDAO;
import mx.trillas.ControlEnvio.persistence.impl.OrigenesDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Origen;

public class OrigenBackend {
	
	 private static final String STRING_PATTERN = "([a-zA-Z]){4,45}";
	 private static OrigenesDAO origenDAO = new OrigenesDAODBImpl();
	
	public static boolean checkString(String phrase){
		  
		  Pattern pattern;
		  Matcher matcher;
	 
		  pattern = Pattern.compile(STRING_PATTERN);
		  matcher = pattern.matcher(phrase);
		  
		  return matcher.matches();
	 }
	 
	 public static void loadOrigenData(String nombreOrigen) throws Exception {
		 Origen origen = null;
		 origen  = new Origen();
		 
		 origen.setNombre(nombreOrigen);
		 
		 try {
			 origenDAO.altaOrigen(origen);
		} catch (Exception e) {
			throw e;
		}
	 }
}
