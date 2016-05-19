package mx.trillas.ControlEnvio.front;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
import mx.trillas.ControlEnvio.persistence.pojosaux.Controlenvio;
import mx.trillas.ControlEnvio.persistence.pojosaux.VboxTable;

public class ReportWindow {

	private static Logger logger = Logger.getLogger(ReportWindow.class);

	private static GuiaDAO guiaDAO = new GuiaDAODBImpl();
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAODBImpl();
	private static DestinatarioDAO destinatarioDAO = new DestinatarioDAODBImpl();

	TableView<Guia> table = null;

	Date dateInicio = new Date();
	Date dateFin = new Date();

	int countTableList = 0;
	
	Button printButton = new Button("Imprimir");
	Button cancelButton = new Button("Cancelar");

	public void GenerarReporteStage(Stage stage, Usuario usuario) {

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	Platform.exit();
				System.exit(0);
		    }
		});
		
		Alert alertWarn = new Alert(AlertType.WARNING, "content text");
		alertWarn.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
		alertWarn.setTitle("Alerta generando reporte");

		try {
			BorderPane border = new BorderPane();
			HBox headerPane = new HBox();
			VBox pane = new VBox();

//			Scene scene = new Scene(border, 970, 500);
			Scene scene = new Scene(border, 950, 500);
			
			FlowPane labelsPane = new FlowPane(45, 80);
			FlowPane datePane = new FlowPane(20, 100);
			HBox footer = new HBox();

			List<Departamento> deptoList = departamentoDAO.getDepartamentoList();
			
			pane.setAlignment(Pos.CENTER);

			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/generarReporte.css").toExternalForm());

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
			dateFinLabel.setPadding(new Insets(25, 65, 15, 65));
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
			deptoCombo.getItems().add("Otro");
			deptoCombo.getItems().add("Todos");

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
					
					Boolean flagOtroDeptoRepet = false;
					if (deptoCombo.getValue() != null && deptoCombo.getValue().toString().equals("Otro") && !(otroDeptoField.getText().equals(""))) {
						for (Departamento element : deptoList) {
							if (element.getNombre().equals(otroDeptoField.getText())){
								flagOtroDeptoRepet = true;
							}
						}
					}
					
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
					} else if (flagOtroDeptoRepet == true) {
								alertWarn.setHeaderText("Error en el manejo de datos");
								alertWarn.setContentText("Un departamento con el mismo nombre ya existe en el menu de departamentos");
								alertWarn.showAndWait();
					} else {
						if (deptoCombo.getValue().toString().equals("Todos")) {
							checkReport(stage, usuario, dateInicio, dateFin, null, false, true, otroDeptoField.getText());
						} else if (deptoCombo.getValue().toString().equals("Otro")) {
							checkReport(stage, usuario, dateInicio, dateFin, otroDeptoField.getText(), true, false,	otroDeptoField.getText());
						} else {
							checkReport(stage, usuario, dateInicio, dateFin, deptoCombo.getValue().toString(), false, false, otroDeptoField.getText());
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
			logger.error(e.getMessage());
		}
	}

	public void checkReport(Stage stage, Usuario usuario, Date fechaInicio, Date fechaFin, String nombreDepartamento, Boolean flagOtro, Boolean flagTodos, String otroDeptoField) {

		Alert alert = new Alert(AlertType.WARNING, "content text");
		alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
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

							List<Destinatario> destinatariosList = destinatarioDAO.getDestinatarioFromDeptoList(departamento);

							dataFullList = new ArrayList<Guia>();

							for (Destinatario element : destinatariosList) {
								dataList = guiaDAO.getGuiaListByDateyDestinatario(fechaInicio, fechaFin, element);
								dataFullList.addAll(dataList);
							}
						} else {
							logger.error("No existen registros con el departamento indicado. Intente con otra consulta");
							alert.setHeaderText("Sin resultados");
							alert.setContentText("No existen registros con el departamento indicado. Intente con otra consulta");
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
				logger.error("El rango de fechas no arroja ningún registro. Intente con otro rango u otro departamento.");
				alert.setHeaderText("Sin resultados");
				alert.setContentText("El rango de fechas no arroja ningún registro. Intente con otro rango u otro departamento.");
				alert.showAndWait();
			} else {
				for (Guia element : dataFullList) {
					datos.add(element);
				}
				reporteViewStage(stage, usuario, fechaInicio, fechaFin, nombreDepartamento, flagTodos, otroDeptoField, datos);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void reporteViewStage(Stage stage, Usuario usuario, Date fechaInicio, Date fechaFin, String nombreDepartamento, Boolean flagTodos, String otroDeptoField, List<Guia> dataList) {

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        Platform.exit();
		    }
		});
		
		VBox paneVbox = new VBox();
		FlowPane buttonsPane = new FlowPane();
		buttonsPane.setVisible(true);
		
		Text confirmText = new Text("");
		confirmText.setId("confirmText");
		confirmText.setText("Se encontraron " + dataList.size() + " registros. ¿Desea mandar a imprimir?\n\n\n");
		paneVbox.getChildren().add(confirmText);
		
		HashMap<Integer, ObservableList<Guia>> hashList = new HashMap<Integer, ObservableList<Guia>>();
	
		Scene scene = new Scene(paneVbox, 950, 500);
