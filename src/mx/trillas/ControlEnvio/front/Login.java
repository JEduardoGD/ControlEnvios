package mx.trillas.ControlEnvio.front;

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

public class Login {
	
	public void LoginStage(Stage login) {
		Menu menuEntry = new Menu();
		
		try {
			VBox rootPane = new VBox();
			Scene scene = new Scene(rootPane, 400, 400);
			FlowPane usernamePane = new FlowPane();
			FlowPane passwdPane = new FlowPane();
			
			rootPane.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/login.css").toExternalForm());

			Text introText = new Text("Control de paquetería");
			introText.setId("introText");
			
			Text loginText = new Text("Login");
			loginText.setId("loginText");
			
			rootPane.getChildren().addAll(introText, loginText);
			
			Label usernameLabel= new Label("Username   ");
			usernameLabel.getStyleClass().add("labels");
			TextField usernameField = new TextField();
			
			usernamePane.getChildren().addAll(usernameLabel, usernameField);
			
			Label passwdLabel = new Label("Contraseña ");
			passwdLabel.getStyleClass().add("labels");
			PasswordField passwdField = new PasswordField();
			
			usernamePane.setAlignment(Pos.BASELINE_CENTER);
			passwdPane.setAlignment(Pos.BASELINE_CENTER);
			passwdPane.getChildren().addAll(passwdLabel, passwdField);
			
			rootPane.getChildren().addAll(usernamePane, passwdPane);

			Button submitButton = new Button("Aceptar");
			submitButton.setId("submitButton");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			rootPane.getChildren().add(submitButton);

			login.setScene(scene);
			login.setTitle("Control de paquetería - Login");
			login.setResizable(false);
			login.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
