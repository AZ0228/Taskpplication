package Taskpplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class WeekController implements Initializable
{

	@FXML
    private GridPane calendar;

    @FXML
    private VBox friday;

    @FXML
    private VBox monday;

    @FXML
    private Text monthText;

    @FXML
    private VBox saturday;

    @FXML
    private VBox sunday;

    @FXML
    private VBox thursday;

    @FXML
    private VBox tuesday;

    @FXML
    private VBox wednesday;
    
    @FXML
    void openMonthView(ActionEvent event) 
    {
    	ControllerHelper.switchView("Calendar.fxml");
    }
    
    @FXML
    void newTask(ActionEvent event) 
    {
    	ControllerHelper.openTaskCreator();
    }

    @FXML
    void nextWeek(ActionEvent event) 
    {

    }
    
    @FXML
    void prevWeek(ActionEvent event) 
    {

    }
    
    private void initializeWeek()
    {
    	for(int i = 0; i < 10; i++)
    	{
    		Rectangle rectangle = new Rectangle(138, 30);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(20);
            rectangle.setFill(Color.web("#97694f"));
            rectangle.setOnMouseEntered(event -> 
	        {
	        	rectangle.setFill(Color.web("#18110e"));
	        });

            rectangle.setOnMouseExited(event -> 
	        {
	        	rectangle.setFill(Color.web("#97694f"));
	        });
	        
            rectangle.setOnMouseClicked(event -> 
	        {
	        	ControllerHelper.openTask();
	        });
            Text text = new Text("Example task: " + i);
            text.setMouseTransparent(true);
            text.setFill(Color.WHITE);
            StackPane pane = new StackPane();
            pane.getChildren().addAll(rectangle, text);
            
            sunday.getChildren().add(pane);
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		initializeWeek();
	}

}
