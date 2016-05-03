package mx.trillas.ControlEnvio.front;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import javafx.scene.input.KeyEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private List<Mensajeria> mensajeriaList = new ArrayList<Mensajeria>();

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

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Mensajeria");
			
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
					try {
						mensajeria.modificarMensajeriaStage(stage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error("Ocurrio un error al procesar cambios");
					}
				}
			});

			headerPane.getChildren().addAll(backButton, modifyButton);
			rootVbox.getChildren().addAll(headerPane);

			Text text = new Text("Ingrese la nueva empresa de mensajeria");
			rootVbox.getChildren().addAll(text);

			Label nombreLabel = new Label("Mensajería:");
			TextField mensajeriaField = new TextField();
			mensajeriaField.textProperty().addListener(( observable, oldValue, newValue) -> {
				   if (mensajeriaField.getText().length() > 44) {
		                String s = mensajeriaField.getText().substring(0, 44);
		                mensajeriaField.setText(s);
		            }
			});
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
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El nombre de empresa de mensajeria \nno debe ir vacio");
						alert.showAndWait();
					} else if (!(MensajeriaBackend.checkString(mensajeriaField.getText()))) {
						logger.error("El nombre de empresa de mensajeria no contiene la estructura requerida");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El nombre de empresa de mensajeria no \ncontiene la estructura requerida");
						alert.showAndWait();
					} else if (mensajeriaObj != null) {
						logger.info("La empresa de mensajeria que intenta crear ya existe.");
						alert.setAlertType(AlertType.WARNING);
						alert.setHeaderText(null);
						alert.setContentText("La empresa de mensajeria que intenta \ncrear ya existe");
						alert.showAndWait();
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

	public void modificarMensajeriaStage(Stage stage) throws Exception {

		ObservableList<Mensajeria> datos = null;

		try {
			datos = MensajeriaBackend.getMensajeriaData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}

		try {
			VBox paneVbox = new VBox();
			FlowPane returnPane = new FlowPane();
			FlowPane buttonsPane = new FlowPane();

			Scene scene = new Scene(paneVbox, 400, 490);
			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/mensajeria.css").toExternalForm());

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MensajeriaWindow window = new MensajeriaWindow();
					window.mensajeriaStage(stage);
				}
			});
			returnPane.getChildren().addAll(backButton);
			paneVbox.getChildren().addAll(returnPane);

			TableView<Mensajeria> table = new TableView<Mensajeria>();
			table.setEditable(true);

			TableColumn<Mensajeria, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(170);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Mensajeria, String> mensajeraCol = new TableColumn<>("Nombre");
			mensajeraCol.setMinWidth(240);
			mensajeraCol.setEditable(true);
			mensajeraCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			mensajeraCol.setCellFactory(TextFieldTableCell.<Mensajeria> forTableColumn());
			mensajeraCol.setOnEditCommit((CellEditEvent<Mensajeria, String> t) -> {

				if (!(t.getNewValue().equals("")) && MensajeriaBackend.checkString(t.getNewValue()) == true && MensajeriaBackend.checkRightString(t.getNewValue())) {
					((Mensajeria) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNombre(t.getNewValue());
					
					Mensajeria mensajeria = new Mensajeria();
					mensajeria.setId(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId());
					mensajeria.setNombre(t.getNewValue());
				
					Mensajeria mensajeriaObj = null;
					try {
						mensajeriaObj = mensajeriaDAO.getMensajeriaById(mensajeria.getId());
						mensajeriaObj.setNombre(mensajeria.getNombre());
					} catch (Exception e) {
						logger.error("Ocurrio un error al intentar conseguir una empresa de mensajeria");
					}
					mensajeriaList.add(mensajeriaObj);
				} else {
					((Mensajeria) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNombre(t.getOldValue());
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Modificaciones en mensajería");
					alert.setHeaderText("Alerta");
					alert.setContentText("Debe ingresar datos válidos");
					alert.showAndWait();
				} 
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
					if (mensajeriaList.isEmpty()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Modificaciones en mensajería");
						alert.setHeaderText("Alerta");
						alert.setContentText("Aún no ha hecho cambios en registros");
						alert.showAndWait();
					} else {
						confirmarModificacionesMensajerias(stage, mensajeriaList);
					}
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
			buttonsPane.getChildren().addAll(aceptarButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Modificar empresa de mensajeria");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			throw e;
		}
	}

	public void confirmarModificacionesMensajerias(Stage stage, List<Mensajeria> mensajerias) {

		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Scene scene = new Scene(rootVbox, 450, 270);
			rootVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());

			Text text = new Text("Desea guardar los cambios?\n");
			String output = "";
			for (Mensajeria element : mensajerias) {
				output += "\nEmpresa de mensajería: " + element.getNombre();
			}
			text.setText(output);
			rootVbox.getChildren().addAll(text);

			Button aceptarButton = new Button(" Guardar ");
			aceptarButton.setEffect(shadow);
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					try {
						mensajeriaDAO.altaMensajeriaByList(mensajerias);

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Cambios en mensajerias");
						alert.setHeaderText(null);
						alert.setContentText("Los cambios se guardaron exitosamente");
						alert.showAndWait();

						MensajeriaWindow window = new MensajeriaWindow();
						window.modificarMensajeriaStage(stage);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Cambios en mensajerias");
						alert.setHeaderText(null);
						alert.setContentText("Ocurrió un error al intentar realizar cambios en mensajerias");
						alert.showAndWait();

						MenuWindow menu = new MenuWindow();
						menu.AdminMenuStage(stage);

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
					MensajeriaWindow window = new MensajeriaWindow();
					window.mensajeriaStage(stage);
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

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Cambios en mensajerias");
						alert.setHeaderText(null);
						alert.setContentText("La empresa de mensajería se ha guardado exitosamente");
						alert.showAndWait();

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