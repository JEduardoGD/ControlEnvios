package mx.trillas.ControlEnvio.backend;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import mx.trillas.ControlEnvio.persistence.dao.DepartamentoDAO;
import mx.trillas.ControlEnvio.persistence.dao.DestinatarioDAO;
import mx.trillas.ControlEnvio.persistence.impl.DepartamentoDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.DestinatarioDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Destinatario;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;

public class DestinatarioBackend {
	
	private static final String STRING_PATTERN = "([a-zA-ZpáéíóúÁÉÍÓÚ\\s]){3,45}";
	private static DestinatarioDAO destinatarioDAO = new DestinatarioDAODBImpl();
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAODBImpl();
	private static Logger logger = Logger.getLogger(DestinatarioBackend.class);
	
	public static boolean checkString(String phrase){
		  
		  Pattern pattern;
		  Matcher matcher;
	 
		  pattern = Pattern.compile(STRING_PATTERN);
		  matcher = pattern.matcher(phrase);
		  
		  return matcher.matches();
	 }
	 
	 public static void loadDestinatarioData(String nombreDestinatario, String nombreDepartamento) throws Exception {
		 Destinatario destinatario = null;
		 Departamento departamentoObj = null;
		 
		 destinatario = new Destinatario();
		 destinatario.setNombre(nombreDestinatario);
		 
		 try {
			 departamentoObj = departamentoDAO.getDepartamento(nombreDepartamento);
		 } catch (Exception e) {
			 logger.error(e.getMessage());
			throw e;
		 }
		 
		 try {
			 if (departamentoObj != null) {
				 destinatario.setDepartamento(departamentoObj);
				 destinatarioDAO.altaDestinatario(destinatario);
			 } else {
				 logger.error("Departamento no existe");
				 throw new Exception("Departamento no existe");
			 }
		} catch (Exception e) {
			throw e;
		}
	 }
}
