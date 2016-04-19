package mx.trillas.ControlEnvio.front;

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
import mx.trillas.ControlEnvio.persistence.pojosaux.Controlenvio;

public class CapturaWindow {

	/* Solo datos de ejemplo */
	private final ObservableList<Controlenvio> data = FXCollections.observableArrayList(
			new Controlenvio(new Integer(0), "DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()),
			new Controlenvio(new Integer(1), "Volaris", "Acapulco", "Sofia Montes", "Sistemas", "", new Date()),
			new Controlenvio(new Integer(2), "Fedex", "Zacatecas", "Mario Gutierrez", "Abogacia", "", new Date()),
			new Controlenvio(new Integer(3), "ODM", "Durango", "Eduardo Ayala", "Pagos", "", new Date()));

	public void CapturaStage(Stage stage) {
		try {
			VBox rootVBox = new VBox(5);
			VBox vbox = new VBox(20);

			FlowPane mensajeraPane = new FlowPane(1, 1);
			FlowPane headerPane = new FlowPane();

			FlowPane guiaPane = new FlowPane(17, 10);
			FlowPane destinatarioPane = new FlowPane(45,10);
			FlowPane deptosPane = new FlowPane(25,10);
			FlowPane origenPane = new FlowPane(55, 10);
			FlowPane otroOrigenPane = new FlowPane(45,10);
			FlowPane otroDestinatarioPane = new FlowPane();
			FlowPane otroDeptoPane = new FlowPane(5,10);
			FlowPane observacionPane = new FlowPane(35, 10);
			FlowPane clearPane = new FlowPane();
			FlowPane buttonsPane = new FlowPane();

			Scene scene = new Scene(rootVBox, 480, 560);

			mensajeraPane.setAlignment(Pos.CENTER);
			guiaPane.setAlignment(Pos.CENTER);
			destinatarioPane.setAlignment(Pos.CENTER);
			origenPane.setAlignment(Pos.CENTER);
			observacionPane.setAlignment(Pos.CENTER);
			otroOrigenPane.setAlignment(Pos.CENTER);
			otroDestinatarioPane.setAlignment(Pos.CENTER);
			otroDeptoPane.setAlignment(Pos.CENTER);
			clearPane.setAlignment(Pos.BOTTOM_CENTER);
			buttonsPane.setAlignment(Pos.BOTTOM_CENTER);

//			otroOrigenPane.setVisible(false);
//			otroDestinatarioPane.setVisible(false);

			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/captura.css").toExternalForm());

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuWindow menu = new MenuWindow();
					menu.UserMenuStage(stage);
				}
			});
			headerPane.setAlignment(Pos.TOP_LEFT);
			headerPane.getChildren().add(backButton);
			rootVBox.getChildren().add(headerPane);

			Label labelMensajeria = new Label();
			labelMensajeria.setText("Paqueteria     ");

			ComboBox<Object> mensajeriaCombo = new ComboBox<>();
			mensajeriaCombo.getItems().addAll("ODM", "DHL", "Estafeta", "Personalmente");
			mensajeriaCombo.setPromptText("Seleccione una opción...");
			mensajeriaCombo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			mensajeraPane.getChildren().addAll(labelMensajeria, mensajeriaCombo);

			rootVBox.getChildren().addAll(mensajeraPane);

			Label guiaLabel = new Label("Numero guia");

			TextField guiaField = new TextField();
			guiaPane.getChildren().addAll(guiaLabel, guiaField);
			rootVBox.getChildren().addAll(guiaPane);

			TextField otroOrigenField = new TextField();
			TextField otroDeptoField = new TextField();
			TextField otroDestinatarioField = new TextField();

			otroOrigenField.setDisable(true);
			otroDeptoField.setDisable(true);
			otroDestinatarioField.setDisable(true);

			Label origenMensajeria = new Label("Origen ");

			ComboBox<Object> origenCombo = new ComboBox<>();
			origenCombo.getItems().addAll("Chihuahua", "Durango", "DF", "Sonora", "Chiapas", "Zacatecas", "Otro");
			origenCombo.setPromptText("Seleccione un origen...");
			origenPane.getChildren().addAll(origenMensajeria, origenCombo);

