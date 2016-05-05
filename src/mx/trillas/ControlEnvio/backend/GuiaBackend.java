package mx.trillas.ControlEnvio.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.trillas.ControlEnvio.persistence.dao.GuiaDAO;
import mx.trillas.ControlEnvio.persistence.impl.GuiaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Guia;

public class GuiaBackend {

	 private static final String NUMERO_GUIA_PATTERN = "([a-zA-Z]{1}[0-9]{3,44})";
	 private static final String STRING_PATTERN = "([a-zA-ZpáéíóúÁÉÍÓÚ\\s]){3,45}";
	 
	 private static Logger logger = Logger.getLogger(CapturarRegistro.class);
	 
	 private static GuiaDAO guiaDAO = new GuiaDAODBImpl();
	 
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
			 guiaDAO.altaGuia(guia);
		} catch (Exception e) {
			throw e;
		}
	 }
	 
	 public static ObservableList<Guia> getGuiaData() throws Exception {

		List<Guia> guias = new ArrayList<Guia>();
		ObservableList<Guia> data = FXCollections.observableArrayList();
		
		try {
			guias = guiaDAO.getGuiaList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		for (Guia element : guias) {
			data.add(element);
		}
		return data;
	}
}