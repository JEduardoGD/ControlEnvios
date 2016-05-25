package mx.trillas.RepartoPaqueteria;

import javafx.stage.Stage;
import mx.trillas.RepartoPaqueteria.front.*;
import javafx.application.Application;

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