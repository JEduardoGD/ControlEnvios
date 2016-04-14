package mx.trilas.ControlEnvio.front;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillasControlEnvio.persistence.pojos.Reporte;

public class Admin {

	private final ObservableList<Reporte> data = FXCollections.observableArrayList(
			new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()),
			new Reporte(new Integer(1),"Volaris", "Acapulco", "Sofia Montes", "Sistemas", "", new Date()),
			new Reporte(new Integer(2),"Fedex", "Zacatecas", "Mario Gutierrez", "Abogacia", "", new Date()),
			new Reporte(new Integer(3),"ODM", "Durango", "Eduardo Ayala", "Pagos", "", new Date()));

	public void AdminMenuStage(Stage stage) {

		try {
			VBox rootVBox = new VBox();
			Scene scene = new Scene(rootVBox, 570, 570);
			rootVBox.setAlignment(Pos.CENTER);

			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/panelAdmin.css").toExternalForm());

			Label label = new Label();
			label.setText("Elija una opción");
			rootVBox.getChildren().add(label);

			Button mensajeriaButon = new Button("Alta o modificar empresa mensajera");
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

	public void mensajeriaStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);
			
			FlowPane nombrePane = new FlowPane(18,15);
			nombrePane.setAlignment(Pos.CENTER);

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 480, 450);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/mensajeria.css").toExternalForm());

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
			
			Button modifyButton = new Button("Modificar empresas");
			modifyButton.getStyleClass().add("modificarRegistroButton");
			
			modifyButton.setAlignment(Pos.TOP_RIGHT);
			modifyButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			
			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);
			
			Text text = new Text("Ingrese la nueva empresa de mensajeria");
			rootVbox.getChildren().addAll(text);
			
			Label nombreLabel = new Label("Mensajería:");
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
			
			rootVbox.setAlignment(Pos.CENTER);
			rootVbox.getChildren().addAll(flowButtonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Alta y modificacion de mensajeria");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void origenesStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);
			
			FlowPane nombrePane = new FlowPane(18,15);
			nombrePane.setAlignment(Pos.CENTER);
			
			FlowPane abrevPane = new FlowPane();
			abrevPane.setAlignment(Pos.CENTER);
			
			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 450, 450);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/origenes.css").toExternalForm());

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
			
			Button modifyButton = new Button("Modificar origenes");
			modifyButton.getStyleClass().add("modificarRegistroButton");
			modifyButton.setAlignment(Pos.TOP_RIGHT);
			modifyButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			
			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);
			
			Text text = new Text("Ingrese el nuevo origen");
			rootVbox.getChildren().addAll(text);
			
			Label nombreLabel = new Label("Origen ");
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
			stage.setTitle("Control de paquetería - Alta y modificacion de origenes");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destinatariosStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);
			
			FlowPane nombrePane = new FlowPane(18,15);
			nombrePane.setAlignment(Pos.CENTER);
			
			FlowPane destinoPane = new FlowPane(18,15);
			destinoPane.setAlignment(Pos.CENTER);
			
			FlowPane abrevPane = new FlowPane();
			abrevPane.setAlignment(Pos.CENTER);
			
			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 480, 450);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/destinatarios.css").toExternalForm());

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
			
			Button modifyButton = new Button("Modificar destinatarios");
			modifyButton.getStyleClass().add("modificarRegistroButton");
			modifyButton.setAlignment(Pos.TOP_RIGHT);
			modifyButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			
			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);
			
			Text text = new Text("Ingrese los datos del nuevo destinatario");
			rootVbox.getChildren().addAll(text);
			
			Label nombreLabel = new Label("Destinatario:");
			TextField nombreField = new TextField();
			
			nombrePane.getChildren().addAll(nombreLabel, nombreField);
			rootVbox.getChildren().addAll(nombrePane);

			Label destinatarioLabel = new Label("Departamento:");
			TextField destinatarioField = new TextField();
			
			destinoPane.getChildren().addAll(destinatarioLabel, destinatarioField);
			rootVbox.getChildren().addAll(destinoPane);
			
			
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
			stage.setTitle("Control de paquetería - Alta y modificacion de destinatarios");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void modificarMensajeriaStage(Stage stage) {

		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();
			
			Scene scene = new Scene(paneVbox, 360, 430);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

			TableView<Reporte> table = new TableView<Reporte>();
			table.setEditable(true);

			TableColumn<Reporte, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(170);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			
			TableColumn<Reporte, String> mensajeraCol = new TableColumn<>("Nombre");
			mensajeraCol.setMinWidth(185);
			mensajeraCol.setCellValueFactory(new PropertyValueFactory<>("mensajeria"));

			mensajeraCol.setCellFactory(TextFieldTableCell.<Reporte> forTableColumn());
			mensajeraCol.setOnEditCommit((CellEditEvent<Reporte, String> t) -> {
				((Reporte) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setMensajeria(t.getNewValue());
			});
			table.setItems(data);
			table.getColumns().addAll(idCol,mensajeraCol);

			paneVbox.setSpacing(10);
			paneVbox.setPadding(new Insets(1));
			paneVbox.getChildren().addAll(table);
			
			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			Button cancelButton = new Button("Cancelar");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(aceptarButton, cancelButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar empresa de mensajeria");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modificarOrigenesStage(Stage stage) {

		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();
			
			Scene scene = new Scene(paneVbox, 290, 440);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

			TableView<Reporte> table = new TableView<Reporte>();
			table.setEditable(true);

			TableColumn<Reporte, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(95);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			
			TableColumn<Reporte, String> origenCol = new TableColumn<>("Origen");
			origenCol.setMinWidth(190);
			origenCol.setCellValueFactory(new PropertyValueFactory<>("origen"));

			origenCol.setCellFactory(TextFieldTableCell.<Reporte> forTableColumn());
			origenCol.setOnEditCommit((CellEditEvent<Reporte, String> t) -> {
				((Reporte) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOrigen(t.getNewValue());
			});

			table.setItems(data);
			table.getColumns().addAll(idCol, origenCol);

			paneVbox.setSpacing(10);
			paneVbox.setPadding(new Insets(1));
			paneVbox.getChildren().addAll(table);
			
			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			Button cancelButton = new Button("Cancelar");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll( aceptarButton, cancelButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar origenes");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void modificarDestinatariosStage(Stage stage) {

		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();
			
//			 Scene scene = new Scene(new Group(), 900, 500);
			Scene scene = new Scene(paneVbox, 430, 450);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

			TableView<Reporte> table = new TableView<Reporte>();
			table.setEditable(true);

			TableColumn<Reporte, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(80);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			
			TableColumn<Reporte, String> destinatarioCol = new TableColumn<>("Destinatario");
			destinatarioCol.setMinWidth(190);
			destinatarioCol.setCellValueFactory(new PropertyValueFactory<>("destinatario"));

			destinatarioCol.setCellFactory(TextFieldTableCell.<Reporte> forTableColumn());
			destinatarioCol.setOnEditCommit((CellEditEvent<Reporte, String> t) -> {
				((Reporte) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setDestinatario(t.getNewValue());
			});

			TableColumn<Reporte, String> deptoCol = new TableColumn<>("Departamento");
			deptoCol.setMinWidth(140);
			deptoCol.setCellValueFactory(new PropertyValueFactory<>("departamento"));
			

			deptoCol.setCellFactory(TextFieldTableCell.<Reporte> forTableColumn());
			deptoCol.setOnEditCommit((CellEditEvent<Reporte, String> t) -> {
				((Reporte) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setDepartamento(t.getNewValue());
			});
			
			table.setItems(data);
			table.getColumns().addAll(idCol,destinatarioCol, deptoCol);

			paneVbox.setSpacing(10);
			paneVbox.setPadding(new Insets(1));
			paneVbox.getChildren().addAll(table);
			
			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			Button cancelButton = new Button("Cancelar");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(aceptarButton, cancelButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar destinatarios");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
