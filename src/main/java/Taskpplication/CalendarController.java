package Taskpplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CalendarController implements Initializable
{

    @FXML
    private GridPane calendar;

    @FXML
    private Text monthText;
    
    private StackPane panes[][];
    
    @FXML
    void newTask(ActionEvent event) 
    {
    	ControllerHelper.openTaskCreator();
    }

    @FXML
    void nextMonth(ActionEvent event) 
    {

    }

    @FXML
    void prevMonth(ActionEvent event) 
    {

    }
    
    private void onRowHover(int row)
    {
    	for (int i = 0; i < 7; i++) 
    	{
    		panes[row][i].setStyle("-fx-background-color:  #97694f; -fx-border-color: white; -fx-border-width: 1;");
        }
    }
    
    private void onRowExit(int row)
    {
    	for (int i = 0; i < 7; i++) 
    	{
    		panes[row][i].setStyle("-fx-background-color:  #c1a595; -fx-border-color: white; -fx-border-width: 1;");
        }
    }
    
    private void onRowClick(int row)
    {
    	ControllerHelper.setWeek(row);
    	ControllerHelper.switchView("Week.fxml");
    }
    
    private void initializeCalendar()
    {
    	//Day[][] days = new Day[5][7];
    	for(int i = 0; i < 5; i++)
    	{
    		for(int j = 0; j < 7; j++)
    		{
    	        StackPane pane = new StackPane();
    	        pane.setStyle("-fx-background-color:  #c1a595; -fx-border-color: white; -fx-border-width: 1;");
    	        int row = i;
    	        pane.setOnMouseEntered(event -> 
    	        {
    	        	onRowHover(row);
    	        });

    	        pane.setOnMouseExited(event -> 
    	        {
    	        	onRowExit(row);
    	        });
    	        
    	        pane.setOnMouseClicked(event -> 
    	        {
    	        	onRowClick(row);
    	        });
    	        
                Text numDay = new Text();
                numDay.setStyle("-fx-font-size: 20;");
                numDay.setText("1");
                numDay.setFill(Color.web("#18110e"));
                
                Text numTasks = new Text();
                numTasks.setStyle("-fx-font-size: 20;");
                numTasks.setText("# Tasks");
                numTasks.setFill(Color.web("#18110e"));
                
                pane.getChildren().add(numDay);
                pane.getChildren().add(numTasks);
                StackPane.setAlignment(numDay, Pos.TOP_LEFT);
                StackPane.setAlignment(numTasks, Pos.CENTER);
                
    	        panes[i][j] = pane;
    	        calendar.add(pane, j, i+1);
    		}
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		panes = new StackPane[5][7];
		monthText.setText("January");
		initializeCalendar();
        
	}

}
