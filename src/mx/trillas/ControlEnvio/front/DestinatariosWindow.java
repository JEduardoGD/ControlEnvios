package mx.trillas.ControlEnvio.front;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import mx.trillas.ControlEnvio.backend.DepartamentoBackend;
import mx.trillas.ControlEnvio.backend.DestinatarioBackend;
import mx.trillas.ControlEnvio.persistence.dao.DepartamentoDAO;
import mx.trillas.ControlEnvio.persistence.dao.DestinatarioDAO;
import mx.trillas.ControlEnvio.persistence.impl.DepartamentoDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.DestinatarioDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;
import mx.trillas.ControlEnvio.persistence.pojos.Destinatario;

public class DestinatariosWindow {

	private static Logger logger = Logger.getLogger(DestinatariosWindow.class);
	private static DestinatarioDAO destinatarioDAO = new DestinatarioDAODBImpl();
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAODBImpl();

	ObservableList<Destinatario> destinatariosList =  FXCollections.observableArrayList();

	public void destinatariosStage(Stage stage) {

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		
		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);

			FlowPane nombrePane = new FlowPane(18, 15);
			nombrePane.setAlignment(Pos.CENTER);

			FlowPane destinoPane = new FlowPane(18, 15);
			destinoPane.setAlignment(Pos.CENTER);

			FlowPane deptoPane = new FlowPane(18, 15);
			deptoPane.setAlignment(Pos.CENTER);

