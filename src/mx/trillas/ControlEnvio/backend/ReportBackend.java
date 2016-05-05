package mx.trillas.ControlEnvio.backend;

import org.apache.log4j.Logger;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.transform.Scale;
import mx.trillas.ControlEnvio.persistence.pojos.Guia;

public class ReportBackend {

	private static Logger logger = Logger.getLogger(ReportBackend.class);

	public static void printForTable(TableView<Guia> table) throws Exception {

		Alert alertWarn = new Alert(AlertType.WARNING);
		alertWarn.setTitle("Alerta al imprimir");

		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setTitle("información");

		Printer printer = null;
		PageLayout pageLayout = null;
		double scaleX = 0;
		double scaleY = 0;

		try {
			printer = Printer.getDefaultPrinter();

			if (printer != null) {
				pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT,
						Printer.MarginType.DEFAULT);

				scaleX = pageLayout.getPrintableWidth() / table.getBoundsInParent().getWidth();
				scaleY = pageLayout.getPrintableHeight() / table.getBoundsInParent().getHeight();

				table.getTransforms().add(new Scale(scaleX, scaleY));

				PrinterJob job = PrinterJob.createPrinterJob();
				if (job != null) {
					boolean success = job.printPage(table);
					if (success) {
						job.endJob();

						alertInfo.setHeaderText(null);
						alertInfo.setContentText("La Impresión ha concluido exitosamente");
						alertInfo.showAndWait();
					} else {
						alertWarn.setHeaderText(null);
						alertWarn.setContentText(
								"La Impresión no pudo concluir satisfactoriamente. Verifique su conexión a impresora");
						alertWarn.showAndWait();
					}
				}
			} else {
				logger.error("No existe impresora instalada");
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
