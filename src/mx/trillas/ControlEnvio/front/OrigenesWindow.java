package mx.trillas.ControlEnvio.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillas.ControlEnvio.backend.OrigenBackend;
import mx.trillas.ControlEnvio.persistence.dao.OrigenesDAO;
import mx.trillas.ControlEnvio.persistence.impl.OrigenesDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;
import mx.trillas.ControlEnvio.persistence.pojos.Origen;

public class OrigenesWindow {

	private static OrigenesDAO origenDAO = new OrigenesDAODBImpl();
	private static Logger logger = Logger.getLogger(OrigenesWindow.class);

	public void origenesStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);

			FlowPane nombrePane = new FlowPane(18, 15);
			nombrePane.setAlignment(Pos.CENTER);

			FlowPane abrevPane = new FlowPane();
			abrevPane.setAlignment(Pos.CENTER);

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 461, 450);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/origenes.css").toExternalForm());

			((Group) scene.getRoot()).getChildren().addAll(rootVbox);

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Origenes");

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

			Button modifyButton = new Button("Modificar origenes");
			modifyButton.getStyleClass().add("modificarRegistroButton");
			modifyButton.setAlignment(Pos.TOP_RIGHT);
			modifyButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					OrigenesWindow origenes = new OrigenesWindow();
					origenes.modificarOrigenesStage(stage);
				}
			});

			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);

			Text text = new Text("Ingrese el origen");
			rootVbox.getChildren().addAll(text);

			Label nombreLabel = new Label("Origen ");
			TextField nombreField = new TextField();
			nombreField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (nombreField.getText().length() > 44) {
					String s = nombreField.getText().substring(0, 44);
					nombreField.setText(s);
				}
			});

			nombrePane.getChildren().addAll(nombreLabel, nombreField);
			rootVbox.getChildren().addAll(nombrePane);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Origen origenObj = null;

					try {
						origenObj = origenDAO.getOrigen(nombreField.getText());
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					if (nombreField.getText() == null || nombreField.getText().equals("")) {
						logger.error("El nombre de origen no debe ir vacio");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El nombre del nuevo origen \nno debe ir vacio");
						alert.showAndWait();
					} else if (!(OrigenBackend.checkString(nombreField.getText()))) {
						logger.error("El nombre de origen no contiene la estructura requerida");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El nombre del nuevo origen no \ncontiene la estructura requerida");
						alert.showAndWait();
					} else if (origenObj != null) {
						logger.info("El origen que intenta crear ya existe.");
						alert.setAlertType(AlertType.WARNING);
						alert.setHeaderText(null);
						alert.setContentText("El origen que intenta \ncrear ya existe");
						alert.showAndWait();
					} else {
						logger.info("Intento guardar el nuevo origen");
						confirmarOrigenesStage(new Alert(AlertType.CONFIRMATION), nombreField.getText());
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

			rootVbox.getChildren().addAll(flowButtonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Alta y modificacion de origenes");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modificarOrigenesStage(Stage stage) {

		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();

			Alert alert = new Alert(AlertType.WARNING, "content text");
			alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
					.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));

			List<Origen> origenList = new ArrayList<Origen>();
			ObservableList<Origen> datos = null;

			try {
				datos = OrigenBackend.getOrigenData();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			Scene scene = new Scene(paneVbox, 290, 440);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

			TableView<Origen> table = new TableView<Origen>();
			table.setEditable(true);

			TableColumn<Origen, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(95);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Origen, String> origenCol = new TableColumn<>("Origen");
			origenCol.setMinWidth(190);
			origenCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

			origenCol.setCellFactory(TextFieldTableCell.<Origen> forTableColumn());
			origenCol.setOnEditCommit((CellEditEvent<Origen, String> t) -> {

				if (!(t.getNewValue().equals("")) && OrigenBackend.checkString(t.getNewValue()) == true) {
					((Origen) t.getTableView().getItems().get(t.getTablePosition().getRow()))
							.setNombre(t.getNewValue());
					Origen origen = new Origen();
					origen.setId(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId());
					origen.setNombre(t.getNewValue());

					int counter = -1;
					int idObj = origen.getId();

					if (origenList.isEmpty()) {
						origenList.add(origen);
					}

					for (Origen element : origenList) {
						counter = counter + 1;
						if (element.getId() == idObj) {

							origenList.remove(counter);
							origenList.add(origen);
							break;
						} else {
							origenList.add(origen);
							break;
						}
					}
				} else {
					logger.error("El nombre del origen no debe ir vacio");
					alert.setHeaderText("Error al ingresar datos");
					alert.setContentText("El dato ingresado no contiene la estructura requerida. Por favor corrigalo");
					alert.showAndWait();

					table.getColumns().get(0).setVisible(false);
					table.getColumns().get(0).setVisible(true);
				}
			});

			table.setItems(datos);
			table.getColumns().addAll(idCol, origenCol);

			paneVbox.setSpacing(10);
			paneVbox.setPadding(new Insets(1));
			paneVbox.getChildren().addAll(table);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if (origenList.isEmpty()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Modificaciones en mensajería");
						alert.setHeaderText("Alerta");
						alert.setContentText("Aún no ha hecho cambios en registros");
						alert.showAndWait();
					} else {
						confirmarModificacionesOrigenes(new Alert(AlertType.CONFIRMATION), origenList);
						origenList.clear();
					}
				}
			});
			Button cancelButton = new Button("Cancelar");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					OrigenesWindow origenes = new OrigenesWindow();
					origenes.origenesStage(stage);
				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(aceptarButton, cancelButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar origenes");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void confirmarModificacionesOrigenes(Alert confirmation, List<Origen> origenes) {

		Text text = new Text();
		String output = "";

		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		for (Origen element : origenes) {
			output += "Origen: " + element.getNombre();
		}
		text.setText(output);

		confirmation.setContentText(text.getText());

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				origenDAO.altaOrigenByList(origenes);
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cambios en mensajerias");
			alert.setHeaderText(null);
			alert.setContentText("Los cambios se guardaron exitosamente");
			alert.showAndWait();
		} else {
			// hacer algo
		}
	}

	public void confirmarOrigenesStage(Alert confirmation, String nombreOrigen) {

		Text text = new Text();
		String output = "";

		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		output += "\nOrigen: " + nombreOrigen;
		text.setText(output);

		confirmation.setContentText(text.getText());

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (OrigenBackend.checkString(nombreOrigen)) {
				try {
					OrigenBackend.loadOrigenData(nombreOrigen);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Cambios en origenes");
					alert.setHeaderText(null);
					alert.setContentText("El origen se ha guardado exitosamente");
					alert.showAndWait();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		} else {
			// hacer algo
		}
	}

	public void otroOrigenStage(Stage stage) {

		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);

			FlowPane nombrePane = new FlowPane(18, 15);
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
}
