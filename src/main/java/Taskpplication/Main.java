package Taskpplication;

import Taskpplication.Database.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The Main class represents the startup class for Taskpplication.
 * @author Chev Kodama
 * @author Vincent Tran
 * @author James Liu
 * @author Kirsten Szeto
 * @version 1.0
 */
public class Main extends Application
{
    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        Task task = new Task( "task", "task", "group", LocalDateTime.now());

        LocalDate now = LocalDate.now();
        ControllerHelper.setMonth(now.getMonthValue());
        
        launch(args);
    }
    
	@Override
	public void start(Stage primaryStage)
	{
		ControllerHelper.setPrimaryStage(primaryStage);
		ControllerHelper.switchView("Calendar.fxml");
	}

}