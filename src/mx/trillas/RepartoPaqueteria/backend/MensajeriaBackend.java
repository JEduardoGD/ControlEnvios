package mx.trillas.RepartoPaqueteria.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.trillas.RepartoPaqueteria.persistence.dao.MensajeriaDAO;
import mx.trillas.RepartoPaqueteria.persistence.impl.MensajeriaDAODBImpl;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Mensajeria;

public class MensajeriaBackend {

	 private static final String STRING_PATTERN = "([a-zA-ZpáéíóúÁÉÍÓÚ\\sñÑ.,]){3,45}";

	 private static Logger logger = Logger.getLogger(MensajeriaBackend.class);

	 private static MensajeriaDAO mensajeriaDAO = new MensajeriaDAODBImpl();

	 public static boolean checkString(String phrase){

		  Pattern pattern;
		  Matcher matcher;

		  pattern = Pattern.compile(STRING_PATTERN);
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
			logger.error(e.getMessage());
			throw e;
		}
	 }
	 
	public static ObservableList<Mensajeria> getMensajeriaData() throws Exception {

		List<Mensajeria> mensajerias = new ArrayList<Mensajeria>();
		ObservableList<Mensajeria> data = FXCollections.observableArrayList();
		
		try {
			mensajerias = mensajeriaDAO.getMensajeriaList();
		} catch (Exception e) {
			throw e;
		}
		for (Mensajeria element : mensajerias) {
			data.add(element);
		}
		return data;
	}
}
