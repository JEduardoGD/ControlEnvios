package mx.trillas.ControlEnvio.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import mx.trillas.ControlEnvio.persistence.dao.DepartamentoDAO;
import mx.trillas.ControlEnvio.persistence.impl.DepartamentoDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

public class DepartamentoBackend {

	 private static final String STRING_PATTERN = "([a-zA-ZpáéíóúÁÉÍÓÚ\\s]){3,45}";

	 private static Logger logger = Logger.getLogger(Departamento.class);

	 private static DepartamentoDAO departamentoDAO = new DepartamentoDAODBImpl();

	 public static boolean checkString(String phrase){

		  Pattern pattern;
		  Matcher matcher;

		  pattern = Pattern.compile(STRING_PATTERN);
		  matcher = pattern.matcher(phrase);

		  return matcher.matches();
	 }

	 public static void loadDepartamentoData(String nombreDepartamento) throws Exception {
		 Departamento departamento = null;
		 departamento  = new Departamento();

		 departamento.setNombre(nombreDepartamento);

		 try {
			 departamentoDAO.altaDepartamento(departamento);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	 }
	 
	 public static ComboBox<Object> getDeptosListCombo() throws Exception {
			ComboBox<Object> departamentos = new ComboBox<>();
			
			ObservableList<Departamento> departamentoList = FXCollections.observableArrayList(departamentoDAO.getDepartamentoList());
	
			for (Departamento element : departamentoList) {
				String elementFormat = element.toString().substring(0, 1).toUpperCase()	+ element.toString().substring(1);
				departamentos.getItems().addAll(elementFormat);
			}
			return departamentos;
	 }
	 
	 public static ObservableList<String> getDepartamentoData() throws Exception {

			List<Departamento> departamentos = new ArrayList<Departamento>();
			ObservableList<String> data = FXCollections.observableArrayList();
			
			try {
				departamentos = departamentoDAO.getDepartamentoList();
			} catch (Exception e) {
				throw e;
			}
			for (Departamento element : departamentos) {
				String elementFormat = element.getNombre().toString().substring(0, 1).toUpperCase()
						+ element.getNombre().toString().substring(1);
				data.add(elementFormat);
			}
			return data;
	}
}
