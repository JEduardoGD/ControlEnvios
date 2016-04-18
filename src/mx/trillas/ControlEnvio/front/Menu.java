package mx.trillas.ControlEnvio.front;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {

	public void UserMenuStage(Stage stage) {

		try {
			VBox rootVBox = new VBox();
			Scene scene = new Scene(rootVBox, 550, 550);
			
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
					Captura captura = new Captura();
					captura.CapturaStage(stage);
				}
			});
			rootVBox.getChildren().add(capturaButton);

			Button generarReporteButton = new Button("Generar reporte");
			generarReporteButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Report reporte = new Report();
					reporte.GenerarReporteStage(stage);
				}
			});
			rootVBox.getChildren().add(generarReporteButton);
			
			Button logOutButton = new Button("Log Out");
			logOutButton.setId("logOutButton");
			logOutButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Login login = new Login();
					login.LoginStage(stage);
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
			Scene scene = new Scene(rootVBox, 550, 550);
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
					Mensajeria mensajeria = new Mensajeria();
					mensajeria.mensajeriaStage(stage);
				}
			});
			rootVBox.getChildren().add(mensajeriaButon);

			Button origenesButton = new Button("Alta o modificar lugares origenes");
			origenesButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Origenes origenes =  new Origenes();
					origenes.origenesStage(stage);
				}
			});
			rootVBox.getChildren().add(origenesButton);

			Button personasButton = new Button("Alta o modificar personas destinatarias");
			personasButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Destinatarios destinatarios = new Destinatarios();
					destinatarios.destinatariosStage(stage);
				}
			});
			rootVBox.getChildren().add(personasButton);

			Button deptosButton = new Button("Alta o modificar departamentos");
			deptosButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Departamento depto = new Departamento();
					depto.departamentoStage(stage);
				}
			});
			rootVBox.getChildren().add(deptosButton);
			
			Button logOutButton = new Button("Log Out");
			logOutButton.setId("logOutButton");
			logOutButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Login login = new Login();
					login.LoginStage(stage);
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
