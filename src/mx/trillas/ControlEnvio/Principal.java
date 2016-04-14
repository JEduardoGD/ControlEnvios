package mx.trillas.ControlEnvio;

import javafx.application.Application;
import javafx.stage.Stage;
import mx.trilas.ControlEnvio.front.Login;

public class Principal extends Application {

	@Override
	public void start(Stage rootStage) {
		
		Login login = new Login();
		login.LoginStage(rootStage);

//		 UserMenuStage(rootStage);
//		 CapturaStage(rootStage);

//		 AdminMenuStage(rootStage);

//		 GenerarReporteStage(rootStage);

//		mensajeriaStage(rootStage);
//		 origenesStage(rootStage);
//		 destinatariosStage(rootStage);

//		otroOrigenStage(rootStage);
//		otroDestinatarioStage(rootStage);
		
//		reporteViewStage(rootStage); 
		
//		modificarMensajeriaStage(rootStage);
//		modificarOrigenesStage(rootStage);
//		modificarDestinatariosStage(rootStage);
		
//		ConfirmarStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));
		
//		ConfirmarMensajeriaStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));
//		ConfirmarOrigenesStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));
//		ConfirmarDestinatariosStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
