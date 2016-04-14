package mx.trilas.ControlEnvio.front;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {


	public void UserMenuStage(Stage stage) {

		try {
			VBox rootVBox = new VBox();
			Scene scene = new Scene(rootVBox, 570, 570);
			
			rootVBox.setAlignment(Pos.CENTER);
			
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/userMenu.css").toExternalForm());

			Label label = new Label();
			label.setText("Elija una opción");
			rootVBox.getChildren().add(label);

			Button capturaButton = new Button("Capturar registro");
			capturaButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			rootVBox.getChildren().add(capturaButton);

			Button generarReporteButton = new Button("Generar reporte");
			generarReporteButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			rootVBox.getChildren().add(generarReporteButton);
			
			Button logOutButton = new Button("Log Out");
			logOutButton.setId("logOutButton");
			logOutButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			logOutButton.setAlignment(Pos.BOTTOM_RIGHT);
			rootVBox.getChildren().addAll(logOutButton);
			
			stage.setScene(scene)	;
			stage.setTitle("Control de paquetería - Menu de usuarios");
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void AdminMenuStage(Stage stage) {

		try {
			VBox rootVBox = new VBox();
			Scene scene = new Scene(rootVBox, 570, 570);
			rootVBox.setAlignment(Pos.CENTER);

			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/panelAdmin.css").toExternalForm());

			Label label = new Label();
			label.setText("Elija una opción");
			rootVBox.getChildren().add(label);

			Button mensajeriaButon = new Button("Alta o modificar empresa de mensajería");
			mensajeriaButon.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			rootVBox.getChildren().add(mensajeriaButon);

			Button origenesBUtton = new Button("Alta o modificar lugares origenes");
			origenesBUtton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			rootVBox.getChildren().add(origenesBUtton);

			Button personasButton = new Button("Alta o modificar personas destinatarias");
			origenesBUtton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			rootVBox.getChildren().add(personasButton);

			Button logOutButton = new Button("Log Out");
			logOutButton.setId("logOutButton");
			logOutButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			logOutButton.setAlignment(Pos.BOTTOM_RIGHT);
			rootVBox.getChildren().addAll(logOutButton);
			
			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Menu de administración");
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
