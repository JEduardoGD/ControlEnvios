package mx.trillas.ControlEnvio.front;

import java.util.Date;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillas.ControlEnvio.backend.CapturarRegistro;
import mx.trillas.ControlEnvio.persistence.dao.DepartamentoDAO;
import mx.trillas.ControlEnvio.persistence.dao.DestinatarioDAO;
import mx.trillas.ControlEnvio.persistence.dao.MensajeriaDAO;
import mx.trillas.ControlEnvio.persistence.dao.OrigenesDAO;
import mx.trillas.ControlEnvio.persistence.dao.UsuarioDAO;
import mx.trillas.ControlEnvio.persistence.impl.DepartamentoDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.DestinatarioDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.GuiaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.MensajeriaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.OrigenesDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.UsuarioDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;
import mx.trillas.ControlEnvio.persistence.pojos.Destinatario;
import mx.trillas.ControlEnvio.persistence.pojos.Guia;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;
import mx.trillas.ControlEnvio.persistence.pojos.Origen;
import mx.trillas.ControlEnvio.persistence.pojos.Usuario;
import mx.trillas.ControlEnvio.persistence.pojosaux.Controlenvio;

public class CapturaWindow {

	private static Logger logger = Logger.getLogger(CapturaWindow.class);

	private static MensajeriaDAO mensajeriaDAO = new MensajeriaDAODBImpl();
	private static OrigenesDAO origenDAO = new OrigenesDAODBImpl();
	private static DestinatarioDAO destinatarioDAO = new DestinatarioDAODBImpl();
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAODBImpl();
	// private static ObservacionesDAO observacionesDAO = new
	// ObservacionesDAODBImpl();
	private static UsuarioDAO usuarioDAO = new UsuarioDAODBImpl();

