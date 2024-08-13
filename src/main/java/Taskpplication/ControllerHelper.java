package Taskpplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerHelper 
{
	private static Stage primaryStage_;
	private static int month_;
	private static int year_ = 2024;
	private static Month monthObject_;
	private static int week_;
	private static int id_;
	private static boolean taskCreatorOpen_ = false;

	public static void setPrimaryStage(Stage primaryStage)
	{
		primaryStage_ = primaryStage;
	}
	
	public static void setMonth(int month)
	{
		if(month == 0)
		{
			month = 12;
			year_--;
		}
		else if(month == 13)
		{
			month = 1;
			year_++;
		}
		month_ = month;
		monthObject_ = new Month(month_, year_);
	}
	
	public static void setWeek(int week)
	{
		week_ = week;
	}
	
	public static void setId(int id) {
		id_ = id;
	}
	
	public static int getMonth()
	{
		return month_;
	}
	
	public static Month getMonthObject()
	{
		return monthObject_;
	}
	
	public static int getWeek()
	{
		return week_;
	}
	
	public static int getId() {
		return id_;
	}
	
	public static void switchView(String fxml)
	{
		try
		{
			File file = new File("src/main/resources/" + fxml);
			FileInputStream stream = new FileInputStream(file);
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(stream);
			Scene scene = new Scene(root);
			primaryStage_.setScene(scene);
			primaryStage_.setTitle("Taskpplication");
			primaryStage_.setMinWidth(400);
			primaryStage_.setMinHeight(400);
			primaryStage_.show();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void openTaskCreator()
	{
		if(taskCreatorOpen_)
		{
		}
		else
		{
			try
			{
				taskCreatorOpen_ = true;
				File file = new File("src/main/resources/TaskCreator.fxml");
				FileInputStream stream = new FileInputStream(file);
				FXMLLoader loader = new FXMLLoader();
				Parent root = loader.load(stream);
				Scene scene = new Scene(root);
				Stage taskCreator = new Stage();
				taskCreator.setScene(scene);
				taskCreator.setResizable(false);
				taskCreator.setTitle("Task Creator");
				taskCreator.setOnCloseRequest(e -> taskCreatorOpen_ = false);
				taskCreator.show();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void openTask(int id)
	{
		id_ = id;
		try
		{
			File file = new File("src/main/resources/Task.fxml");
			FileInputStream stream = new FileInputStream(file);
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(stream);
			Scene scene = new Scene(root);
			Stage task = new Stage();
			task.setScene(scene);
			task.setResizable(false);
			task.setTitle("Task");
			task.show();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
