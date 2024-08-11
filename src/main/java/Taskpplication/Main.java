package Taskpplication;

import Taskpplication.Database.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main extends Application
{
    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        Task task = new Task( "task", "task", "group", LocalDateTime.now());
        Day today = new Day(LocalDate.now());

        today.add_task(task);

        List<Task> all_tasks = today.return_taskList();
        for (Task task1 : all_tasks) {
            System.out.println(task1.toString());
        }

        today.edit_description(task.getId(), "hello there");

        all_tasks = today.return_taskList();
        for (Task task1 : all_tasks) {
            System.out.println(task1.toString());
        }
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