//		Scene scene = new Scene(paneVbox, 1110, 700);
		
		paneVbox.setAlignment(Pos.CENTER);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());
		printButton.setVisible(true);
		cancelButton.setVisible(true);
		
		try {

			int count = 0;
			int countRows = 0;
			int counterList = 0;

			List<Controlenvio> ControlenvioList = ReportBackend.guiaToControlenvio(dataList);
			Collections.sort(ControlenvioList);
			
			HashMap<Integer, ArrayList<Guia>> hashMap = ReportBackend.getDeptoFullMap(ControlenvioList);
			HashMap<Integer, VboxTable> vboxTableMap = new HashMap<Integer, VboxTable>();
			/*
			for (int i = 0;  i < hashMap.size();  i++) {
				List<Guia> lista = hashMap.get(i);
				
				for (Guia element : lista) {
					String depto = null;
					
					if (element.getDestinatario() == null) {
						depto = element.getOtrodepartamento();
					} else {
						depto = element.getDestinatario().getDepartamento().getNombre();
					}
					
					System.out.println("id=" + element.getId() + "  number=" + element.getNumero() + "  departamento=" + depto);
				}
			}
			System.out.println("");
			
			
			List<Guia> dataSorted = new ArrayList<Guia>();

			for (Controlenvio controlenvio : ControlenvioList) {
				dataSorted.add(controlenvio.getGuia());
			}
			*/
			ObservableList<Guia> datos = null;

			for (int i = 0; i < hashMap.size(); i ++) {

				List<Guia> guiaList = hashMap.get(i);

				for (Guia element : guiaList) {
					System.out.println("ListaNo= "+ i +"  id=" + element.getId() + "  element=" + element.getNumero() + "  guiaList.size=" +guiaList.size()+"  hashMap.size()=" + hashMap.size());
			
					if (datos == null) {
						datos = FXCollections.observableArrayList();
					}
					
					count++;
					countRows++;
	
					datos.add(element);
	
					if (count == 22) {
						hashList.put(new Integer(counterList), datos);
						counterList++;
	
						count = 0;	
						datos = null;
					} 
					else if (countRows == guiaList.size()) {
						hashList.put(new Integer(counterList), datos);
						count = 0;
						countRows = 0;
						
						counterList++;
						datos = null;
					}
				}
			}

			for (int i = 0; i < hashList.size(); i++) {
				System.out.println("ListaNo="  + i  + "  hashList.size()="+hashList.size());
				if (generarTable(hashList.get(i), nombreDepartamento, flagTodos) instanceof VBox) {
				
					List<Guia> guiaList = hashList.get(i);
					
					String depto = null;
					for (Guia element : guiaList) {
						
						if (element.getDestinatario() == null) {
							depto = element.getOtrodepartamento();
						} else {
							depto = element.getDestinatario().getDepartamento().getNombre();
						}
						break;
					}
					
					VboxTable vboxObj = new VboxTable(i, guiaList.size(), depto);
					
					if (nombreDepartamento == null){
						nombreDepartamento = depto;
					}
					vboxObj.setVbox((VBox) generarTable(hashList.get(i), depto, false));
					
					VBox tables = vboxObj.getVbox();
//					VBox tables = (VBox) generarTable(hashList.get(i),nombreDepartamento, flagTodos, otroDeptoField);
					
					tables.setVisible(false);
					
					paneVbox.getChildren().add(new VBox());
					
					vboxTableMap.put(i, vboxObj);
					countTableList++;
				}
			}
		System.out.println("countTableList= " + countTableList);
		/*
		for (int i = 0; i < hashList.size(); i++) {
			List<Guia> guiaList = hashList.get(i);
			for (Guia element : guiaList) {
				System.out.println("i=" + i + "  hashList element: " + element);
			}
			System.out.println("");
		}
		*/
		printButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

//				confirmText.setVisible(false);
				confirmText.setText("Procesando... Por favor espere.");
				buttonsPane.setVisible(false);
				
				try {
					for (int i = 0; i < countTableList + 1; i++) {

						VboxTable vboxTableObj = vboxTableMap.get(i);
						
						if (vboxTableObj != null) {
						
						VBox objVbox  = vboxTableObj.getVbox();
						int objRowsNumber = vboxTableObj.getRowsNumber();
						
						
							if (objRowsNumber < 20) {

							}	
							else { 
								objVbox.setTranslateY(-335);
							}
							
							objVbox.setVisible(true);
							ReportBackend.printForTable(objVbox);

							paneVbox.getChildren().set(i+1, objVbox);
							objVbox.setVisible(false);
						} 
					}
			        
//					countTableList = 0;
					confirmText.setText("Impresión concluida");
					
					Alert alertInfo = new Alert(AlertType.INFORMATION, "content text");
					alertInfo.getDialogPane().getChildren().stream().filter(nodeAlert -> nodeAlert instanceof Label).forEach(nodeAlert -> ((Label) nodeAlert).setMinHeight(Region.USE_PREF_SIZE));
					alertInfo.setTitle("información");
					
					alertInfo.setHeaderText(null);
					alertInfo.setContentText("La Impresión ha finalizado correctamente");
					alertInfo.showAndWait();
											
					GenerarReporteStage(stage, usuario);
				} catch (Exception e) {
					logger.error(e.getMessage());
					e.printStackTrace();
					
					confirmText.setText("Error al imprimir");
					Alert alertWarn = new Alert(AlertType.WARNING, "content text");
					alertWarn.getDialogPane().getChildren().stream().filter(nodeAlert -> nodeAlert instanceof Label).forEach(nodeAlert -> ((Label) nodeAlert).setMinHeight(Region.USE_PREF_SIZE));
					alertWarn.setTitle("Alerta al imprimir");
					
					alertWarn.setHeaderText(null);
					alertWarn.setContentText("La Impresión no pudo concluir satisfactoriamente. Verifique su conexión a impresora");
					alertWarn.showAndWait();
					
					GenerarReporteStage(stage, usuario);
				}
			}
		});
			
		cancelButton = new Button("Cancelar");
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
		
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	    
	public VBox generarTable(ObservableList<Guia> datos, String nombreDepartamento,	Boolean flagTodos) {

		VBox vbox = new VBox();
//		TableView<Guia> table = null;

		table = new TableView<Guia>();
		table.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());
		table.setEditable(false);
		
		table.getSelectionModel().clearSelection();
		
		FlowPane cabeceraFlow = new FlowPane(150, 5);

		Text cabeceraText = new Text("Correspondencia");
		cabeceraText.getStyleClass().add("cabeceraClass");
		cabeceraFlow.getChildren().addAll(cabeceraText);

