package mx.trillas.ControlEnvio.backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import javafx.collections.ObservableList;
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
import mx.trillas.ControlEnvio.persistence.pojos.Guia;
import mx.trillas.ControlEnvio.persistence.pojosaux.Controlenvio;

public class ReportBackend {

	private static Logger logger = Logger.getLogger(ReportBackend.class);

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
		double scaleX = 0;
		double scaleY = 0;

		try {
			printer = Printer.getDefaultPrinter();

			if (printer != null) {
				pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

				scaleX = pageLayout.getPrintableWidth() / table.getBoundsInParent().getWidth();
				scaleY = pageLayout.getPrintableHeight() / table.getBoundsInParent().getHeight();
				
				System.out.println(" scaleX=" + scaleX);
				System.out.println(" scaleY=" + scaleY);
				
//				scaleX=0.6612612612612613
//				scaleY=1.4580838323353293
				
				table.getTransforms().add(new Scale(0.6612612612612613, 1.4580838323353293));
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
	
	public static List<Controlenvio> guiaToControlenvio(List<Guia> guiaList) {
		
		List<Controlenvio> ControlenvioList = new ArrayList<Controlenvio>();
		
		for ( Guia guia : guiaList ) {
			Controlenvio controlenvio = new Controlenvio();
			controlenvio.setGuia(guia);
			if (guia.getDestinatario() != null)
				controlenvio.setDepartamento(guia.getDestinatario().getDepartamento().getNombre());
			else 
				controlenvio.setDepartamento(guia.getOtrodepartamento());
			ControlenvioList.add(controlenvio);
		}
		return ControlenvioList;
	}
	/*
	// Esta cosa devuelve un hashmap con las listas separadas por departamento
	public static HashMap<Integer, ArrayList<Guia>> getDeptoFullMap (List<Guia> dataSorted) {
		
		HashMap<Integer, ArrayList<Guia>> hashMap = new HashMap<Integer, ArrayList<Guia>>();
		
		int i = 0;
		Boolean flag = true;
		
		while(flag==true) {
		
			hashMap.put(i, new ArrayList<Guia>());
			
			List<Guia> list = hashMap.get(i);
			
			for (int j = 0; j < dataSorted.size(); j++) {
				if (j == dataSorted.size()){
					flag = false;
					break;
				}
				Guia guia = dataSorted.get(j);
				String departamento = guia.getDestinatario().getDepartamento().getNombre();
				
				if (!list.contains(guia)) {
					i++;
					break;
				} else {
					list.add(guia);
				}
			}
		}
		return hashMap;
	}
*/
	public static HashMap<Integer, ArrayList<Guia>> getDeptoFullMap (List<Controlenvio> controlenvioList) {
	
		HashMap<Integer, ArrayList<Guia>> hashMap = new HashMap<Integer, ArrayList<Guia>>();
		
		String tempDepartamento = null;
		
		int i = 0;
		hashMap.put(i, new ArrayList<Guia>());
		
		for (Controlenvio controlenvio : controlenvioList) {
			
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
