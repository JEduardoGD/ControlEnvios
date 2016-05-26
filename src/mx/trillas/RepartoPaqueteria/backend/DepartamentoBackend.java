package mx.trillas.RepartoPaqueteria.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import mx.trillas.RepartoPaqueteria.persistence.dao.DepartamentoDAO;
import mx.trillas.RepartoPaqueteria.persistence.impl.DepartamentoDAODBImpl;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Departamento;

public class DepartamentoBackend {

	private static final String STRING_PATTERN = "([a-zA-ZpáéíóúÁÉÍÓÚ\\sñÑ.,]){3,45}";

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
	 
	 public static ObservableList<Departamento> getDepartamentoData() throws Exception {

			List<Departamento> departamentos = new ArrayList<Departamento>();
			try {
				departamentos = departamentoDAO.getDepartamentoList();
			} catch (Exception e) {
				throw e;
			}
			ObservableList<Departamento> data =  FXCollections.observableList(departamentos);
			return data;
	}
	 
	public static ObservableList<String> getDepartamentoCombo() throws Exception {

			List<Departamento> departamentos = new ArrayList<Departamento>();
			ObservableList<String> data = FXCollections.observableArrayList();
			
			try {
				departamentos = departamentoDAO.getDepartamentoList();
			} catch (Exception e) {
				throw e;
			}
			for (Departamento element : departamentos) {
				String elementFormat = element.getNombre().substring(0, 1).toUpperCase() + element.getNombre().substring(1);
				data.add(elementFormat);
			}
			return data;
	}
}
