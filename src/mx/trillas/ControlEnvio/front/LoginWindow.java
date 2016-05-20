package mx.trillas.ControlEnvio.front;

import org.apache.log4j.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mx.trillas.ControlEnvio.backend.LoginBackend;
import mx.trillas.ControlEnvio.persistence.dao.UsuarioDAO;

public class LoginWindow {

	private static Logger logger = Logger.getLogger(UsuarioDAO.class);

	public void LoginStage(Stage stage) {

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		
		VBox rootPane = new VBox();
		Scene scene = new Scene(rootPane, 450, 490);

		FlowPane usernamePane = new FlowPane(50, 50);
		FlowPane passwdPane = new FlowPane(20, 20);

		Alert alert = new Alert(AlertType.WARNING, "content text");
		alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
				.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
		alert.setTitle("Acceso a control de paqueteria");
		alert.setHeaderText(null);

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
		usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (usernameField.getText().length() > 44) {
				String s = usernameField.getText().substring(0, 44);
				usernameField.setText(s);
			}
		});
		usernamePane.getChildren().addAll(usernameLabel, usernameField);

		Label passwdLabel = new Label("Contraseña");
		passwdLabel.getStyleClass().add("labels");

		PasswordField passwdField = new PasswordField();
		passwdField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (passwdField.getText().length() > 44) {
				String s = passwdField.getText().substring(0, 44);
				passwdField.setText(s);
			}
		});
		usernamePane.setAlignment(Pos.CENTER);
		passwdPane.setAlignment(Pos.CENTER);

		passwdField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {

					Boolean existUserBoolean = false;

					try {
						existUserBoolean = LoginBackend.existUser(usernameField.getText(), passwdField.getText());
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					if (existUserBoolean) {
						if (LoginBackend.checkLoginData(usernameField, passwdField)) {
							try {
								LoginBackend.getMenuUser(stage, usernameField.getText(), passwdField.getText());
							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}
					} else {
						alert.setContentText("El usuario no existe. Verifique los datos.");
						alert.showAndWait();
					}
				}
			}
		});
		passwdPane.getChildren().addAll(passwdLabel, passwdField);
		rootPane.getChildren().addAll(usernamePane, passwdPane);

		Button submitButton = new Button("Aceptar");
		submitButton.setId("submitButton");

		submitButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {

					Boolean existUserBoolean = false;

					try {
						existUserBoolean = LoginBackend.existUser(usernameField.getText(), passwdField.getText());
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					if (existUserBoolean) {
						if (LoginBackend.checkLoginData(usernameField, passwdField)) {
							try {
								LoginBackend.getMenuUser(stage, usernameField.getText(), passwdField.getText());
							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}
					} else {
						alert.setContentText("El usuario no existe. Verifique los datos.");
						alert.showAndWait();
					}
				}
			}
		});
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Boolean existUserBoolean = false;

				try {
					existUserBoolean = LoginBackend.existUser(usernameField.getText(), passwdField.getText());
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				if (existUserBoolean) {
					if (LoginBackend.checkLoginData(usernameField, passwdField)) {
						try {
							LoginBackend.getMenuUser(stage, usernameField.getText(), passwdField.getText());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.error(e.getMessage());
						}
					}
				} else {
					alert.setContentText("El usuario no existe. Verifique los datos.");
					alert.showAndWait();
				}
			}
		});
		rootPane.getChildren().add(submitButton);

		stage.setScene(scene);
		stage.setTitle("Control de paquetería - Login");
		stage.setResizable(false);
		stage.show();
	}
}
