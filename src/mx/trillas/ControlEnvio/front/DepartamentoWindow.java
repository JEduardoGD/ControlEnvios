package mx.trillas.ControlEnvio.front;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import javafx.application.Platform;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mx.trillas.ControlEnvio.backend.DepartamentoBackend;
import mx.trillas.ControlEnvio.persistence.dao.DepartamentoDAO;
import mx.trillas.ControlEnvio.persistence.impl.DepartamentoDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;

public class DepartamentoWindow {

	private static Logger logger = Logger.getLogger(DepartamentoWindow.class);
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAODBImpl();

	public void departamentoStage(Stage stage) {


		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        Platform.exit();
		    }
		});
		
		try {
			VBox rootVbox = new VBox(25);
			rootVbox.setAlignment(Pos.CENTER);

			FlowPane nombrePane = new FlowPane(18, 15);
			nombrePane.setAlignment(Pos.CENTER);

			FlowPane destinoPane = new FlowPane(18, 15);
			destinoPane.setAlignment(Pos.CENTER);

			FlowPane abrevPane = new FlowPane();
			abrevPane.setAlignment(Pos.CENTER);

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 490, 450);
			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/departamento.css").toExternalForm());

			((Group) scene.getRoot()).getChildren().addAll(rootVbox);

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Departamentos");

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

			Button modifyButton = new Button("Modificar departamentos");
			modifyButton.getStyleClass().add("modificarRegistroButton");
			modifyButton.setAlignment(Pos.TOP_RIGHT);
			modifyButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					DepartamentoWindow depto = new DepartamentoWindow();
					depto.modificarDepartamentoStage(stage);
				}
			});

			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);

			Text text = new Text("Ingrese el departamento");
			rootVbox.getChildren().addAll(text);

			Label nombreLabel = new Label("Departamento:");
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

					Departamento departamentoObj = null;

					try {
						departamentoObj = departamentoDAO.getDepartamento(nombreField.getText());
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					if (nombreField.getText() == null || nombreField.getText().equals("")) {
						logger.error("El nombre del departamento no debe ir vacio");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El nombre del departamento \nno debe ir vacio");
						alert.showAndWait();
					} else if (!(DepartamentoBackend.checkString(nombreField.getText()))) {
						logger.error("El nombre del departamento no contiene la estructura requerida");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El nombre del departamento no \ncontiene la estructura requerida");
						alert.showAndWait();
					} else if (departamentoObj != null) {
						logger.error("El departamento que intenta crear ya existe.");
						alert.setAlertType(AlertType.WARNING);
						alert.setHeaderText(null);
						alert.setContentText("El nombre del departamento que intenta \ncrear ya existe");
						alert.showAndWait();
					} else {
						logger.info("Intento guardar el nuevo departamento");
						confirmarDepartamentoStage(new Alert(AlertType.CONFIRMATION), nombreField.getText());
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
			stage.setTitle("Control de paquetería - Alta y modificacion de departamentos");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modificarDepartamentoStage(Stage stage) {


		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        Platform.exit();
		    }
		});
		
		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();

			Alert alert = new Alert(AlertType.WARNING, "content text");
			alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
					.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));

			alert.setTitle("Departamentos");

			List<Departamento> departamentoList = new ArrayList<Departamento>();
			ObservableList<Departamento> datos = null;

			try {
				datos = DepartamentoBackend.getDepartamentoData();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			Scene scene = new Scene(paneVbox, 400, 450);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/departamento.css").toExternalForm());

			TableView<Departamento> table = new TableView<Departamento>();
			table.setEditable(true);

			TableColumn<Departamento, String> idCol = new TableColumn<Departamento, String>("Id");
			idCol.setMinWidth(200);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Departamento, String> deptoCol = new TableColumn<Departamento, String>("Departamento");
			deptoCol.setMinWidth(200);
			deptoCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

			deptoCol.setCellFactory(TextFieldTableCell.<Departamento> forTableColumn());
			deptoCol.setOnEditCommit((CellEditEvent<Departamento, String> t) -> {

				if (!(t.getNewValue().equals("")) && DepartamentoBackend.checkString(t.getNewValue()) == true) {
					((Departamento) t.getTableView().getItems().get(t.getTablePosition().getRow()))
							.setNombre(t.getNewValue());
					Departamento departamento = new Departamento();
					departamento.setId(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId());
					departamento.setNombre(t.getNewValue());

					int counter = -1;
					int idObj = departamento.getId();

					if (departamentoList.isEmpty()) {
						departamentoList.add(departamento);
					}

					for (Departamento element : departamentoList) {
						counter = counter + 1;
						if (element.getId() == idObj) {

							departamentoList.remove(counter);
							departamentoList.add(departamento);
							break;
						} else {
							departamentoList.add(departamento);
							break;
						}
					}
				} else {
					logger.error("El nombre del departamento no debe ir vacio");
					alert.setHeaderText("Error al ingresar datos");
					alert.setContentText("El dato ingresado no contiene la estructura requerida. Por favor corrigalo");
					alert.showAndWait();

					table.getColumns().get(0).setVisible(false);
					table.getColumns().get(0).setVisible(true);
				}
			});

			table.setItems(datos);
			table.getColumns().addAll(idCol, deptoCol);

			paneVbox.setSpacing(10);
			paneVbox.setPadding(new Insets(1));
			paneVbox.getChildren().addAll(table);

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (departamentoList.isEmpty()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Modificaciones en departamentos");
						alert.setHeaderText("Alerta");
						alert.setContentText("Aún no ha hecho cambios en registros");
						alert.showAndWait();
					} else {
						confirmarModificacionesDepartamentos(new Alert(AlertType.CONFIRMATION), departamentoList);
						departamentoList.clear();
					}
				}
			});
			
			Button cancelButton = new Button("Cancelar");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					DepartamentoWindow depto = new DepartamentoWindow();
					depto.departamentoStage(stage);
				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(aceptarButton, cancelButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar departamentos");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void confirmarModificacionesDepartamentos(Alert confirmation, List<Departamento> departamentos) {

		Text text = new Text();
		String output = "";

		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		for (Departamento element : departamentos) {
			output += "\nDepartamento: " + element.getNombre();
		}
		text.setText(output);

		confirmation.setContentText(text.getText());

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				departamentoDAO.altaDepartamentoByList(departamentos);
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cambios en departamentos");
			alert.setHeaderText(null);
			alert.setContentText("Los cambios se guardaron exitosamente");
			alert.showAndWait();
		} else {
			// hacer algo
		}
	}

	public void confirmarDepartamentoStage(Alert confirmation, String nombreDepartamento) {

		Text text = new Text();
		String output = "";

		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		output += "\nDepartamento: " + nombreDepartamento;
		text.setText(output);

		confirmation.setContentText(text.getText());

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {

			try {
				DepartamentoBackend.loadDepartamentoData(nombreDepartamento);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cambios en departamentos");
				alert.setHeaderText(null);
				alert.setContentText("Los cambios se guardaron exitosamente");
				alert.showAndWait();
			} catch (Exception e) {
				logger.error(e.getMessage());

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Cambios en departamentos");
				alert.setHeaderText(null);
				alert.setContentText("Ocurrió un error al intentar realizar cambios en departamentos");
				alert.showAndWait();
			}
		} else {
			// hacer algo
		}
	}
}
