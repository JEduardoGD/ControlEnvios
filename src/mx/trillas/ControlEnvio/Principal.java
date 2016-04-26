package mx.trillas.ControlEnvio;

import javafx.stage.Stage;
import javafx.application.Application;
import mx.trillas.ControlEnvio.front.*;

public class Principal extends Application {

	@Override
	public void start(Stage rootStage) {
		
		LoginWindow login = new LoginWindow();
		login.LoginStage(rootStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}