			FlowPane abrevPane = new FlowPane();
			abrevPane.setAlignment(Pos.CENTER);

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 480, 450);
			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/destinatarios.css").toExternalForm());

			((Group) scene.getRoot()).getChildren().addAll(rootVbox);

			Alert alert = new Alert(AlertType.WARNING, "content text");
			alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
			.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
			alert.setTitle("Destinatarios");

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					MenuWindow menu = new MenuWindow();
					menu.AdminMenuStage(stage);
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
					DestinatariosWindow destinatarios = new DestinatariosWindow();
					destinatarios.modificarDestinatariosStage(stage);
				}
			});

			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);

			Text text = new Text("Ingrese los datos del nuevo destinatario");
			text.getStyleClass().add("textRules");
			rootVbox.getChildren().addAll(text);

			Label destinatarioLabel = new Label("Destinatario");
			destinatarioLabel.getStyleClass().add(".inputs");
			TextField destinatarioField = new TextField();
			destinatarioField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (destinatarioField.getText().length() > 44) {
					String s = destinatarioField.getText().substring(0, 44);
					destinatarioField.setText(s);
				}
			});
			nombrePane.getChildren().addAll(destinatarioLabel, destinatarioField);
			rootVbox.getChildren().addAll(nombrePane);

			Label deptoLabel = new Label("Departamento");
			deptoLabel.getStyleClass().add(".inputs");

			ComboBox<Object> deptoCombo = DepartamentoBackend.getDeptosListCombo();
			deptoCombo.setPrefWidth(208);
			deptoCombo.setPromptText("Seleccione una opcion...");
			deptoPane.getChildren().addAll(deptoLabel, deptoCombo);
			rootVbox.getChildren().addAll(deptoPane);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					Destinatario destinatarioObj = null;
					try {
						destinatarioObj = destinatarioDAO.getDestinatarioByName(destinatarioField.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error("Ocurrio un error al intentar buscar un destinatario");
					}

					if (destinatarioField.getText() == null || destinatarioField.getText().equals("")) {
						logger.error("El nombre del destinatario no debe ir vacio");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El campo \"destinatario\" no puede ir vacio");
						alert.showAndWait();
					} else if (deptoCombo.getValue() == null || deptoCombo.getValue().toString() == null
							|| deptoCombo.getValue().toString().equals("")) {
						logger.error("El nombre del departamento no debe ir vacio");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("Seleccione el departamento al que el nuevo destinatario pertenece ");
						alert.showAndWait();
					} else if (!(DestinatarioBackend.checkString(destinatarioField.getText()))) {
						logger.error("El nombre del destinatario no contiene la estructura requerida");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El campo \"destinatario\" no contiene la estructura requerida"
								+ " (Números, letras)");
						alert.showAndWait();
					} else if (destinatarioObj != null) {
						logger.info("El destinatario ya existe en otro departamento");
						alert.setAlertType(AlertType.WARNING);
						alert.setHeaderText(null);
						alert.setContentText("El destinatario ya existe en otro departamento");
						alert.showAndWait();
					} else {
						confirmarDestinatariosStage(new Alert(AlertType.CONFIRMATION), destinatarioField.getText(),
								deptoCombo.getValue().toString());
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
			flowButtonsPane.getChildren().addAll(aceptarButton);

			rootVbox.getChildren().addAll(flowButtonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Alta y modificacion de destinatarios");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void modificarDestinatariosStage(Stage stage) {

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		
		FlowPane returnPane = new FlowPane();
		
		ObservableList<String> deptosListCombo = null;
		ObservableList<Destinatario> destinatariosData = null;
		
		try {
			deptosListCombo = DepartamentoBackend.getDepartamentoCombo();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {
			destinatariosData = DestinatarioBackend.getDestinatarioData();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
//		for (String element : deptosListCombo) {
//			System.out.println("element=" + element);
//		}
		
		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();

			Scene scene = new Scene(paneVbox, 430, 520);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/destinatarios.css").toExternalForm());

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setCursor(Cursor.HAND);
			backButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					DestinatariosWindow window = new DestinatariosWindow();
					window.destinatariosStage(stage);
				}
			});
			returnPane.getChildren().addAll(backButton);
			paneVbox.getChildren().addAll(returnPane);

			Text text = new Text("Edite los registros que necesite modificar, y guarde \nlos cambios presionando el botón guardar.");
			text.getStyleClass().add("textRules");
			paneVbox.getChildren().addAll(text);
			
			TableView<Destinatario> table = new TableView<Destinatario>();
			table.setEditable(true);

			TableColumn<Destinatario, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(80);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Destinatario, String> destinatarioCol = new TableColumn<>("Destinatario");
			TableColumn<Destinatario, String> deptoCol = new TableColumn<>("Departamento");

			/* Trabajo por destinatario*/
			destinatarioCol.setMinWidth(190);
			destinatarioCol.setCellValueFactory(new Callback<CellDataFeatures<Destinatario, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Destinatario, String> param) {
					String destinatario = param.getValue().getNombre().toLowerCase();
					String destinatarioCaptitalize = destinatario.substring(0, 1).toUpperCase() + destinatario.substring(1);
					return new SimpleStringProperty(destinatarioCaptitalize);
				}
			});
			destinatarioCol.setCellFactory(TextFieldTableCell.<Destinatario> forTableColumn());
			destinatarioCol.setOnEditCommit((CellEditEvent<Destinatario, String> t) -> {

				if (!(t.getNewValue().equals("")) && DestinatarioBackend.checkString(t.getNewValue()) == true) {
					((Destinatario) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNombre(t.getNewValue().toString());
					
					Destinatario destinatario = null;

					int idDestinatario = Integer.parseInt(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId().toString());
					
					try {
						destinatario = destinatarioDAO.getDestinatarioById(idDestinatario);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
										
					destinatario.setNombre(t.getNewValue());

					/* Trabajo en la lista */
					int counter = -1;
					int idDestinatarioObj = destinatario.getId();

					if (destinatariosList.isEmpty()) {
						destinatariosList.add(destinatario);
					}

					for (Destinatario element : destinatariosList) {
						counter = counter + 1;
						if (element.getId() == idDestinatarioObj) {

							destinatariosList.remove(counter);
							destinatariosList.add(destinatario);
							break;
						} else {
							destinatariosList.add(destinatario);
							break;
						}
					}
				} else {
					Alert alert = new Alert(AlertType.WARNING, "content text");
					alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
					.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));

					logger.error("El nombre de destinatario no debe ir vacio");
					alert.setHeaderText("Error al ingresar datos");
					alert.setContentText("El dato ingresado no contiene la estructura requerida."
							+ " (Números, letras). Por favor corrigalo");
					alert.showAndWait();

					table.getColumns().get(0).setVisible(false);
					table.getColumns().get(0).setVisible(true);
				}
			});

			/* Trabajo por departamento */
			deptoCol.setMinWidth(140);
			deptoCol.setCellValueFactory(new Callback<CellDataFeatures<Destinatario, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Destinatario, String> param) {
					String departamento = param.getValue().getDepartamento().getNombre().toLowerCase();
					String departamentoCaptitalize = departamento.substring(0, 1).toUpperCase() + departamento.substring(1);
					return new SimpleStringProperty(departamentoCaptitalize);
				}
			});
			deptoCol.setCellFactory(ComboBoxTableCell.forTableColumn(null, deptosListCombo));
			deptoCol.setOnEditCommit((CellEditEvent<Destinatario, String> t) -> {
				((Destinatario) t.getTableView().getItems().get(t.getTablePosition().getRow())).getDepartamento().setNombre(t.getNewValue());
				
				Destinatario destinatario = null;
				Departamento departamento = null;
				
				int idDestinatario = Integer.parseInt(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId().toString());

				try {
					destinatario = destinatarioDAO.getDestinatarioById(idDestinatario);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				
				try {
					departamento = departamentoDAO.getDepartamento(t.getNewValue());
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				destinatario.setDepartamento(departamento);
				
				int counter = -1;
				int idObj = destinatario.getId();

				if (destinatariosList.isEmpty()) {
					destinatariosList.add(destinatario);
				}

				for (Destinatario element : destinatariosList) {
					counter = counter + 1;
					if (element.getId() == idObj) {

						destinatariosList.remove(counter);
						destinatariosList.add(destinatario);
						break;
					} else {
						destinatariosList.add(destinatario);
						break;
					}
				}
			});

			table.setItems(destinatariosData);
			table.getColumns().addAll(idCol, destinatarioCol, deptoCol);

			paneVbox.setSpacing(10);
			paneVbox.setPadding(new Insets(1));
			paneVbox.getChildren().addAll(table);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					if (destinatariosList.isEmpty()) {
						Alert alert = new Alert(AlertType.INFORMATION, "content text");
						alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
						.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
						alert.setTitle("Modificaciones en destinatarios");
						alert.setHeaderText("Alerta");
						alert.setContentText("Aún no ha hecho cambios en registros");
						alert.showAndWait();
					} else {
						Alert confirmation = new Alert(AlertType.CONFIRMATION, "content text");
						confirmation.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
						.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
						
						confirmarModificacionesDestinatarios(confirmation, destinatariosList);
						
						destinatariosList.clear();
					}
				}
			});
			Button cancelButton = new Button("Cancelar");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					DestinatariosWindow destinatarios = new DestinatariosWindow();
					destinatarios.destinatariosStage(stage);
				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(aceptarButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar destinatarios");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void confirmarModificacionesDestinatarios(Alert confirmation, List<Destinatario> destinatarios) {

		Text text = new Text();
		String output = "";

		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		for (Destinatario element : destinatarios) {
			output += "\nNombre: " + element.getNombre() + ", Departamento: " + element.getDepartamento().getNombre();
		}
		text.setText(output);

		confirmation.setContentText(text.getText());

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				destinatarioDAO.updateDestinatarioByList(destinatarios);
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cambios en destinatarios");
			alert.setHeaderText(null);
			alert.setContentText("Los cambios se guardaron exitosamente");
			alert.showAndWait();
		} else {
			// hacer algo
		}
	}

	public void confirmarDestinatariosStage(Alert confirmation, String nombreDestinatario, String nombreDepartamento) {

		Text text = new Text();
		String output = "";

		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		output += "\n\nDestinatario: " + nombreDestinatario	+ "\nDepartamento: " + nombreDepartamento;
		text.setText(output);

		confirmation.setContentText(text.getText());

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (DestinatarioBackend.checkString(nombreDestinatario)) {
				try {
					DestinatarioBackend.loadDestinatarioData(nombreDestinatario, nombreDepartamento);

					Alert alert = new Alert(AlertType.INFORMATION, "content text");
					alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
					.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
					alert.setTitle("Cambios en destinatarios");
					alert.setHeaderText(null);
					alert.setContentText("El destinatario se ha guardado exitosamente");
					alert.showAndWait();
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} else {
			// hacer algo
		}
	}
}