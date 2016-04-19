package mx.trillas.ControlEnvio.front;

import java.time.LocalDate;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillas.ControlEnvio.persistence.pojosaux.Controlenvio;

public class ReportWindow {

	private final ObservableList<Controlenvio> data = FXCollections.observableArrayList(
			new Controlenvio(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()),
			new Controlenvio(new Integer(1),"Volaris", "Acapulco", "Sofia Montes", "Sistemas", "", new Date()),
			new Controlenvio(new Integer(2),"Fedex", "Zacatecas", "Mario Gutierrez", "Abogacia", "", new Date()),
			new Controlenvio(new Integer(3),"ODM", "Durango", "Eduardo Ayala", "Pagos", "", new Date()));

	public void GenerarReporteStage(Stage stage) {

		try {
			BorderPane border = new BorderPane();
			HBox headerPane = new HBox ();
			VBox pane = new VBox();

			Scene scene = new Scene(border, 850, 500);

			FlowPane labelsPane = new FlowPane(60, 80);
			FlowPane datePane = new FlowPane(20, 40);
			HBox footer = new HBox();

			pane.setAlignment(Pos.CENTER);

			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/generarReporte.css").toExternalForm());

			Button backButton = new Button("Regresar");
			backButton.getStyleClass().add("backButton");
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					MenuWindow menu = new MenuWindow();
					menu.UserMenuStage(stage);
				}
			});

			headerPane.getChildren().add(backButton);
			headerPane.setAlignment(Pos.TOP_LEFT);
			border.setTop(headerPane);

			Label welcomeLabel = new Label("Elija el rango de fechas para generación del reporte");
			welcomeLabel.setId("welcomeLabel");
			pane.getChildren().addAll(welcomeLabel);

			Label textInicio = new Label("Fecha Inicio");
			textInicio.setPadding(new Insets(15, 70, 15, 8));
			textInicio.setId("textInicio");

			Label textFin = new Label("Fecha fin");
			textFin.setPadding(new Insets(15, 8, 15, 70));
			textFin.setId("textFin");

			labelsPane.setAlignment(Pos.CENTER);
			labelsPane.getChildren().addAll(textInicio, textFin);
			pane.getChildren().addAll(labelsPane);

			DatePicker datePickerInicio = new DatePicker();
			datePickerInicio.setOnAction(event -> {
				LocalDate date = datePickerInicio.getValue();
			});
			pane.getChildren().addAll(datePickerInicio);

			Label toCalendar = new Label("a");
			pane.getChildren().add(toCalendar);

			DatePicker datePickerFin = new DatePicker();

			datePickerFin.setOnAction(event -> {
				LocalDate date = datePickerFin.getValue();
				System.out.println("Selected date: " + date);
			});
			pane.getChildren().addAll(datePickerFin);

			datePane.getChildren().addAll(datePickerInicio, toCalendar, datePickerFin);
			datePane.setAlignment(Pos.CENTER);

			Button generarButton = new Button("Generar reporte");
			generarButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					reporteViewStage(stage);
				}
			});

			Button downloadButton = new Button("Descargar");
			downloadButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

				}
			});
			pane.getChildren().addAll(datePane);

			footer.setAlignment(Pos.BOTTOM_CENTER);
			footer.setPadding(new Insets(15, 0, 15 , 0));
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

	public VBox reportView(Stage stage, Scene scene) {
		VBox vbox = new VBox();
		FlowPane flowPane = new FlowPane();

		try {
			StackPane pane = new StackPane();
			pane.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

			Label reporteLabel = new Label("Buscar");
			reporteLabel.getStyleClass().add("labelReport");
			TextField buscador = new TextField();
			buscador.getStyleClass().add("textFieldReport");

			flowPane.getChildren().addAll(reporteLabel, buscador);

			Text cabeceraTabla = new Text("Casa: departamento");

			TableView<Controlenvio> table = new TableView<Controlenvio>();
			table.setEditable(false);

			TableColumn<Controlenvio, String> mensajeraCol = new TableColumn<>("Mensajeria");
			mensajeraCol.setMinWidth(140);
			mensajeraCol.setCellValueFactory(new PropertyValueFactory<>("mensajeria"));

			mensajeraCol.setCellFactory(TextFieldTableCell.<Controlenvio> forTableColumn());
			mensajeraCol.setOnEditCommit((CellEditEvent<Controlenvio, String> t) -> {
				((Controlenvio) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setMensajeria(t.getNewValue());
			});

			TableColumn<Controlenvio, String> origenCol = new TableColumn<>("Origen");
			origenCol.setMinWidth(140);
			origenCol.setCellValueFactory(new PropertyValueFactory<>("origen"));

			origenCol.setCellFactory(TextFieldTableCell.<Controlenvio> forTableColumn());
			origenCol.setOnEditCommit((CellEditEvent<Controlenvio, String> t) -> {
				((Controlenvio) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOrigen(t.getNewValue());
			});

			TableColumn<Controlenvio, String> destinatarioCol = new TableColumn<>("Destinatario");
			destinatarioCol.setMinWidth(140);
			destinatarioCol.setCellValueFactory(new PropertyValueFactory<>("destinatario"));

			destinatarioCol.setCellFactory(TextFieldTableCell.<Controlenvio> forTableColumn());
			destinatarioCol.setOnEditCommit((CellEditEvent<Controlenvio, String> t) -> {
				((Controlenvio) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setDestinatario(t.getNewValue());
			});

			TableColumn<Controlenvio, String> fechaCol = new TableColumn<>("Fecha");
			fechaCol.setMinWidth(160);
			fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

			TableColumn<Controlenvio, String> observacionCol = new TableColumn<>("Observacion");
			observacionCol.setMinWidth(140);
			observacionCol.setCellValueFactory(new PropertyValueFactory<>("Observacion"));

			observacionCol.setCellFactory(TextFieldTableCell.<Controlenvio> forTableColumn());
			observacionCol.setOnEditCommit((CellEditEvent<Controlenvio, String> t) -> {
				((Controlenvio) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setObservacion(t.getNewValue());
			});

			TableColumn<Controlenvio, String> firmaCol = new TableColumn<>("Firma");
			observacionCol.setMinWidth(250);

			table.setItems(data);
			table.getColumns().addAll(mensajeraCol, origenCol, destinatarioCol, observacionCol, fechaCol, firmaCol);

			vbox.setSpacing(10);
			vbox.setPadding(new Insets(4, 4, 4, 4));
			vbox.getChildren().addAll(cabeceraTabla);
			vbox.getChildren().addAll(table);
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vbox;
	}

	public void reporteViewStage(Stage stage) {

		try {
			VBox paneVbox = new VBox();
			FlowPane buttonsPane = new FlowPane();

			Scene scene = new Scene(paneVbox, 900, 500);

			paneVbox.setAlignment(Pos.CENTER);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("style/report.css").toExternalForm());

			VBox vbox = reportView(stage, scene);

			Button printButton = new Button("Imprimir");
			printButton.setOnAction(new EventHandler<ActionEvent>() {
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
					GenerarReporteStage(stage);
				}
			});
			buttonsPane.setAlignment(Pos.BASELINE_CENTER);
			buttonsPane.getChildren().addAll(printButton, cancelButton);
			paneVbox.getChildren().addAll(vbox, buttonsPane);

			stage.setScene(scene);
			stage.setTitle("Control de paquetería -Generar reporte");
			stage.setResizable(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
