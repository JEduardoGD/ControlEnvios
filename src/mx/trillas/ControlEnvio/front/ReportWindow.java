package mx.trillas.ControlEnvio.front;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import mx.trillas.ControlEnvio.backend.DepartamentoBackend;
import mx.trillas.ControlEnvio.backend.ReportBackend;
import mx.trillas.ControlEnvio.persistence.dao.DepartamentoDAO;
import mx.trillas.ControlEnvio.persistence.dao.DestinatarioDAO;
import mx.trillas.ControlEnvio.persistence.dao.GuiaDAO;
import mx.trillas.ControlEnvio.persistence.impl.DepartamentoDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.DestinatarioDAODBImpl;
import mx.trillas.ControlEnvio.persistence.impl.GuiaDAODBImpl;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;
import mx.trillas.ControlEnvio.persistence.pojos.Destinatario;
import mx.trillas.ControlEnvio.persistence.pojos.Guia;
import mx.trillas.ControlEnvio.persistence.pojos.Usuario;

public class ReportWindow {

	private static Logger logger = Logger.getLogger(ReportWindow.class);

	private static GuiaDAO guiaDAO = new GuiaDAODBImpl();
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAODBImpl();
	private static DestinatarioDAO destinatarioDAO = new DestinatarioDAODBImpl();

	ObservableList<Guia> datos = FXCollections.observableArrayList();
	ObservableList<Guia> datosFull = FXCollections.observableArrayList();
	private static Date dateInicio = new Date();
	private static Date dateFin = new Date();
	VBox table1 = new VBox();

