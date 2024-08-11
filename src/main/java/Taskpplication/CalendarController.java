package Taskpplication;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CalendarController implements Initializable
{

    @FXML
    private GridPane calendar;

    @FXML
    private Text monthText;
    
    private StackPane panes[][];
    private Month month;
    
    @FXML
    void newTask(ActionEvent event) 
    {
    	ControllerHelper.openTaskCreator();
    }

    @FXML
    void nextMonth(ActionEvent event) 
    {
    	ControllerHelper.setMonth(ControllerHelper.getMonth()+1);
    	ControllerHelper.switchView("Calendar.fxml");
    }

    @FXML
    void prevMonth(ActionEvent event) 
    {
    	ControllerHelper.setMonth(ControllerHelper.getMonth()-1);
    	ControllerHelper.switchView("Calendar.fxml");
    }
    
    private void onRowHover(int row)
    {
    	for (int i = 0; i < 7; i++) 
    	{
    		if(panes[row][i].getId().equals("OUT"))
    		{
    			panes[row][i].setStyle("-fx-background-color:  black; -fx-border-color: white; -fx-border-width: 1;");
    		}
    		else
    		{
    			panes[row][i].setStyle("-fx-background-color:  #97694f; -fx-border-color: white; -fx-border-width: 1;");
    		}
        }
    }
    
    private void onRowExit(int row)
    {
    	for (int i = 0; i < 7; i++) 
    	{
    		if(panes[row][i].getId().equals("OUT"))
    		{
    			panes[row][i].setStyle("-fx-background-color:  gray; -fx-border-color: white; -fx-border-width: 1;");
    		}
    		else
    		{
    			panes[row][i].setStyle("-fx-background-color:  #c1a595; -fx-border-color: white; -fx-border-width: 1;");
    		}
        }
    }
    
    private void onRowClick(int row)
    {
    	ControllerHelper.setWeek(row);
    	ControllerHelper.switchView("Week.fxml");
    }
    
    private void initializeCalendar()
    {
    	Day[][] days = month.getMonth();
    	for(int i = 0; i < 6; i++)
    	{
    		for(int j = 0; j < 7; j++)
    		{
    	        StackPane pane = new StackPane();
    	        if(days[i][j].getOutsideMonth())
    	        {
    	        	pane.setStyle("-fx-background-color:  gray; -fx-border-color: white; -fx-border-width: 1;");
    	        	pane.setId("OUT");
    	        }
    	        else
    	        {
    	        	pane.setStyle("-fx-background-color:  #c1a595; -fx-border-color: white; -fx-border-width: 1;");
    	        	pane.setId("IN");
    	        }
    	        
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
    	        
    	        LocalDate date = days[i][j].return_date();
    	        
                Text numDay = new Text();
                numDay.setStyle("-fx-font-size: 20;");
                
                numDay.setText(""+ date.getDayOfMonth());
                numDay.setFill(Color.web("#18110e"));
                
                Text numTasks = new Text();
                numTasks.setStyle("-fx-font-size: 20;");
                numTasks.setText(days[i][j].return_taskList().size()+" Tasks");
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
    
    private void initMonth()
    {
    	int month = ControllerHelper.getMonthObject().getMonthNumber();

    	if (month == 1) {
    	    monthText.setText("January");
    	} else if (month == 2) {
    	    monthText.setText("February");
    	} else if (month == 3) {
    	    monthText.setText("March");
    	} else if (month == 4) {
    	    monthText.setText("April");
    	} else if (month == 5) {
    	    monthText.setText("May");
    	} else if (month == 6) {
    	    monthText.setText("June");
    	} else if (month == 7) {
    	    monthText.setText("July");
    	} else if (month == 8) {
    	    monthText.setText("August");
    	} else if (month == 9) {
    	    monthText.setText("September");
    	} else if (month == 10) {
    	    monthText.setText("October");
    	} else if (month == 11) {
    	    monthText.setText("November");
    	} else if (month == 12) {
    	    monthText.setText("December");
    	} else {
    	    monthText.setText("N/A");
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		month = ControllerHelper.getMonthObject();
		panes = new StackPane[6][7];
		initMonth();
		initializeCalendar();
        
	}

}