	public void CapturaStage(Stage stage, Usuario usuario) {
		try {
			VBox rootVBox = new VBox(5);
			VBox vbox = new VBox(20);

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

			Scene scene = new Scene(rootVBox, 480, 560);

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Captura de paquetería");

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
					// TODO Auto-generated method stub
					MenuWindow menu = new MenuWindow();
					menu.UserMenuStage(stage, usuario);
				}
			});
			headerPane.setAlignment(Pos.TOP_LEFT);
			headerPane.getChildren().add(backButton);
			rootVBox.getChildren().add(headerPane);

			Label labelMensajeria = new Label();
			labelMensajeria.setText("Paqueteria");

			ObservableList<Mensajeria> mensajeriaList = FXCollections
					.observableArrayList(mensajeriaDAO.getMensajeriaList());

			ComboBox<Object> mensajeriaCombo = new ComboBox<>();

			for (Mensajeria element : mensajeriaList) {
				String elementFormat = element.toString().substring(0, 1).toUpperCase()
						+ element.toString().substring(1);
				mensajeriaCombo.getItems().add(elementFormat);
			}

			mensajeriaCombo.getItems().addAll();
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
			/*
			 * guiaField.textProperty().addListener(new ChangeListener<String>()
			 * {
			 * 
			 * @Override public void changed(final ObservableValue<? extends
			 * String> ov, final String oldValue, final String newValue) { if
			 * (tf.getText().length() > maxLength) { String s =
			 * tf.getText().substring(0, maxLength); tf.setText(s); } } });
			 */ guiaPane.getChildren().addAll(guiaLabel, guiaField);
			rootVBox.getChildren().addAll(guiaPane);

			TextField otroOrigenField = new TextField();
			TextField otroDeptoField = new TextField();
			TextField otroDestinatarioField = new TextField();

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
			origenCombo.setPromptText("Seleccione una opción...");
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

			Label otroOrigenLabel = new Label("Otro origen");
			otroOrigenPane.getChildren().addAll(otroOrigenLabel, otroOrigenField);

			rootVBox.getChildren().addAll(otroOrigenPane);

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

					Departamento departamentoForCombo = null;
					ObservableList<Destinatario> destinatarioList = null;

					try {
						departamentoForCombo = departamentoDAO.getDepartamento(deptoCombo.getValue().toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage());
					}

					try {
						destinatarioList = FXCollections.observableArrayList(
								destinatarioDAO.getDestinatarioFromDeptoList(departamentoForCombo));
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
					
					destinatarioCombo.getItems().add("Otro");
					
					if (deptoCombo.getValue().equals("Otro")) {
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

			deptosPane.getChildren().addAll(deptoLabel, deptoCombo);
			rootVBox.getChildren().addAll(deptosPane);

			Label destinatarioLabel = new Label("Destinatario");

			destinatarioCombo.setPromptText("Seleccione una opción...");
			destinatarioCombo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
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
			});

			destinatarioPane.getChildren().addAll(destinatarioLabel, destinatarioCombo);
			rootVBox.getChildren().addAll(destinatarioPane);

			Label otroDestinatarioLabel = new Label("Otro destinatario");
			Label otroDeptoLabel = new Label("Otro departamento");

			otroDestinatarioPane.getChildren().addAll(otroDestinatarioLabel, otroDestinatarioField);
			rootVBox.getChildren().addAll(otroDestinatarioPane);

			otroDeptoPane.getChildren().addAll(otroDeptoLabel, otroDeptoField);
			rootVBox.getChildren().addAll(otroDeptoPane);

			Label observacionLabel = new Label("Observacion");

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

					Guia guia = new Guia();
					Boolean flag = true;
					Mensajeria mensajeria = null;
					Origen origen = null;
					Departamento departamento = null;
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

					if (deptoCombo.getValue().toString().equals("Otro")
							|| destinatarioCombo.getValue().toString().equals("Otro")) {
						if (otroDeptoField.getText() == null || otroDeptoField.getText().equals("")) {
							logger.error("El valor Otro Departamento no puede ir vacio");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText(
									"El valor de Otro Departamento no puede ir vacio.\nComplete el campo o seleccione un departamento");
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

						if (deptoCombo.getValue() == null || deptoCombo.getValue().toString() == null
								|| deptoCombo.getValue().toString().equals("")) {
							logger.error("El dato departamento no puede ir vacio");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText("El dato departamento no puede ir vacio");
							alert.showAndWait();
							flag = false;
						} else if (destinatarioCombo.getValue() == null
								|| destinatarioCombo.getValue().toString() == null
								|| destinatarioCombo.getValue().toString().equals("")) {
							logger.error("El dato destinatario no puede ir vacio");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText("El dato destinatario no puede ir vacio");
							alert.showAndWait();
							flag = false;
						}

						if (otroDestinatarioField.getText() == null || otroDestinatarioField.getText().equals("")) {
							logger.error("El valor Otro destinatario no puede ir vacio");
							alert.setHeaderText("Error al ingresar datos");
							alert.setContentText(
									"El valor Otro destinatario no puede ir vacio.\nComplete el campo o seleccione un destinatario");
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
						}
					} else {
						try {
							destinatario = destinatarioDAO
									.getDestinatarioByName(destinatarioCombo.getValue().toString());
							guia.setDestinatario(destinatario);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.error(e.getMessage());
						}
					}
					guia.setFecha(new Date());
					guia.setUsuario(usuario);

					if (flag == true) {
						confirmarStage(stage, guia, usuario);
					}
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

	public void confirmarStage(Stage stage, Guia guia, Usuario usuario) {
		DropShadow shadow = new DropShadow();

		try {
			final VBox rootVbox = new VBox();
			FlowPane flowPane = new FlowPane();

			rootVbox.setSpacing(10);
			rootVbox.setPadding(new Insets(30, 30, 30, 30));

			Text text = new Text();
			String output = "Desea guardar los cambios?\n";

			output += "\nPaquetería: " + guia.getMensajeria().getNombre();
			output += "\nNúmero Guia: " + guia.getNumero();

			if (guia.getOrigen() != null && !(guia.getOrigen().getNombre().toString().equals("")))
				output += "\nOrigen: " + guia.getOrigen().getNombre();
			if (guia.getDestinatario() != null && !(guia.getDestinatario().getNombre().toString().equals("")))
				output += "\nDestinatario: " + guia.getDestinatario().getNombre();
			if (guia.getDestinatario() != null && guia.getDestinatario().getDepartamento() != null && !(guia.getDestinatario().getDepartamento().getNombre().toString().equals("")))
				output += "\nDepartamento: " + guia.getDestinatario().getDepartamento().getNombre();
			if (guia.getOtrodepartamento() != null && !(guia.getOtrodepartamento().toString().equals("")))
				output += "\nDepartamento: " + guia.getOtrodepartamento();
			if (guia.getOtrodestinatario() != null && !(guia.getOtrodestinatario().toString().equals("")))
				output += "\nDestinatario: " + guia.getOtrodestinatario();
			if (guia.getObservacion() != null && !(guia.getObservacion().toString().equals("")))
				output += "\nObservaciones: " + guia.getObservacion().toString();

			text.setText(output);

			Scene scene = new Scene(rootVbox, 450, 270);
			rootVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());

			rootVbox.getChildren().addAll(text);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Captura de registro");

			Button aceptarButton = new Button("Aceptar");
			aceptarButton.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/confirmar.css").toExternalForm());
			aceptarButton.setEffect(shadow);
			aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

					try {
						CapturarRegistro.loadCapturaData(guia);

						alert.setHeaderText("Alerta de confirmación");
						alert.setContentText("El registro se ha guardado exitosamente");
						alert.showAndWait();
					} catch (Exception e) {
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
					CapturaWindow capture = new CapturaWindow();
					capture.CapturaStage(stage, usuario);
				}
			});

			Button returnButton = new Button("");
			returnButton.getStyleClass().add("backButton");
			returnButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuWindow menu = new MenuWindow();
					menu.UserMenuStage(stage, usuario);
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