	public void GenerarReporteStage(Stage stage, Usuario usuario) {

		Alert alertWarn = new Alert(AlertType.WARNING);
		alertWarn.setTitle("Alerta generando reporte");

		try {
			BorderPane border = new BorderPane();
			HBox headerPane = new HBox();
			VBox pane = new VBox();

			Scene scene = new Scene(border, 960, 500);

			FlowPane labelsPane = new FlowPane(60, 80);
			FlowPane datePane = new FlowPane(20, 100);
			HBox footer = new HBox();

			pane.setAlignment(Pos.CENTER);

			scene.getStylesheets()
					.add(getClass().getClassLoader().getResource("style/generarReporte.css").toExternalForm());

			Button backButton = new Button("Regresar");
			backButton.setCursor(Cursor.HAND);
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					MenuWindow menu = new MenuWindow();
					menu.UserMenuStage(stage, usuario);
				}
			});

			headerPane.getChildren().add(backButton);
			headerPane.setAlignment(Pos.TOP_LEFT);
			border.setTop(headerPane);

			Label welcomeLabel = new Label("Elija el rango de fechas para generación del reporte");
			welcomeLabel.setId("welcomeLabel");
			pane.getChildren().addAll(welcomeLabel);

			Label dateInicioLabel = new Label("Fecha Inicio");
			dateInicioLabel.setPadding(new Insets(25, 25, 15, 25));
			dateInicioLabel.getStyleClass().add("reportLabel");

			Label dateFinLabel = new Label("Fecha fin");
			dateFinLabel.setPadding(new Insets(25, 25, 15, 25));
			dateFinLabel.getStyleClass().add("reportLabel");

			Label deptoLabel = new Label("Departamento");
			deptoLabel.setPadding(new Insets(25, 25, 15, 25));
			deptoLabel.getStyleClass().add("reportLabel");

			Label otroDepartamentoLabel = new Label("Otro Departamento");
			otroDepartamentoLabel.setPadding(new Insets(25, 1, 15, 25));
			otroDepartamentoLabel.getStyleClass().add("reportLabel");

			labelsPane.setAlignment(Pos.CENTER);
			labelsPane.getChildren().addAll(dateInicioLabel, dateFinLabel, deptoLabel, otroDepartamentoLabel);
			pane.getChildren().addAll(labelsPane);

			DatePicker datePickerInicio = new DatePicker();
			DatePicker datePickerFin = new DatePicker();

			datePickerInicio.setEditable(false);
			datePickerInicio.setOnAction(event -> {
				LocalDate localDate = datePickerInicio.getValue();
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				dateInicio = Date.from(instant);
				System.out.println("Selected date: " + dateInicio);
			});
			pane.getChildren().addAll(datePickerInicio);

			datePickerFin.setEditable(false);
			datePickerFin.setOnAction(event -> {

				LocalDate localDate = datePickerFin.getValue();
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));

				dateFin = Date.from(instant);
				dateFin = ReportBackend.getCalendarOnHour(dateFin);

				System.out.println("Selected date: " + dateFin);
			});
			pane.getChildren().addAll(datePickerFin);

			VBox container = new VBox();

			TextField otroDeptoField = new TextField();
			otroDeptoField.setPromptText("Otro departamento");
			otroDeptoField.setDisable(true);

			ComboBox<Object> deptoCombo = DepartamentoBackend.getDeptosListCombo();
			deptoCombo.setPromptText("Seleccione una opcion...");
			deptoCombo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						if (deptoCombo != null && deptoCombo.getValue().equals("Otro")) {
							otroDeptoField.setDisable(false);
						} else {
							otroDeptoField.setDisable(true);
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			});
			deptoCombo.getItems().add("Todos");
			deptoCombo.getItems().add("Otro");

			container.getChildren().addAll(deptoCombo, otroDeptoField);
			datePane.getChildren().addAll(datePickerInicio, datePickerFin, deptoCombo, otroDeptoField);
			datePane.setAlignment(Pos.CENTER);

			FlowPane deptoPane = new FlowPane();
			deptoPane.setAlignment(Pos.CENTER);

			Button generarButton = new Button("Generar reporte");
			generarButton.setCursor(Cursor.HAND);
			generarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (datePickerInicio.getValue() == null) {
						alertWarn.setHeaderText("Error en el manejo de datos");
						alertWarn.setContentText("Seleccione la fecha de inicio");
						alertWarn.showAndWait();
					} else if (datePickerFin.getValue() == null) {
						alertWarn.setHeaderText("Error en el manejo de datos");
						alertWarn.setContentText("Seleccione la fecha fin");
						alertWarn.showAndWait();
					} else if (deptoCombo.getValue() == null) {
						alertWarn.setHeaderText("Error en el manejo de datos");
						alertWarn.setContentText("Seleccione un departamento");
						alertWarn.showAndWait();
					} else if (deptoCombo.getValue().toString().equals("Otro") && otroDeptoField.getText().equals("")) {
						alertWarn.setHeaderText("Error en el manejo de datos");
						alertWarn.setContentText("Ingrese el nombre del otro departamento");
						alertWarn.showAndWait();
					} else {
						if (deptoCombo.getValue().toString().equals("Todos")) {
							checkReport(stage, usuario, dateInicio, dateFin, null, false);
						} else if (deptoCombo.getValue().toString().equals("Otro")) {
							checkReport(stage, usuario, dateInicio, dateFin, otroDeptoField.getText(), true);
						} else {
							checkReport(stage, usuario, dateInicio, dateFin, deptoCombo.getValue().toString(), false);
						}
					}
				}
			});

			pane.getChildren().addAll(datePane);

			footer.setAlignment(Pos.BOTTOM_CENTER);
			footer.setPadding(new Insets(15, 0, 15, 0));
			footer.getChildren().addAll(generarButton);

			border.setCenter(pane);
			border.setBottom(footer);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Generar reporte");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkReport(Stage stage, Usuario usuario, Date fechaInicio, Date fechaFin, String nombreDepartamento,
			Boolean flagOtro) {

		Alert alert = new Alert(AlertType.WARNING, "content text");
		alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
				.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
		alert.setTitle("Alerta al generar reporte");

		try {
			ObservableList<Guia> datos = FXCollections.observableArrayList();
			List<Guia> dataList = null;
			List<Guia> dataFullList = null;

			if (nombreDepartamento == null) {
				try {
					dataFullList = guiaDAO.getGuiaListByDate(fechaInicio, fechaFin);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			} else {
				try {
					Departamento departamento = departamentoDAO.getDepartamento(nombreDepartamento);
					if (flagOtro == false) {
						if (departamento != null) {

							List<Destinatario> destinatariosList = destinatarioDAO
									.getDestinatarioFromDeptoList(departamento);

							dataFullList = new ArrayList<Guia>();

							for (Destinatario element : destinatariosList) {
								dataList = guiaDAO.getGuiaListByDateyDestinatario(fechaInicio, fechaFin, element);
								dataFullList.addAll(dataList);
							}
						} else {
							logger.error(
									"No existen registros con el departamento indicado. Intente con otra consulta");
							alert.setHeaderText("Sin resultados");
							alert.setContentText(
									"No existen registros con el departamento indicado. Intente con otra consulta");
							alert.showAndWait();
						}
					} else {
						dataFullList = guiaDAO.getGuiaListByDateOtroDepto(fechaInicio, fechaFin, nombreDepartamento);
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}

			if (dataFullList.isEmpty()) {
				logger.error(
						"El rango de fechas no arroja ningún registro. Intente con otro rango u otro departamento.");
				alert.setHeaderText("Sin resultados");
				alert.setContentText(
						"El rango de fechas no arroja ningún registro. Intente con otro rango u otro departamento.");
				alert.showAndWait();
			} else {

				for (Guia element : dataFullList) {
					datos.add(element);
				}
				reporteViewStage(stage, usuario, fechaInicio, fechaFin, datos);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reporteViewStage(Stage stage, Usuario usuario, Date fechaInicio, Date fechaFin, List<Guia> dataList) {

		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();
			HashMap<Integer, ObservableList<Guia>> hashList = new HashMap<Integer, ObservableList<Guia>>();

			Scene scene = new Scene(paneVbox, 1030, 560);
			VBox vboxTable = new VBox();

			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

			int count = 0;
			int countRows = 0;
			int counterList = 0;

			ObservableList<Guia> datos = null;

			for (Guia element : dataList) {
				if (datos == null)
					datos = FXCollections.observableArrayList();
					count++;
					countRows++;
					datos.add(element);

				if (count == 21) {

					hashList.put(new Integer(counterList), datos);
					counterList++;

					count = 0;
					datos = null;
				} else if (countRows == dataList.size()) {
					hashList.put(new Integer(counterList), datos);
					counterList++;
				}
			}

			for (int i = 0; i < hashList.size(); i++) {

				vboxTable.getChildren().add((VBox) generarTable(hashList.get(i)));
			}

			// vboxTable.getChildren().add(table1);

			Button printButton = new Button("Imprimir");
			printButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {

//						for (int i = 0; i < hashList.size(); i++) {
//							if (vboxTable.getChildren().get(i) != null) {

//								if (vboxTable.getChildren().get(i) instanceof VBox) {

									ReportBackend.printForTable((VBox) vboxTable.getChildren().get(0));
//								}
//							}
//						}
						// ReportBackend.printForTable(table);
						GenerarReporteStage(stage, usuario);

					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			});

			Button cancelButton = new Button("Cancelar");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					GenerarReporteStage(stage, usuario);
				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(printButton, cancelButton);
			paneVbox.getChildren().addAll(buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería - Generar reporte");
			stage.setResizable(true);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VBox generarTable(ObservableList<Guia> datos) {

		VBox vbox = new VBox();
		TableView<Guia> table = null;

//		scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

		table = new TableView<Guia>();
		table.setEditable(false);

		Text cabeceraTabla = new Text("Casa: departamento");

		try {
			TableColumn<Guia, String> numeroCol = new TableColumn<>("Numero guia");
			numeroCol.setMinWidth(190);
			numeroCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
			numeroCol.setCellFactory(TextFieldTableCell.<Guia> forTableColumn());
			numeroCol.setOnEditCommit((CellEditEvent<Guia, String> t) -> {
				((Guia) t.getTableView().getItems().get(t.getTablePosition().getRow())).getMensajeria()
						.setNombre(t.getNewValue());
			});

			TableColumn<Guia, String> mensajeraCol = new TableColumn<>("Mensajeria");
			mensajeraCol.setMinWidth(120);
			mensajeraCol.setCellValueFactory(new Callback<CellDataFeatures<Guia, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Guia, String> param) {
					SimpleStringProperty ssp = null;
					if (param.getValue().getMensajeria() != null) {
						ssp = new SimpleStringProperty(param.getValue().getMensajeria().getNombre());
						return ssp;
					} else {
						return new SimpleStringProperty("");
					}
				}
			});
			mensajeraCol.setCellFactory(TextFieldTableCell.<Guia> forTableColumn());
			mensajeraCol.setOnEditCommit((CellEditEvent<Guia, String> t) -> {
				((Guia) t.getTableView().getItems().get(t.getTablePosition().getRow())).getMensajeria()
						.setNombre(t.getNewValue());
			});

			TableColumn<Guia, String> origenCol = new TableColumn<>("Origen");
			origenCol.setMinWidth(120);
			origenCol.setCellValueFactory(new Callback<CellDataFeatures<Guia, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Guia, String> param) {
					SimpleStringProperty ssp = null;
					if (param.getValue().getOrigen() != null) {
						ssp = new SimpleStringProperty(param.getValue().getOrigen().getNombre());
						return ssp;
					} else {
						return new SimpleStringProperty(param.getValue().getOtroorigen());
					}
				}
			});
			origenCol.setCellFactory(TextFieldTableCell.<Guia> forTableColumn());
			origenCol.setOnEditCommit((CellEditEvent<Guia, String> t) -> {
				((Guia) t.getTableView().getItems().get(t.getTablePosition().getRow())).getOrigen()
						.setNombre(t.getNewValue());
			});

			TableColumn<Guia, String> destinatarioCol = new TableColumn<>("Destinatario");
			destinatarioCol.setMinWidth(120);
			destinatarioCol
					.setCellValueFactory(new Callback<CellDataFeatures<Guia, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Guia, String> param) {
							SimpleStringProperty ssp = null;
							if (param.getValue().getDestinatario() != null) {
								ssp = new SimpleStringProperty(param.getValue().getDestinatario().getNombre());
								return ssp;
							} else {
								return new SimpleStringProperty(param.getValue().getOtrodestinatario());
							}
						}
					});
			destinatarioCol.setCellFactory(TextFieldTableCell.<Guia> forTableColumn());
			destinatarioCol.setOnEditCommit((CellEditEvent<Guia, String> t) -> {
				((Guia) t.getTableView().getItems().get(t.getTablePosition().getRow())).getDestinatario()
						.setNombre(t.getNewValue());
			});

			TableColumn<Guia, String> fechaCol = new TableColumn<>("Fecha");
			fechaCol.setMinWidth(160);
			fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

			TableColumn<Guia, String> observacionCol = new TableColumn<>("Observacion");
			observacionCol.setMinWidth(160);
			observacionCol.setCellValueFactory(new PropertyValueFactory<>("observacion"));
			observacionCol.setCellFactory(TextFieldTableCell.<Guia> forTableColumn());
			observacionCol.setOnEditCommit((CellEditEvent<Guia, String> t) -> {
				((Guia) t.getTableView().getItems().get(t.getTablePosition().getRow())).setObservacion(t.getNewValue());
			});

			TableColumn<Guia, String> firmaCol = new TableColumn<>("Firma");
			firmaCol.setMinWidth(150);

			table.setItems(datos);
			table.getColumns().addAll(numeroCol, mensajeraCol, origenCol, destinatarioCol, observacionCol, fechaCol,
					firmaCol);

			vbox.setSpacing(10);
			vbox.setPadding(new Insets(4, 4, 4, 4));
			vbox.getChildren().addAll(cabeceraTabla);
			vbox.getChildren().addAll(table);
//			stage.setScene(scene);
//			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vbox;
	}
}