//		if (nombreDepartamento != null) {
			Text nombreDepartamentoText = new Text(nombreDepartamento);
			nombreDepartamentoText.getStyleClass().add("cabeceraClass");
			cabeceraFlow.getChildren().addAll(nombreDepartamentoText);
//		}

		try {
			TableColumn<Guia, String> numeroCol = new TableColumn<>("Numero guia");
			numeroCol.setMinWidth(190);
			numeroCol.setSortable(false);
			numeroCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
			numeroCol.setCellFactory(TextFieldTableCell.<Guia> forTableColumn());
			numeroCol.setOnEditCommit((CellEditEvent<Guia, String> t) -> {
				((Guia) t.getTableView().getItems().get(t.getTablePosition().getRow())).getMensajeria()
						.setNombre(t.getNewValue());
			});

			TableColumn<Guia, String> mensajeraCol = new TableColumn<>("Mensajeria");
			mensajeraCol.setMinWidth(130);
			mensajeraCol.setSortable(false);
			mensajeraCol.setCellValueFactory(new Callback<CellDataFeatures<Guia, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Guia, String> param) {
					SimpleStringProperty ssp = null;
					if (param.getValue().getMensajeria() != null) {
						ssp = new SimpleStringProperty(param.getValue().getMensajeria().getNombre());
						return ssp;
					} else {
						return new SimpleStringProperty("Personalmente");
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
			origenCol.setSortable(false);
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
			destinatarioCol.setMinWidth(190);
			destinatarioCol.setSortable(false);
			destinatarioCol.setCellValueFactory(new Callback<CellDataFeatures<Guia, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Guia, String> param) {
					SimpleStringProperty ssp = null;
					if (param.getValue().getDestinatario() != null) {

						String output = param.getValue().getDestinatario().getNombre().toString();
						String outputDepto = param.getValue().getDestinatario().getDepartamento().getNombre()
								.toString();

						if (flagTodos == false) {
							ssp = new SimpleStringProperty(
									output.substring(0, 1).toUpperCase() + output.substring(1));
						} else {
							ssp = new SimpleStringProperty(output.substring(0, 1).toUpperCase()
									+ output.substring(1) + "/" + outputDepto);
						}
						return ssp;
					} else {
						return new SimpleStringProperty(param.getValue().getOtrodestinatario() + "/"
								+ param.getValue().getOtrodepartamento());
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
			fechaCol.setSortable(false);
			fechaCol.setCellValueFactory(new Callback<CellDataFeatures<Guia, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Guia, String> param) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
					return new SimpleStringProperty(formatter.format(param.getValue().getFecha().getTime()));
				}
			});

			TableColumn<Guia, String> observacionCol = new TableColumn<>("Observacion");
			observacionCol.setMinWidth(160);
			observacionCol.setSortable(false);
			observacionCol.setCellValueFactory(new PropertyValueFactory<>("observacion"));
			observacionCol.setCellFactory(TextFieldTableCell.<Guia> forTableColumn());
			observacionCol.setOnEditCommit((CellEditEvent<Guia, String> t) -> {
				((Guia) t.getTableView().getItems().get(t.getTablePosition().getRow())).setObservacion(t.getNewValue());
			});

			TableColumn<Guia, String> firmaCol = new TableColumn<>("Firma");
			firmaCol.setMinWidth(150);
			firmaCol.setSortable(false);

			table.focusedProperty().addListener((a,b,c) -> {
				table.getSelectionModel().clearSelection();
			});
			
			table.setItems(datos);
			table.getColumns().addAll(numeroCol, mensajeraCol, origenCol, destinatarioCol, observacionCol, fechaCol,
					firmaCol);

			vbox.setSpacing(10);
			vbox.setPadding(new Insets(4, 4, 4, 4));
			vbox.getChildren().addAll(cabeceraFlow);
			vbox.getChildren().addAll(table);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vbox;
	}
}