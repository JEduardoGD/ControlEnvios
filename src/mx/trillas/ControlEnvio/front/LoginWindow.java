package mx.trillas.ControlEnvio.front;

import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillas.ControlEnvio.persistence.dao.TipousuarioDAO;
import mx.trillas.ControlEnvio.persistence.dao.UsuarioDAO;
import mx.trillas.ControlEnvio.persistence.dao.TipousuarioDAO.TIPOS_USUARIO;
import mx.trillas.ControlEnvio.persistence.factory.ImplFactory;
import mx.trillas.ControlEnvio.persistence.impl.TipousuarioDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Usuario;

public class LoginWindow {
	private static UsuarioDAO usuarioDAO = ImplFactory.getUsuarioDAO();
	private static Logger logger = Logger.getLogger(UsuarioDAO.class);
	private static TipousuarioDAO tipousuarioDAO = new TipousuarioDAODBImpl();
	
	public void LoginStage(Stage stage) {

		try {
			VBox rootPane = new VBox();
			Scene scene = new Scene(rootPane, 450, 450);

			FlowPane usernamePane = new FlowPane(50, 50);
			FlowPane passwdPane = new FlowPane(20, 20);

			rootPane.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/login.css").toExternalForm());

			Text introText = new Text("Control de paquetería");
			introText.setId("introText");

			Text loginText = new Text("Acceso");
			loginText.setId("loginText");

			rootPane.getChildren().addAll(introText, loginText);

			Label usernameLabel = new Label("Usuario");
			usernameLabel.getStyleClass().add("labels");
			TextField usernameField = new TextField();

			usernamePane.getChildren().addAll(usernameLabel, usernameField);

			Label passwdLabel = new Label("Contraseña");
			passwdLabel.getStyleClass().add("labels");
			PasswordField passwdField = new PasswordField();

			usernamePane.setAlignment(Pos.CENTER);
			passwdPane.setAlignment(Pos.CENTER);
			passwdPane.getChildren().addAll(passwdLabel, passwdField);

			rootPane.getChildren().addAll(usernamePane, passwdPane);

			Button submitButton = new Button("Aceptar");
			submitButton.setId("submitButton");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					String contentUsernameField = usernameField.getText();
					String contentPasswdField = passwdField.getText();

					String username = contentUsernameField;
					String password = contentPasswdField;
					Usuario usuario = null;
					MenuWindow menu = new MenuWindow();
					
					if (contentUsernameField == null) {
						logger.error("Username nulo");
					}
					if (contentUsernameField.equals("")) {
						logger.error("Username vacio" );
					}
					if (contentPasswdField == null) {
						logger.error("Password nulo" );
					}
					if (contentPasswdField.equals("")) {
						logger.error("Password vacio" );
					}
					
					try {
						usuario = usuarioDAO.getByUsernameAndPassword(username, password);

						if (usuario != null) {
								
								System.out.println(usuario.getTiposusuario());
							
								if (usuario.getTiposusuario().equals(tipousuarioDAO.getTipoDeusuario(TIPOS_USUARIO.TIPOUSUARIO_ADMINISTRADOR))) {
									menu.AdminMenuStage(stage);
								} 
								else if (usuario.getTiposusuario().equals(tipousuarioDAO.getTipoDeusuario(TIPOS_USUARIO.TIPOUSUARIO_CAPTURISTA))) {
									menu.UserMenuStage(stage);
								}
								else {
									logger.error("Usuario no contiene los permisos necesarios de acceso.");
								}
							} else {
								logger.error("El usuario ingresado no existe" );
							}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			});
			rootPane.getChildren().add(submitButton);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Login");
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
