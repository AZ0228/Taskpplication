package Taskpplication;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The WeekController class handles switching
 * from the Month view to the Week view.
 * @author Chev Kodama
 * @author Vincent Tran
 * @author James Liu
 * @author Kirsten Szeto
 * @version 1.0
 */
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

    private List<VBox> dayBoxes;
    
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

    private Week week;
    
    @FXML
    void prevWeek(ActionEvent event) 
    {

    }
    
    private void initializeWeek()
    {
        Day[] days = ControllerHelper.getMonthObject().getWeek(ControllerHelper.getWeek());
        System.out.println(ControllerHelper.getWeek());

        for (int i = 0; i < days.length; i++) {
            Day day = days[i];
            day.getTasks();
            VBox dayBox = dayBoxes.get(i);  // Get the corresponding VBox for the day
            dayBox.getChildren().clear();   // Clear any existing tasks

            List<Task> tasks = day.return_taskList();
            //sort by time
            tasks.sort(Comparator.comparing(Task::getDateTime));

            for (Task task : tasks) {
                Rectangle rectangle = new Rectangle(138, 30);
                rectangle.setArcWidth(20);
                rectangle.setArcHeight(20);
                if(task.getDateTime().toLocalTime().equals(LocalTime.of(0,0,1))){
                    rectangle.setFill(Color.web("#726B68"));
                    rectangle.setOnMouseEntered(event ->
                            rectangle.setFill(Color.web("#B4ABA6"))
                    );

                    rectangle.setOnMouseExited(event ->
                            rectangle.setFill(Color.web("#726B68"))
                    );

                    rectangle.setOnMouseClicked(event ->
                            ControllerHelper.openTask(task.getId())
                    );
                } else {
                    rectangle.setFill(Color.web("#97694f"));
                    rectangle.setOnMouseEntered(event ->
                            rectangle.setFill(Color.web("#18110e"))
                    );

                    rectangle.setOnMouseExited(event ->
                            rectangle.setFill(Color.web("#97694f"))
                    );

                    rectangle.setOnMouseClicked(event ->
                            ControllerHelper.openTask(task.getId())
                    );
                }

                Text text = new Text(task.getTitle());
                text.setMouseTransparent(true);
                text.setFill(Color.WHITE);

                StackPane pane = new StackPane();
                pane.getChildren().addAll(rectangle, text);

                // Add the pane to the appropriate VBox for the day
                dayBox.getChildren().add(pane);
            }
        }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
        dayBoxes = new ArrayList<>();
        dayBoxes.add(sunday);    // Index 0
        dayBoxes.add(monday);    // Index 1
        dayBoxes.add(tuesday);   // Index 2
        dayBoxes.add(wednesday); // Index 3
        dayBoxes.add(thursday);  // Index 4
        dayBoxes.add(friday);    // Index 5
        dayBoxes.add(saturday);  // Index 6
        initializeWeek();
	}

}
