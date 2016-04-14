package mx.trillas.ControlEnvio;

import java.util.Date;

import javafx.application.Application;
import javafx.stage.Stage;
import mx.trilas.ControlEnvio.front.*;
import mx.trillasControlEnvio.persistence.pojos.Reporte;

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
		
//		login.LoginStage(rootStage);

//		 menu.UserMenuStage(rootStage);
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
//		origenes.otroOrigenStage(rootStage);
		
//		destinatarios.destinatariosStage(rootStage);
//		destinatarios.modificarDestinatariosStage(rootStage);
//		destinatarios.otroDestinatarioStage(rootStage);
		destinatarios.ConfirmarDestinatariosStage(rootStage, new Reporte(new Integer(0),"DHL", "Chihuahua", "Maria Dominguez", "Contaduria", "", new Date()));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
