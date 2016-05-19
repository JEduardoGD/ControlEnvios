package mx.trillas.ControlEnvio.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import mx.trillas.ControlEnvio.backend.MensajeriaBackend;
import mx.trillas.ControlEnvio.persistence.dao.MensajeriaDAO;
import mx.trillas.ControlEnvio.persistence.impl.MensajeriaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

public class MensajeriaWindow {

	private static Logger logger = Logger.getLogger(MensajeriaWindow.class);
	private static MensajeriaDAO mensajeriaDAO = new MensajeriaDAODBImpl();

	public void mensajeriaStage(Stage stage) {

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

			FlowPane flowButtonsPane = new FlowPane();

			HBox headerPane = new HBox(150);
			Scene scene = new Scene(new Group(), 480, 450);
			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/mensajeria.css").toExternalForm());

			((Group) scene.getRoot()).getChildren().addAll(rootVbox);

			Alert alert = new Alert(AlertType.WARNING, "content text");
			alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
					.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
			alert.setTitle("Mensajeria");

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setCursor(Cursor.HAND);
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
			modifyButton.setCursor(Cursor.HAND);
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
			text.getStyleClass().add("textRules");
			rootVbox.getChildren().addAll(text);

			Label nombreLabel = new Label("Mensajería:");
			TextField mensajeriaField = new TextField();
			mensajeriaField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (mensajeriaField.getText().length() > 44) {
					String s = mensajeriaField.getText().substring(0, 44);
					mensajeriaField.setText(s);
				}
			});
			nombrePane.getChildren().addAll(nombreLabel, mensajeriaField);
			rootVbox.getChildren().addAll(nombrePane);

			Button aceptarButton = new Button("Guardar");
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
						alert.setContentText("El nombre de empresa de mensajeria no debe ir vacio");
						alert.showAndWait();
					} else if (!(MensajeriaBackend.checkString(mensajeriaField.getText()))) {
						logger.error("El nombre de empresa de mensajeria no contiene la estructura requerida");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El nombre de empresa de mensajeria no contiene la estructura requerida");
						alert.showAndWait();
					} else if (mensajeriaObj != null) {
						logger.info("La empresa de mensajeria que intenta crear ya existe.");
						alert.setAlertType(AlertType.WARNING);
						alert.setHeaderText(null);
						alert.setContentText("La empresa de mensajeria que intenta crear ya existe");
						alert.showAndWait();
					} else {
						logger.info("Intento guardar la empresa de mensajeria");
						confirmarMensajeriaStage(new Alert(AlertType.CONFIRMATION), mensajeriaField.getText());

					}
				}
			});

			flowButtonsPane.setAlignment(Pos.CENTER);
			flowButtonsPane.getChildren().addAll(aceptarButton);

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

	public void confirmarMensajeriaStage(Alert confirmation, String nombreMensajeria) {

		Text text = new Text();
		String output = "Mensajeria: " + nombreMensajeria;

		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		text.setText(output);
		
		confirmation.setContentText(text.getText());
		
		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				MensajeriaBackend.loadMensajeriaData(nombreMensajeria);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cambios en mensajerias");
				alert.setHeaderText(null);
				alert.setContentText("La empresa de mensajería se ha guardado exitosamente");
				alert.showAndWait();
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
		} else {
			// hacer algo
		}
	}
	
	public void modificarMensajeriaStage(Stage stage) throws Exception {

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		
		VBox paneVbox = new VBox();
		paneVbox.setAlignment(Pos.CENTER);
		
		FlowPane returnPane = new FlowPane();
		FlowPane buttonsPane = new FlowPane();

		Scene scene = new Scene(paneVbox, 430, 520);
		stage.setScene(scene);
		stage.setTitle("Control de paquetería - Modificar empresa de mensajeria");
		stage.setResizable(false);
		stage.show();
		
		Alert alert = new Alert(AlertType.WARNING, "content text");
		alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
		.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
		
		alert.setTitle("Mensajeria");

		List<Mensajeria> mensajeriaList = new ArrayList<Mensajeria>();
		ObservableList<Mensajeria> datos = null;

		try {
			datos = MensajeriaBackend.getMensajeriaData();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/mensajeria.css").toExternalForm());

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setCursor(Cursor.HAND);
			backButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					MensajeriaWindow window = new MensajeriaWindow();
					window.mensajeriaStage(stage);
				}
			});
			returnPane.getChildren().addAll(backButton);
			paneVbox.getChildren().addAll(returnPane);

			Text text = new Text("Edite los registros que necesite modificar, y guarde \nlos cambios presionando el botón guardar.");
			text.getStyleClass().add("textRules");
			paneVbox.getChildren().addAll(text);
			
			TableView<Mensajeria> table = new TableView<Mensajeria>();
			table.setEditable(true);

			TableColumn<Mensajeria, String> idCol = new TableColumn<>("Id");
			idCol.setMinWidth(180);
			idCol.setEditable(false);
			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Mensajeria, String> mensajeraCol = new TableColumn<>("Nombre");
			mensajeraCol.setMinWidth(247);
			mensajeraCol.setEditable(true);
			mensajeraCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			mensajeraCol.setCellFactory(TextFieldTableCell.<Mensajeria> forTableColumn());
			mensajeraCol.setOnEditCommit((CellEditEvent<Mensajeria, String> t) -> {

				if (!(t.getNewValue().equals("")) && MensajeriaBackend.checkString(t.getNewValue()) == true) {
					((Mensajeria) t.getTableView().getItems().get(t.getTablePosition().getRow()))
							.setNombre(t.getNewValue());
					Mensajeria mensajeria = new Mensajeria();
					mensajeria.setId(t.getTableView().getItems().get(t.getTablePosition().getRow()).getId());
					mensajeria.setNombre(t.getNewValue());

					int counter = -1;
					int idObj = mensajeria.getId();

					if (mensajeriaList.isEmpty()) {
						mensajeriaList.add(mensajeria);
					}
					
					for (Mensajeria element : mensajeriaList) {
						counter = counter + 1;
						if (element.getId() == idObj) {
							
							mensajeriaList.remove(counter);
							mensajeriaList.add(mensajeria);
							break;
						} else {
							mensajeriaList.add(mensajeria);
							break;
						}
					}
				} else {
					logger.error("El nombre de empresa de mensajeria no debe ir vacio");
					alert.setHeaderText("Error al ingresar datos");
					alert.setContentText("El dato ingresado no contiene la estructura requerida. Por favor corrigalo");
					alert.showAndWait();
					
					table.getColumns().get(0).setVisible(false);
					table.getColumns().get(0).setVisible(true);
				}
			});
			table.setItems(datos);
			table.getColumns().addAll(idCol, mensajeraCol);

			paneVbox.setSpacing(10);
			paneVbox.setPadding(new Insets(1));
			paneVbox.getChildren().addAll(table);

			Button aceptarButton = new Button("Guardar");
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if (mensajeriaList.isEmpty()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Modificaciones en mensajería");
						alert.setHeaderText("Alerta");
						alert.setContentText("Aún no ha hecho cambios en registros");
						alert.showAndWait();
					} else {
						confirmarModificacionesMensajerias(new Alert(AlertType.CONFIRMATION), mensajeriaList);
						mensajeriaList.clear();
					}
				}
			});
			
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(aceptarButton);
			paneVbox.getChildren().addAll(buttonsPane);
		} catch (Exception e) {
			throw e;
		}
	}

	public void confirmarModificacionesMensajerias(Alert confirmation, List<Mensajeria> mensajerias) {
		
		Text text = new Text();
		String output = "";
		
		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		for (Mensajeria element : mensajerias) {
			output += "\nEmpresa: " + element.getNombre();
		}
		text.setText(output);
		
		confirmation.setContentText(text.getText());
		
		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				mensajeriaDAO.altaMensajeriaByList(mensajerias);
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
}