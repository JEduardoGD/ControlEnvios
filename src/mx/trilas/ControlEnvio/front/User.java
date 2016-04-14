package mx.trilas.ControlEnvio.front;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillasControlEnvio.persistence.pojos.Reporte;

public class User {
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
			labelMensajeria.setText("Paqueteria ");

			ComboBox mensajeriaCombo = new ComboBox<>();
			mensajeriaCombo.getItems().addAll("ODM", "DHL", "Estafeta", "Personalmente");
			mensajeriaCombo.setPromptText("Seleccione una paqueteria...");
			
			mensajeraPane.getChildren().addAll(labelMensajeria, mensajeriaCombo);
			rootVBox.getChildren().addAll(mensajeraPane);

			Label guiaLabel = new Label();
			guiaLabel.setText("Numero de guia ");

			TextField guiaField = new TextField();
			guiaPane.getChildren().addAll(guiaLabel, guiaField);
			rootVBox.getChildren().addAll(guiaPane);
			
			Label origenMensajeria = new Label();
			origenMensajeria.setText("Origen ");

			ComboBox origenCombo = new ComboBox<>();
			origenCombo.getItems().addAll("Chihuahua", "Durango", "DF", "Sonora", "Chiapas", "Zacatecas", "Otro");
			origenCombo.setPromptText("Seleccione un origen...");
			origenPane.getChildren().addAll(origenMensajeria, origenCombo);
			rootVBox.getChildren().addAll(origenPane);

			Label destinatarioLabel = new Label();
			destinatarioLabel.setText("Destinatario ");

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
	
	
	public void ConfirmarMensajeriaStage(Stage stage, Reporte reporte) {
		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Text text = new Text("Desea guardar los cambios?\n" + "\nMensajeria: " + reporte.getMensajeria());

			Scene scene = new Scene(rootVbox, 450, 270);
			rootVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());

			rootVbox.getChildren().addAll(text);

			Button aceptarButton = new Button("Aceptar");
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
	
	
	public void GenerarReporteStage(Stage stage) {

		try {
			VBox pane = new VBox();
//			Scene scene = new Scene(pane, 800, 460);
			Scene scene = new Scene(pane, 780, 285);
			HBox headerPane = new HBox ();
			FlowPane labelsPane = new FlowPane(20, 40);
			FlowPane datePane = new FlowPane(20, 40);
			FlowPane buttonsPane = new FlowPane(20, 40);

			pane.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/generarReporte.css").toExternalForm());
			
			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			headerPane.getChildren().add(backButton);
			headerPane.setAlignment(Pos.TOP_LEFT);
			pane.getChildren().add(headerPane);

			Label text = new Label("Elija el rango de fechas para generación del reporte");
			pane.getChildren().addAll(text);

			Label textInicio = new Label("Fecha Inicio");
			textInicio.setId("textInicio");

			Label textFin = new Label("Fecha fin");
			textFin.setId("textFin");

			labelsPane.setAlignment(Pos.CENTER);
			labelsPane.setPadding(new Insets(8));
			labelsPane.getChildren().addAll(textInicio, textFin);
			pane.getChildren().addAll(labelsPane);

			DatePicker datePickerInicio = new DatePicker();
			datePickerInicio.setOnAction(event -> {
				LocalDate date = datePickerInicio.getValue();
//				System.out.println("Selected date: " + date);
			});
			pane.getChildren().addAll(datePickerInicio);

			Label toCalendar = new Label("a");
			pane.getChildren().add(toCalendar);

			DatePicker datePickerFin = new DatePicker();

			datePickerFin.setOnAction(event -> {
				LocalDate date = datePickerFin.getValue();
				System.out.println("Selected date: " + date);
			});
			pane.getChildren().addAll(datePickerFin);

			datePane.getChildren().addAll(datePickerInicio, toCalendar, datePickerFin);
			datePane.setAlignment(Pos.CENTER);

			Button generarButton = new Button("Generar reporte");
			generarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			Button downloadButton = new Button("Descargar");
			downloadButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			buttonsPane.setAlignment(Pos.BOTTOM_CENTER);
			buttonsPane.setPadding(new Insets(20)); 
			buttonsPane.getChildren().addAll(generarButton);
			
			pane.getChildren().addAll(datePane, buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Generar reporte");
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void ConfirmarOrigenesStage(Stage stage, Reporte reporte) {
		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Text text = new Text("Desea guardar los cambios?\n" 
					+ "\nOrigen: " + reporte.getOrigen());

			Scene scene = new Scene(rootVbox, 450, 270);
			rootVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());

			rootVbox.getChildren().addAll(text);

			Button aceptarButton = new Button("Aceptar");
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
			stage.setTitle("Control de paquetería - Alta y modificacion de origenes");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ConfirmarDestinatariosStage(Stage stage, Reporte reporte) {
		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Text text = new Text("Desea guardar los cambios?\n" + "\nDestinatario: " + reporte.getDestinatario() + "\nDepartamento: " + reporte.getDepartamento());

			Scene scene = new Scene(rootVbox, 450, 270);
			rootVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());

			rootVbox.getChildren().addAll(text);

			Button aceptarButton = new Button("Aceptar");
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
			stage.setTitle("Control de paquetería - Alta y modificacion de destinatarios");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void otroOrigenStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);
			
			FlowPane nombrePane = new FlowPane(18,15);
			nombrePane.setAlignment(Pos.CENTER);

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 450, 450);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/otros.css").toExternalForm());

			((Group) scene.getRoot()).getChildren().addAll(rootVbox);

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			backButton.setAlignment(Pos.TOP_LEFT);
			
			headerPane.getChildren().addAll(backButton);
			rootVbox.getChildren().addAll(headerPane);
			
			Text text = new Text("Ingrese el origen");
			rootVbox.getChildren().addAll(text);
			
			Label nombreLabel = new Label("Origen:");
			TextField nombreField = new TextField();
			
			nombrePane.getChildren().addAll(nombreLabel, nombreField);
			rootVbox.getChildren().addAll(nombrePane);
			
			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			Button cancelarButton = new Button("Cancelar");
			cancelarButton.setAlignment(Pos.CENTER);
			cancelarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			flowButtonsPane.setAlignment(Pos.CENTER);
			flowButtonsPane.getChildren().addAll(aceptarButton, cancelarButton);
			
			rootVbox.getChildren().addAll(flowButtonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Otro origen");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void otroDestinatarioStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);
			
			FlowPane nombrePane = new FlowPane(18,15);
			nombrePane.setAlignment(Pos.CENTER);

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 450, 450);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/otros.css").toExternalForm());

			((Group) scene.getRoot()).getChildren().addAll(rootVbox);

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			backButton.setAlignment(Pos.TOP_LEFT);
			
			headerPane.getChildren().addAll(backButton);
			rootVbox.getChildren().addAll(headerPane);
			
			Text text = new Text("Escriba el nombre del destinatario");
			rootVbox.getChildren().addAll(text);
			
			Label nombreLabel = new Label("Destinatario:");
			TextField nombreField = new TextField();
			
			nombrePane.getChildren().addAll(nombreLabel, nombreField);
			rootVbox.getChildren().addAll(nombrePane);
			
			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});

			Button cancelarButton = new Button("Cancelar");
			cancelarButton.setAlignment(Pos.CENTER);
			cancelarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			flowButtonsPane.setAlignment(Pos.CENTER);
			flowButtonsPane.getChildren().addAll(aceptarButton, cancelarButton);
			
			rootVbox.getChildren().addAll(flowButtonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Otro destinatario");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
