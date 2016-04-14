package mx.trilas.ControlEnvio.front;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.trillasControlEnvio.persistence.pojos.Reporte;

public class Report {

	private final ObservableList<Reporte> data = FXCollections.observableArrayList(
			new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()),
			new Reporte(new Integer(1),"Volaris", "Acapulco", "Sofia Montes", "Sistemas", "", new Date()),
			new Reporte(new Integer(2),"Fedex", "Zacatecas", "Mario Gutierrez", "Abogacia", "", new Date()),
			new Reporte(new Integer(3),"ODM", "Durango", "Eduardo Ayala", "Pagos", "", new Date()));

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
			
			TableView<Reporte> table = new TableView<Reporte>();
			table.setEditable(false);

			TableColumn<Reporte, String> mensajeraCol = new TableColumn<>("Mensajeria");
			mensajeraCol.setMinWidth(140);
			mensajeraCol.setCellValueFactory(new PropertyValueFactory<>("mensajeria"));

			mensajeraCol.setCellFactory(TextFieldTableCell.<Reporte> forTableColumn());
			mensajeraCol.setOnEditCommit((CellEditEvent<Reporte, String> t) -> {
				((Reporte) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setMensajeria(t.getNewValue());
			});

			TableColumn<Reporte, String> origenCol = new TableColumn<>("Origen");
			origenCol.setMinWidth(140);
			origenCol.setCellValueFactory(new PropertyValueFactory<>("origen"));

			origenCol.setCellFactory(TextFieldTableCell.<Reporte> forTableColumn());
			origenCol.setOnEditCommit((CellEditEvent<Reporte, String> t) -> {
				((Reporte) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOrigen(t.getNewValue());
			});

			TableColumn<Reporte, String> destinatarioCol = new TableColumn<>("Destinatario");
			destinatarioCol.setMinWidth(140);
			destinatarioCol.setCellValueFactory(new PropertyValueFactory<>("destinatario"));

			destinatarioCol.setCellFactory(TextFieldTableCell.<Reporte> forTableColumn());
			destinatarioCol.setOnEditCommit((CellEditEvent<Reporte, String> t) -> {
				((Reporte) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setDestinatario(t.getNewValue());
			});

			TableColumn<Reporte, String> fechaCol = new TableColumn<>("Fecha");
			fechaCol.setMinWidth(160);
			fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

			TableColumn<Reporte, String> observacionCol = new TableColumn<>("Observacion");
			observacionCol.setMinWidth(140);
			observacionCol.setCellValueFactory(new PropertyValueFactory<>("Observacion"));

			observacionCol.setCellFactory(TextFieldTableCell.<Reporte> forTableColumn());
			observacionCol.setOnEditCommit((CellEditEvent<Reporte, String> t) -> {
				((Reporte) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setObservacion(t.getNewValue());
			});
			
			TableColumn<Reporte, String> firmaCol = new TableColumn<>("Firma");
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
//			 Scene scene = new Scene(new Group(), 900, 500);
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
