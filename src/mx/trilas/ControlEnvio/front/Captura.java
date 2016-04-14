package mx.trilas.ControlEnvio.front;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillasControlEnvio.persistence.pojos.Reporte;

public class Captura {

	/* Solo datos de ejemplo*/

	private final ObservableList<Reporte> data = FXCollections.observableArrayList(
			new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()),
			new Reporte(new Integer(1),"Volaris", "Acapulco", "Sofia Montes", "Sistemas", "", new Date()),
			new Reporte(new Integer(2),"Fedex", "Zacatecas", "Mario Gutierrez", "Abogacia", "", new Date()),
			new Reporte(new Integer(3),"ODM", "Durango", "Eduardo Ayala", "Pagos", "", new Date()));

	
	public void CapturaStage(Stage stage) {
		try {
			VBox rootVBox = new VBox();
			VBox vbox = new VBox();

			FlowPane mensajeraPane = new FlowPane();
			FlowPane headerPane = new FlowPane();
			FlowPane guiaPane = new FlowPane();
			FlowPane destinatarioPane = new FlowPane();
			FlowPane deptosPane = new FlowPane();
			FlowPane origenPane = new FlowPane();

			Scene scene = new Scene(rootVBox, 470, 500);
			mensajeraPane.setAlignment(Pos.CENTER);
			guiaPane.setAlignment(Pos.CENTER);
			destinatarioPane.setAlignment(Pos.CENTER);
			origenPane.setAlignment(Pos.CENTER);

			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/captura.css").toExternalForm());

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			headerPane.setAlignment(Pos.TOP_LEFT);
			headerPane.getChildren().add(backButton);
			rootVBox.getChildren().add(headerPane);

			Label labelMensajeria = new Label();
			labelMensajeria.setText("Paqueteria     ");

			ComboBox mensajeriaCombo = new ComboBox<>();
			mensajeriaCombo.getItems().addAll("ODM", "DHL", "Estafeta", "Personalmente");
			mensajeriaCombo.setPromptText("Seleccione una opción...");
			
			mensajeraPane.getChildren().addAll(labelMensajeria, mensajeriaCombo);
			rootVBox.getChildren().addAll(mensajeraPane);

			Label guiaLabel = new Label();
			guiaLabel.setText("Numero guia    ");

			TextField guiaField = new TextField();
			guiaPane.getChildren().addAll(guiaLabel, guiaField);
			rootVBox.getChildren().addAll(guiaPane);
			
			Label origenMensajeria = new Label();
			origenMensajeria.setText("Origen            ");

			ComboBox origenCombo = new ComboBox<>();
			origenCombo.getItems().addAll("Chihuahua", "Durango", "DF", "Sonora", "Chiapas", "Zacatecas", "Otro");
			origenCombo.setPromptText("Seleccione un origen...");
			origenPane.getChildren().addAll(origenMensajeria, origenCombo);
			rootVBox.getChildren().addAll(origenPane);

			Label destinatarioLabel = new Label();
			destinatarioLabel.setText("Destinatario    ");

			ComboBox destinatarioCombo = new ComboBox<>();
			destinatarioCombo.getItems().addAll("Mario Benites", "Rufo Vazquez", "Mafia Costa", "Sofia Fierro", "Otro");
			destinatarioCombo.setPromptText("Seleccione una opcion...");

			destinatarioPane.getChildren().addAll(destinatarioLabel, destinatarioCombo);
			rootVBox.getChildren().addAll(destinatarioPane);

			Label deptoLabel = new Label();
			deptoLabel.setText("Departamento ");

			ComboBox deptoCombo = new ComboBox<>();
			deptoCombo.getItems().addAll("Contaduria", "Abogacia", "Sistemas", "Jefaturas");
			deptoCombo.setPromptText("Seleccione una opcion...");
			deptosPane.setAlignment(Pos.CENTER);
			deptosPane.getChildren().addAll(deptoLabel, deptoCombo);
			rootVBox.getChildren().addAll(deptosPane);

			
			Button submitButton = new Button("Aceptar");
			submitButton.setAlignment(Pos.BOTTOM_CENTER);
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			vbox.getChildren().addAll(submitButton);
			vbox.setAlignment(Pos.BOTTOM_CENTER);
			rootVBox.getChildren().addAll(vbox);
			
			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Capturar registro");
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ConfirmarStage(Stage stage, Reporte reporte) {
		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Text text = null;
			if ( reporte.getObservacion() == null || reporte.getObservacion().equals("")){
				text = new Text("Desea guardar los cambios?\n" + "\nMensajeria: " + reporte.getMensajeria()
				+ "\nOrigen: " + reporte.getOrigen() + "\nDestinatario: " + reporte.getDestinatario() + "\nDepartamento: " + reporte.getDepartamento());
			
			} else {
				text = new Text("Desea guardar los cambios?\n" + "\nMensajeria: " + reporte.getMensajeria()
				+ "\nOrigen: " + reporte.getOrigen() + "\nDestinatario: " + reporte.getDestinatario() + "\nObservacion: "+ reporte.getObservacion()+ "\nDepartamento: " + reporte.getDepartamento());
			
			}
			
			
			Scene scene = new Scene(rootVbox, 450, 270);
			rootVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());

			rootVbox.getChildren().addAll(text);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());
			aceptarButton.setEffect(shadow);
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			Button cancelarButton = new Button("Cancelar");
			cancelarButton.setEffect(shadow);
			cancelarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			Button returnButton = new Button("");
			returnButton.getStyleClass().add("backButton");
			returnButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			flowPane.setAlignment(Pos.CENTER);

			flowPane.getChildren().addAll(aceptarButton, cancelarButton);
			rootVbox.getChildren().addAll(flowPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Alta y modificacion de mensajeria");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
