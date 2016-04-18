package mx.trillas.ControlEnvio;

import javafx.stage.Stage;
import javafx.application.Application;
import mx.trillas.ControlEnvio.front.*;

public class Principal extends Application {

	@Override
	public void start(Stage rootStage) {
		
		Login login = new Login();
		login.LoginStage(rootStage);
		
		Report report = new Report();
//		report.GenerarReporteStage(rootStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}