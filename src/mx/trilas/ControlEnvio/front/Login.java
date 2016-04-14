package mx.trilas.ControlEnvio.front;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trilas.ControlEnvio.front.Admin;
import mx.trilas.ControlEnvio.front.Login;
import mx.trilas.ControlEnvio.front.User;
public class Login {
	
	public void LoginStage(Stage login) {
		Admin admin = new Admin();
		User user =  new User();
		
		try {
			VBox rootPane = new VBox();
			Scene scene = new Scene(rootPane, 400, 400);
			BorderPane footerPane = new BorderPane(); 
			rootPane.setAlignment(Pos.CENTER);

			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/login.css").toExternalForm());

			Text text = new Text("Control de paquetería");
			text.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 25));
			
			Text logintext = new Text("Login");
			logintext.setFont(Font.font("Sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 18));
			// logintext.setFill("#333");
			rootPane.getChildren().addAll(text, logintext);
			
			Text inputField = new Text("Usuario: fulano de tal");
			inputField.setId("inputField");
			rootPane.getChildren().addAll(inputField);

			Label passwdLabel = new Label("Ingrese su contraseña");

			PasswordField passwdField = new PasswordField();
			passwdField.setId("password");
			rootPane.getChildren().addAll(passwdLabel, passwdField);

			Button submitButton = new Button("Aceptar");
			submitButton.getStylesheets().add(getClass().getClassLoader().getResource("style/login.css").toExternalForm());
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			footerPane.setId("footerPane");
			rootPane.getChildren().add(submitButton);
			rootPane.getChildren().add(footerPane);

			login.setScene(scene);
			login.setTitle("Control de paquetería - Login");
			login.setResizable(false);
			login.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
