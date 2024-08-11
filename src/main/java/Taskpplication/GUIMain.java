package Taskpplication;

import javafx.application.Application;
import javafx.stage.Stage;

public class GUIMain extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		ControllerHelper.setPrimaryStage(primaryStage);
		ControllerHelper.switchView("Calendar.fxml");
	}
}