			origenCombo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if (origenCombo.getValue().equals("Otro")) {
						otroOrigenField.setDisable(false);
					} else {
						otroOrigenField.setDisable(true);
					}
				}
			});

			rootVBox.getChildren().addAll(origenPane);

			Label otroOrigenLabel = new Label("Otro origen ");

			otroOrigenPane.getChildren().addAll(otroOrigenLabel, otroOrigenField);


			rootVBox.getChildren().addAll(otroOrigenPane);

			Label deptoLabel = new Label("Departamento ");

			ComboBox<Object> deptoCombo = new ComboBox<>();
			deptoCombo.getItems().addAll("Contaduria", "Abogacia", "Sistemas", "Jefaturas", "Otro");
			deptoCombo.setPromptText("Seleccione una opcion...");
			deptosPane.setAlignment(Pos.CENTER);

			deptoCombo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if (deptoCombo.getValue().equals("Otro")) {
					}
					if (deptoCombo.getValue().equals("Otro")) {
						otroDeptoField.setDisable(false);
					} else {
						otroDeptoField.setDisable(true);
					}
				}
			});

			deptosPane.getChildren().addAll(deptoLabel, deptoCombo);
			rootVBox.getChildren().addAll(deptosPane);

			Label destinatarioLabel = new Label("Destinatario ");

			ComboBox<Object> destinatarioCombo = new ComboBox<>();
			destinatarioCombo.getItems().addAll("Mario Benites", "Rufo Vazquez", "Mafia Costa", "Sofia Fierro", "Otro");
			destinatarioCombo.setPromptText("Seleccione una opción...");

			destinatarioCombo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if (destinatarioCombo.getValue().equals("Otro")) {
					}
					if (destinatarioCombo.getValue().equals("Otro")) {
						// System.out.println(destinatarioCombo.getValue().equals("Otro"));
						otroDestinatarioField.setDisable(false);
					} else {
						otroDestinatarioField.setDisable(true);
					}
				}
			});

			destinatarioPane.getChildren().addAll(destinatarioLabel, destinatarioCombo);
			rootVBox.getChildren().addAll(destinatarioPane);

			Label otroDestinatarioLabel = new Label("Otro destinatario ");
			Label otroDeptoLabel = new Label("Otro deparamento ");

			otroDestinatarioPane.getChildren().addAll(otroDestinatarioLabel, otroDestinatarioField);
			rootVBox.getChildren().addAll(otroDestinatarioPane);

			otroDeptoPane.getChildren().addAll(otroDeptoLabel, otroDeptoField);
			rootVBox.getChildren().addAll(otroDeptoPane);

			Label observacionLabel = new Label("Observacion ");

			TextField observacionField = new TextField();
			observacionPane.getChildren().addAll(observacionLabel, observacionField);
			rootVBox.getChildren().addAll(observacionPane);

			Button clearButton = new Button("Limpiar formulario");
			clearButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			clearPane.getChildren().addAll(clearButton);

			Button submitButton = new Button("Aceptar");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			buttonsPane.getChildren().addAll(submitButton);
			rootVBox.getChildren().addAll(clearPane, buttonsPane);

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

	public void ConfirmarStage(Stage stage, Controlenvio controlEnvio) {
		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Text text = null;
			if (controlEnvio.getObservacion() == null || controlEnvio.getObservacion().equals("")) {
				text = new Text("Desea guardar los cambios?\n" + "\nMensajeria: " + controlEnvio.getMensajeria()
						+ "\nOrigen: " + controlEnvio.getOrigen() + "\nDestinatario: " + controlEnvio.getDestinatario()
						+ "\nDepartamento: " + controlEnvio.getDepartamento());

			} else {
				text = new Text("Desea guardar los cambios?\n" + "\nMensajeria: " + controlEnvio.getMensajeria()
						+ "\nOrigen: " + controlEnvio.getOrigen() + "\nDestinatario: " + controlEnvio.getDestinatario()
						+ "\nObservacion: " + controlEnvio.getObservacion() + "\nDepartamento: "
						+ controlEnvio.getDepartamento());

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
