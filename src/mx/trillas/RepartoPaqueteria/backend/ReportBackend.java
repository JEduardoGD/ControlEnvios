package mx.trillas.RepartoPaqueteria.backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.transform.Scale;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Guia;
import mx.trillas.RepartoPaqueteria.persistence.pojosaux.Repartopaqueteria;

public class ReportBackend {

	private static Logger logger = Logger.getLogger(ReportBackend.class);
	
	private static double SCALEX = 0.6612612612612613;
	private static double SCALEY = 1.4580838323353293;
	
	public static Date getCalendarOnHour(Date date){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR, 23);
     
        date = calendar.getTime();
		return date;
	}
	
	public static void printForTable(VBox table) throws Exception {

		Printer printer = null;
		PageLayout pageLayout = null;

		try {
			printer = Printer.getDefaultPrinter();

			if (printer != null) {
				pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

				table.getTransforms().add(new Scale(SCALEX, SCALEY));
				PrinterJob job = PrinterJob.createPrinterJob(printer);
				
				job.getJobSettings().setPageLayout(pageLayout);
				
				if (job != null) {
					boolean success = job.printPage(table);
					if (success) {
						job.endJob();
					} else {
						// put here any terrible message
					}
				}
			} else {
				logger.error("No existe impresora predeterminada");
				
				Alert alertWarn = new Alert(AlertType.WARNING, "content text");
				alertWarn.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
				alertWarn.setTitle("Alerta al imprimir");
				
				alertWarn.setHeaderText(null);
				alertWarn.setContentText("No existe impresora predeterminada. Favor de configurar conexion de impresora en su equipo.");
				alertWarn.showAndWait();
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static List<Repartopaqueteria> guiaToRepartopaqueteria(List<Guia> guiaList) {
		
		String departamento = null;
		List<Repartopaqueteria> repartopaqueteriaList = new ArrayList<Repartopaqueteria>();
		
		for ( Guia guia : guiaList ) {
			Repartopaqueteria repartopaqueteria = new Repartopaqueteria();
			
			if (guia.getDestinatario() != null){
				departamento = guia.getDestinatario().getDepartamento().getNombre();
			}
			else 
				departamento = guia.getOtrodepartamento();
			
			departamento = departamento.toLowerCase();
			
			repartopaqueteria.setGuia(guia);
			repartopaqueteria.setDepartamento(departamento);
			
			repartopaqueteriaList.add(repartopaqueteria);
		}
		return repartopaqueteriaList;
	}

	public static HashMap<Integer, ArrayList<Guia>> getDeptoFullMap (List<Repartopaqueteria> repartopaqueteriaList) {
	
		HashMap<Integer, ArrayList<Guia>> hashMap = new HashMap<Integer, ArrayList<Guia>>();
		
		String tempDepartamento = null;
		
		int i = 0;
		hashMap.put(i, new ArrayList<Guia>());
		
		for (Repartopaqueteria controlenvio : repartopaqueteriaList) {
			
			if (tempDepartamento == null)
				tempDepartamento = controlenvio.getDepartamento();
				
			List<Guia> guiaList = hashMap.get(i);
			String departamento = controlenvio.getDepartamento();
				
			if (tempDepartamento.equals(departamento)){
				guiaList.add(controlenvio.getGuia());
			} else {
				i++;
				hashMap.put(i, new ArrayList<Guia>());
				tempDepartamento = controlenvio.getDepartamento();
				List<Guia> tempList = hashMap.get(i);
				tempList.add(controlenvio.getGuia());
			}
		}
		return hashMap;
	}
}
