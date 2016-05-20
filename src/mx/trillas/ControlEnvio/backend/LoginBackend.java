package mx.trillas.ControlEnvio.backend;

import mx.trillas.ControlEnvio.front.MenuWindow;
import mx.trillas.ControlEnvio.persistence.dao.TipousuarioDAO;
import mx.trillas.ControlEnvio.persistence.dao.UsuarioDAO;
import mx.trillas.ControlEnvio.persistence.dao.TipousuarioDAO.TIPOS_USUARIO;
import mx.trillas.ControlEnvio.persistence.factory.ImplFactory;
import mx.trillas.ControlEnvio.persistence.impl.TipousuarioDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Usuario;
import org.apache.log4j.Logger;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginBackend {
	
	private static Logger logger = Logger.getLogger(LoginBackend.class);
	
	private static UsuarioDAO usuarioDAO = ImplFactory.getUsuarioDAO();
	private static TipousuarioDAO tipousuarioDAO = new TipousuarioDAODBImpl();
	
	public static boolean checkLoginData(TextField usernameField, TextField passwdField) {
		String contentUsernameField = usernameField.getText();
		String contentPasswdField = passwdField.getText();
		
		if (contentUsernameField == null) {
			logger.error("Username nulo");
			return false;
		}
		if (contentUsernameField.equals("")) {
			logger.error("Username vacio" );
			return false;
		}
		if (contentPasswdField == null) {
			logger.error("Password nulo" );
			return false;
		}
		if (contentPasswdField.equals("")) {
			logger.error("Password vacio" );
			return false;
		}
		return true;
	}

	public static void getMenuUser(Stage stage, String username, String passwd) throws Exception {
		
		try {
			Usuario usuario = null;
			MenuWindow menu = new MenuWindow();
			
			usuario = usuarioDAO.getByUsernameAndPassword(username, passwd);

			if (usuario != null) {
					
				if (usuario.getTiposusuario().equals(tipousuarioDAO.getTipoDeusuario(TIPOS_USUARIO.TIPOUSUARIO_ADMINISTRADOR))) {
					menu.AdminMenuStage(stage);
				} 
				else if (usuario.getTiposusuario().equals(tipousuarioDAO.getTipoDeusuario(TIPOS_USUARIO.TIPOUSUARIO_CAPTURISTA))) {
					menu.UserMenuStage(stage, usuario);
				}
				else {
					logger.error("Usuario no contiene los permisos necesarios de acceso.");
				}
			} else {
				logger.error("El usuario ingresado no existe" );
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static boolean existUser(String username, String passwd) throws Exception {
		
		Usuario usuario = null;
		
		try {
			usuario = usuarioDAO.getByUsernameAndPassword(username, passwd);

			if (usuario != null) {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}
}
