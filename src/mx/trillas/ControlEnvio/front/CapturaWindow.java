package mx.trillas.ControlEnvio.front;

import java.util.Date;
import java.util.Optional;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillas.ControlEnvio.backend.CapturarRegistro;
import mx.trillas.ControlEnvio.persistence.dao.DepartamentoDAO;
import mx.trillas.ControlEnvio.persistence.dao.DestinatarioDAO;
import mx.trillas.ControlEnvio.persistence.dao.GuiaDAO;
import mx.trillas.ControlEnvio.persistence.dao.MensajeriaDAO;
import mx.trillas.ControlEnvio.persistence.dao.OrigenesDAO;
import mx.trillas.ControlEnvio.persistence.impl.DepartamentoDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.DestinatarioDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.GuiaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.MensajeriaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.OrigenesDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;
import mx.trillas.ControlEnvio.persistence.pojos.Destinatario;
import mx.trillas.ControlEnvio.persistence.pojos.Guia;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;
import mx.trillas.ControlEnvio.persistence.pojos.Origen;
import mx.trillas.ControlEnvio.persistence.pojos.Usuario;

public class CapturaWindow {

	private static Logger logger = Logger.getLogger(CapturaWindow.class);
	
	private static GuiaDAO guiaDAO = new GuiaDAODBImpl();
	private static OrigenesDAO origenDAO = new OrigenesDAODBImpl();
	private static MensajeriaDAO mensajeriaDAO = new MensajeriaDAODBImpl();
	private static DestinatarioDAO destinatarioDAO = new DestinatarioDAODBImpl();
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAODBImpl();

