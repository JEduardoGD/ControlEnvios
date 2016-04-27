package mx.trillas.ControlEnvio.front;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillas.ControlEnvio.backend.MensajeriaBackend;
import mx.trillas.ControlEnvio.persistence.dao.MensajeriaDAO;
import mx.trillas.ControlEnvio.persistence.impl.MensajeriaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

public class MensajeriaWindow {

	private static Logger logger = Logger.getLogger(MensajeriaWindow.class);
	private static MensajeriaDAO mensajeriaDAO = new MensajeriaDAODBImpl();

	/* Solo datos de ejemplo */

	private final ObservableList<Mensajeria> data = FXCollections.observableArrayList();

	public ObservableList<Mensajeria> getMensajeriaData() throws Exception {

		List<Mensajeria> mensajerias = new ArrayList<Mensajeria>();

		try {
			mensajerias = mensajeriaDAO.getMensajeriaList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		for (Mensajeria element : mensajerias) {
			data.add(element);
		}
		
		return data;
	}

	public void mensajeriaStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);

			FlowPane nombrePane = new FlowPane(18, 15);
			nombrePane.setAlignment(Pos.CENTER);

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 480, 450);
			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/mensajeria.css").toExternalForm());

			((Group) scene.getRoot()).getChildren().addAll(rootVbox);

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuWindow menu = new MenuWindow();
					menu.AdminMenuStage(stage);
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
					MensajeriaWindow mensajeria = new MensajeriaWindow();
					mensajeria.modificarMensajeriaStage(stage);
				}
			});

			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);

			Text text = new Text("Ingrese la nueva empresa de mensajeria");
			rootVbox.getChildren().addAll(text);

			Label nombreLabel = new Label("Mensajería:");
			TextField mensajeriaField = new TextField();
			nombrePane.getChildren().addAll(nombreLabel, mensajeriaField);
			rootVbox.getChildren().addAll(nombrePane);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Mensajeria mensajeriaObj = null;

					try {
						mensajeriaObj = mensajeriaDAO.getMensajeria(mensajeriaField.getText());
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					if (mensajeriaField.getText() == null || mensajeriaField.getText().equals("")) {
						logger.error("El nombre de empresa de mensajeria no debe ir vacio");
					} else if (!(MensajeriaBackend.checkString(mensajeriaField.getText()))) {
						logger.error("El nombre de empresa de mensajeria no contiene la estructura requerida");
					} else if (mensajeriaObj != null) {
						logger.info("La empresa de mensajeria que intenta crear ya existe.");
					} else {
						logger.info("Intento guardar la empresa de mensajeria");
						confirmarMensajeriaStage(stage, mensajeriaField.getText());
					}
				}
			});

			Button cancelarButton = new Button("Cancelar");
			cancelarButton.setAlignment(Pos.CENTER);
			cancelarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuWindow menu = new MenuWindow();
					menu.AdminMenuStage(stage);
				}
			});

			flowButtonsPane.setAlignment(Pos.CENTER);
			flowButtonsPane.getChildren().addAll(aceptarButton, cancelarButton);

			rootVbox.setAlignment(Pos.CENTER);
			rootVbox.getChildren().addAll(flowButtonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Alta y modificacion de mensajeria");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modificarMensajeriaStage(Stage stage) {

		ObservableList<Mensajeria> datos = null;
		
		try {
			datos = getMensajeriaData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		
		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();

			Scene scene = new Scene(paneVbox, 360, 430);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

			TableView<Mensajeria> table = new TableView<Mensajeria>();
			table.setEditable(true);

			TableColumn<Mensajeria, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(170);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Mensajeria, String> mensajeraCol = new TableColumn<>("Nombre");
			mensajeraCol.setMinWidth(185);
			mensajeraCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

			mensajeraCol.setCellFactory(TextFieldTableCell.<Mensajeria> forTableColumn());
			mensajeraCol.setOnEditCommit((CellEditEvent<Mensajeria, String> t) -> {
				((Mensajeria) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNombre(t.getNewValue());
			});
			table.setItems(datos);
			table.getColumns().addAll(idCol, mensajeraCol);

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
					MensajeriaWindow mensajeria = new MensajeriaWindow();
					mensajeria.mensajeriaStage(stage);
				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(aceptarButton, cancelButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar empresa de mensajeria");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void confirmarMensajeriaStage(Stage stage, String nombreMensajeria) {
		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Text text = new Text("Desea guardar los cambios?\n" + "\nMensajeria: " + nombreMensajeria);

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
					try {
						MensajeriaBackend.loadMensajeriaData(nombreMensajeria);

						MenuWindow menu = new MenuWindow();
						menu.AdminMenuStage(stage);
						// Ir a ventana de confirmar

					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage());
					}
				}
			});

			Button cancelarButton = new Button("Cancelar");
			cancelarButton.setEffect(shadow);
			cancelarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuWindow menu = new MenuWindow();
					menu.AdminMenuStage(stage);
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
			stage.setTitle("Confirmar mensajeria");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}