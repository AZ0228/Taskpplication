package Taskpplication;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The GUIMain class was previously used for testing
 * Taskpplication's GUI. It is no longer used.
 * @author Chev Kodama
 * @author Vincent Tran
 * @author James Liu
 * @author Kirsten Szeto
 * @version 1.0
 */
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
