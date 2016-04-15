package mx.trillas.ControlEnvio;

import java.util.Date;

import javafx.application.Application;
import javafx.stage.Stage;
import mx.trillas.ControlEnvio.front.*;

public class Principal extends Application {

	@Override
	public void start(Stage rootStage) {
		
		Login login = new Login();
		Menu menu =  new Menu();
		Report report = new Report();
		Captura captura = new Captura();
		Mensajeria  mensajeria  = new Mensajeria();
		Destinatarios  destinatarios = new Destinatarios();
		Origenes origenes = new Origenes();

		login.LoginStage(rootStage);

//		 menu.UserMenuStage(rootStage);1
//		 menu.AdminMenuStage(rootStage);

//		captura.CapturaStage(rootStage);
//		captura.ConfirmarStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));

//		report.GenerarReporteStage(rootStage);
//		report.reporteViewStage(rootStage); 
		
//		mensajeria.mensajeriaStage(rootStage);
//		mensajeria.modificarMensajeriaStage(rootStage);
//		mensajeria.ConfirmarMensajeriaStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));

//		origenes.modificarOrigenesStage(rootStage);
//		origenes.origenesStage(rootStage);
//		origenes.ConfirmarOrigenesStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));

		
//		destinatarios.destinatariosStage(rootStage);
//		destinatarios.modificarDestinatariosStage(rootStage);

//		destinatarios.ConfirmarDestinatariosStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));
	
//		origenes.otroOrigenStage(rootStage);
//		destinatarios.otroDestinatarioStage(rootStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