	public void CapturaStage(Stage stage, Usuario usuario) {
		try {
			Alert alert = new Alert(AlertType.WARNING, "content text");
			alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
			.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
			
			alert.setTitle("Captura de paquetería");
			
			VBox rootVBox = new VBox(10);

			FlowPane mensajeraPane = new FlowPane(1, 1);
			FlowPane headerPane = new FlowPane();

			FlowPane guiaPane = new FlowPane(17, 10);
			FlowPane destinatarioPane = new FlowPane(45, 10);
			FlowPane deptosPane = new FlowPane(25, 10);
			FlowPane origenPane = new FlowPane(55, 10);
			FlowPane otroOrigenPane = new FlowPane(45, 10);
			FlowPane otroDestinatarioPane = new FlowPane();
			FlowPane otroDeptoPane = new FlowPane(5, 10);
			FlowPane observacionPane = new FlowPane(35, 10);
			
			FlowPane clearPane = new FlowPane();
			FlowPane buttonsPane = new FlowPane();

			FlowPane verticalFlow = new FlowPane(10,1);
			VBox vertical1 = new VBox(20);
			VBox vertical2 = new VBox(12);
			
			verticalFlow.setPadding(new Insets(5, 0, 5, 30));
			vertical1.setAlignment(Pos.CENTER);
			
			Scene scene = new Scene(rootVBox, 435, 550);

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

			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/captura.css").toExternalForm());

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					MenuWindow menu = new MenuWindow();
					menu.UserMenuStage(stage, usuario);
				}
			});
			headerPane.setAlignment(Pos.TOP_LEFT);
			headerPane.getChildren().add(backButton);
			rootVBox.getChildren().add(headerPane);

			Label labelMensajeria = new Label();
			labelMensajeria.setText("Paqueteria");
			
			ObservableList<Mensajeria> mensajeriaList = FXCollections.observableArrayList(mensajeriaDAO.getMensajeriaList());

			ComboBox<Object> mensajeriaCombo = new ComboBox<>();

			for (Mensajeria element : mensajeriaList) {
				String elementFormat = element.toString().substring(0, 1).toUpperCase()
						+ element.toString().substring(1);
				mensajeriaCombo.getItems().add(elementFormat);
			}

			mensajeriaCombo.getItems().addAll();
			mensajeriaCombo.setPromptText("Seleccione una opción...");
			
			vertical1.getChildren().addAll(labelMensajeria);
			vertical2.getChildren().addAll( mensajeriaCombo);

			Label guiaLabel = new Label("Numero guia");

			TextField guiaField = new TextField();
			guiaField.textProperty().addListener(( observable, oldValue, newValue) -> {
				   if (guiaField.getText().length() > 44) {
		                String s = guiaField.getText().substring(0, 44);
		                guiaField.setText(s);
		            }
			});
			vertical1.getChildren().addAll(guiaLabel);
			vertical2.getChildren().addAll( guiaField);

			TextField otroOrigenField = new TextField();
			otroOrigenField.textProperty().addListener(( observable, oldValue, newValue) -> {
				   if (otroOrigenField.getText().length() > 44) {
		                String s = otroOrigenField.getText().substring(0, 44);
		                otroOrigenField.setText(s);
		            }
			});
			
			TextField otroDeptoField = new TextField();
			otroDeptoField.textProperty().addListener(( observable, oldValue, newValue) -> {
				   if (otroDeptoField.getText().length() > 44) {
		                String s = otroDeptoField.getText().substring(0, 44);
		                otroDeptoField.setText(s);
		            }
			});
			
			TextField otroDestinatarioField = new TextField();
			otroDestinatarioField.textProperty().addListener(( observable, oldValue, newValue) -> {
				   if (otroDestinatarioField.getText().length() > 44) {
		                String s = otroDestinatarioField.getText().substring(0, 44);
		                otroDestinatarioField.setText(s);
		            }
			});
			
			otroOrigenField.setDisable(true);
			otroDeptoField.setDisable(true);
			otroDestinatarioField.setDisable(true);

			Label origenMensajeria = new Label("Origen");

			ObservableList<Origen> origenList = FXCollections.observableArrayList(origenDAO.getOrigenList());

			ComboBox<Object> origenCombo = new ComboBox<>();

			for (Origen element : origenList) {
				String elementFormat = element.toString().substring(0, 1).toUpperCase()
						+ element.toString().substring(1);
				origenCombo.getItems().add(elementFormat);
			}
			origenCombo.getItems().add("Otro");
			origenCombo.setPromptText("Seleccione una opción...");
			origenCombo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					try {
						if (origenCombo != null && origenCombo.getValue().equals("Otro")) {
							otroOrigenField.setDisable(false);
						} else {
							otroOrigenField.setDisable(true);
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			});
			
			vertical1.getChildren().addAll(origenMensajeria);
			vertical2.getChildren().addAll( origenCombo);

			Label otroOrigenLabel = new Label("Otro origen");
			
			vertical1.getChildren().addAll(otroOrigenLabel);
			vertical2.getChildren().addAll( otroOrigenField);

			Label deptoLabel = new Label("Departamento");

			ComboBox<Object> deptoCombo = new ComboBox<>();
			ComboBox<Object> destinatarioCombo = new ComboBox<>();

			ObservableList<Departamento> departamentoList = FXCollections
					.observableArrayList(departamentoDAO.getDepartamentoList());

			for (Departamento element : departamentoList) {
				String elementFormat = element.toString().substring(0, 1).toUpperCase()
						+ element.toString().substring(1);
				deptoCombo.getItems().add(elementFormat);
			}
			deptoCombo.getItems().add("Otro");
			deptoCombo.setPromptText("Seleccione una opción...");
			deptosPane.setAlignment(Pos.CENTER);
			deptoCombo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					if ( deptoCombo.getValue() != null ) {
						Departamento departamentoForCombo = null;
						ObservableList<Destinatario> destinatarioList = null;
	
						try {
							departamentoForCombo = departamentoDAO.getDepartamento(deptoCombo.getValue().toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.error(e.getMessage());
						}
	
						try {
							destinatarioList = FXCollections.observableArrayList(destinatarioDAO.getDestinatarioFromDeptoList(departamentoForCombo));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.error(e.getMessage());
						}
						destinatarioCombo.getItems().clear();
						for (Destinatario element : destinatarioList) {
							String elementFormat = element.toString().substring(0, 1).toUpperCase()
									+ element.toString().substring(1);
	
							destinatarioCombo.getItems().add(elementFormat);
						}
						if (!(destinatarioCombo.getItems().contains("Otro"))) {
							destinatarioCombo.getItems().add("Otro");
						}
					}
					
					if (deptoCombo.getValue() != null && deptoCombo.getValue().equals("Otro")) {
						otroDeptoField.setDisable(false);
						otroDestinatarioField.setDisable(false);
						destinatarioCombo.setDisable(true);
					} else {
						otroDeptoField.setDisable(true);
						otroDestinatarioField.setDisable(true);
						destinatarioCombo.setDisable(false);
					}
				}
			});
			vertical1.getChildren().addAll(deptoLabel);
			vertical2.getChildren().addAll( deptoCombo);

			Label destinatarioLabel = new Label("Destinatario");

			destinatarioCombo.setPromptText("Seleccione una opción...");
			destinatarioCombo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (destinatarioCombo.getValue() != null && deptoCombo.getValue() != null) {
						if (destinatarioCombo.getValue().equals("Otro") || deptoCombo.getValue().equals("Otro")) {
							otroDeptoField.setDisable(false);
							otroDestinatarioField.setDisable(false);
							deptoCombo.setDisable(true);
						} else {
							otroDeptoField.setDisable(true);
							otroDestinatarioField.setDisable(true);
							deptoCombo.setDisable(false);
						}
					}
				}
			});
			
			vertical1.getChildren().addAll(destinatarioLabel);
			vertical2.getChildren().addAll( destinatarioCombo);

			Label otroDestinatarioLabel = new Label("Otro destinatario");
			Label otroDeptoLabel = new Label("Otro departamento");

			vertical1.getChildren().addAll(otroDestinatarioLabel);
			vertical2.getChildren().addAll( otroDestinatarioField);

			vertical1.getChildren().addAll(otroDeptoLabel);
			vertical2.getChildren().addAll(otroDeptoField);

			Label observacionLabel = new Label("Observacion");

			TextField observacionField = new TextField();
			observacionField.textProperty().addListener(( observable, oldValue, newValue) -> {
				   if (observacionField.getText().length() > 44) {
		                String s = observacionField.getText().substring(0, 44);
		                observacionField.setText(s);
		            }
			});
			
			vertical1.getChildren().addAll(observacionLabel);
			vertical2.getChildren().addAll( observacionField);
			verticalFlow.getChildren().addAll(vertical1, vertical2);

			Button clearButton = new Button("Limpiar formulario");
			clearButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					mensajeriaCombo.setValue(null);
					origenCombo.setValue(null);
					destinatarioCombo.getItems().clear();
					destinatarioCombo.setValue(null);
					deptoCombo.setValue(null);
					
					observacionField.clear();
					otroDeptoField.clear();
					otroDestinatarioField.clear();
					otroOrigenField.clear();
					guiaField.clear();
					
					otroDeptoField.setDisable(true);
					otroDestinatarioField.setDisable(true);
					otroOrigenField.setDisable(true);
					deptoCombo.setDisable(false);
					destinatarioCombo.setDisable(false);
				}
			});
			clearPane.getChildren().addAll(clearButton);

			Button submitButton = new Button("Aceptar");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Guia guia = new Guia();
					Boolean flag = true;
					Mensajeria mensajeria = null;
					Origen origen = null;
					Destinatario destinatario = null;

					if (mensajeriaCombo.getValue() == null || mensajeriaCombo.getValue().toString() == null
							|| mensajeriaCombo.getValue().toString().equals("")) {
						logger.error("El valor de la mensajería no puede ir vacio");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El valor de la mensajería no puede ir vacio");
						alert.showAndWait();
						flag = false;
					} else {
						try {
							mensajeria = mensajeriaDAO.getMensajeria(mensajeriaCombo.getValue().toString());
							flag = true;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.error(e.getMessage());
						}
						guia.setMensajeria(mensajeria);
					}

					if (guiaField.getText() == null || guiaField.getText().equals("")) {
						logger.error("El número guia no puede ir vacío");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El número guia no puede ir vacío");
						alert.showAndWait();
						flag = false;
					} else if (!(CapturarRegistro.checkStructNumeroGuia(guiaField.getText()))) {
						logger.error("El número guia no contiene la estructura requerida");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El número guia no contiene la estructura requerida");
						alert.showAndWait();
						flag = false;
					} else {
						guia.setNumero(guiaField.getText());
					}

					if (origenCombo.getValue() == null || origenCombo.getValue().toString() == null
							|| origenCombo.getValue().toString().equals("")) {
						logger.error("El valor del origen no puede ir vacio");
						alert.setHeaderText("Error al ingresar datos");
						alert.setContentText("El valor del origen no puede ir vacio");
						alert.showAndWait();
						flag = false;
					} else if (origenCombo.getValue().toString().equals("Otro")) {
						if (otroOrigenField.getText() == null || otroOrigenField.getText().equals("")) {
							logger.error("El valor de otro origen no puede ir vacio");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText(
									"El valor de otro origen no puede ir vacio.\nComplete el campo o seleccione un origen");
							alert.showAndWait();
							flag = false;
						} else if (!(CapturarRegistro.checkString(otroOrigenField.getText()))) {
							logger.error("El nombre origen no contiene la estructura requerida");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText("El nombre origen no contiene la estructura requerida");
							alert.showAndWait();
							flag = false;
						} else {
							guia.setOtroorigen(otroOrigenField.getText());
						}
					} else {
						try {
							origen = origenDAO.getOrigen(origenCombo.getValue().toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.error(e.getMessage());
						}
						guia.setOrigen(origen);
					}

					/* Filtro para Departamento y destinatarios */
					if (deptoCombo.getValue() == null  && deptoCombo.getValue().toString().equals("") && destinatarioCombo.getValue() == null && destinatarioCombo.getValue().toString().equals("")) 
					{
							logger.error("Los datos departamento y destinatario no pueden ir vacios");
							alert.setHeaderText("Los datos departamento y destinatario no pueden ir vacios");
							alert.setContentText("Los datos departamento y destinatario no pueden ir vacios");
							alert.showAndWait();
							flag = false;
					}
					
					/* Filtro para otros Departamento y destinatarios */
					else if (deptoCombo.getValue() != null && deptoCombo.getValue().toString().equals("Otro") || destinatarioCombo.getValue() != null && destinatarioCombo.getValue().toString().equals("Otro")) {
						if (otroDeptoField.getText() == null || otroDeptoField.getText().equals("")) {
							logger.error("El valor Otro Departamento no puede ir vacio");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText("El valor de Otro Departamento no puede ir vacio.\nComplete el campo o seleccione un departamento");
							alert.showAndWait();
							flag = false;
						} else if (!(CapturarRegistro.checkString(otroDeptoField.getText()))) {
							logger.error("El nombre departamento no contiene la estructura requerida");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText("El nombre departamento no contiene la estructura requerida");
							alert.showAndWait();
							flag = false;
						} else {
							guia.setOtrodepartamento(otroDeptoField.getText());
						}
						
						if (otroDestinatarioField.getText() == null || otroDestinatarioField.getText().equals("")) {
							logger.error("El valor Otro destinatario no puede ir vacio");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText("El valor Otro destinatario no puede ir vacio.\nComplete el campo o seleccione un destinatario");
							alert.showAndWait();
							flag = false;
						} else if (!(CapturarRegistro.checkString(otroDestinatarioField.getText()))) {
							logger.error("El nombre destinatario no contiene la estructura requerida");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText("El nombre destinatario no contiene la estructura requerida");
							alert.showAndWait();
							flag = false;
						} else {
							guia.setOtrodestinatario(otroDestinatarioField.getText());
							guia.setFecha(new Date());
							guia.setUsuario(usuario);
						}
					}
					 else {
							try {
							destinatario = destinatarioDAO.getDestinatarioByName(destinatarioCombo.getValue().toString());
							guia.setDestinatario(destinatario);
							guia.setFecha(new Date());
							guia.setUsuario(usuario);
							guia.setObservacion(observacionField.getText());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.error(e.getMessage());
						}	
					}
					
					/* Concluye y verifica si el numero guia existe */
					Guia guiaObj = null;
					try {
						guiaObj = guiaDAO.getGuia(guiaField.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage());
					}
					if (flag == true) { 
						if(guiaObj == null) {
							confirmarStage(new Alert(AlertType.CONFIRMATION), guia, usuario);
						} else if (guiaObj != null) {
							logger.error("Un registro con el mismo número guía ya existe. Verifique el dato");
							alert.setHeaderText("Error al guardar datos");
							alert.setContentText("Un registro con el mismo número guía ya existe. Verifique el dato");
							alert.showAndWait();
						}
					}
				}
			});
			buttonsPane.getChildren().addAll(submitButton);
			rootVBox.getChildren().addAll(verticalFlow);
			rootVBox.getChildren().addAll(clearPane, buttonsPane);
			
			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Capturar registro");
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void confirmarStage(Alert confirmation, Guia guia, Usuario usuario) {

		Text text = new Text();
		String output = "";

		confirmation.setTitle("Confirme los cambios");
		confirmation.setHeaderText("¿Desea guardar los cambios?");

		output += "\nPaquetería: " + guia.getMensajeria().getNombre();
		output += "\nNúmero Guia: " + guia.getNumero();

		if (guia.getOrigen() != null && !(guia.getOrigen().getNombre().toString().equals("")))
			output += "\nOrigen: " + guia.getOrigen().getNombre();
		if (guia.getDestinatario() != null && !(guia.getDestinatario().getNombre().toString().equals("")))
			output += "\nDestinatario: " + guia.getDestinatario().getNombre();
		if (guia.getDestinatario() != null && guia.getDestinatario().getDepartamento() != null
				&& !(guia.getDestinatario().getDepartamento().getNombre().toString().equals("")))
			output += "\nDepartamento: " + guia.getDestinatario().getDepartamento().getNombre();
		if (guia.getOtrodepartamento() != null && !(guia.getOtrodepartamento().toString().equals("")))
			output += "\nDepartamento: " + guia.getOtrodepartamento();
		if (guia.getOtrodestinatario() != null && !(guia.getOtrodestinatario().toString().equals("")))
			output += "\nDestinatario: " + guia.getOtrodestinatario();
		if (guia.getObservacion() != null && !(guia.getObservacion().toString().equals("")))
			output += "\nObservaciones: " + guia.getObservacion().toString();

		text.setText(output);

		confirmation.setContentText(text.getText());

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				CapturarRegistro.loadCapturaData(guia);
				
				Alert alertInfo = new Alert(AlertType.INFORMATION);
				alertInfo.setHeaderText("Alerta de confirmación");
				alertInfo.setContentText("El registro se ha guardado exitosamente");
				alertInfo.showAndWait();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			// hacer algo
		}
	}
}
