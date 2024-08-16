package Taskpplication;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import Taskpplication.Database.TaskDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The TaskCreatorController class handles the creation
 * and editing of tasks by the user.
 * @author Chev Kodama
 * @author Vincent Tran
 * @author James Liu
 * @author Kirsten Szeto
 * @version 1.0
 */
public class TaskCreatorController implements Initializable
{

    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ComboBox<String> groupField;

    @FXML
    private TextField hourField;

    @FXML
    private TextField minuteField;

    @FXML
    private TextField titleField;

    @FXML
    private Text header;

    @FXML
    private Button createButton;



    private int id;
    
    /**
     * Sets the date
     * @param date	the new date
     */
    public void setDateField(LocalDate date) {
    	dateField.setValue(date);
    }
    
    /**
     * Sets the description
     * @param description	the new description
     */
    public void setDescription(String description) {
    	descriptionField.setText(description);
    }

    public void setTaskId(int taskId) {id = taskId;}
    
    /**
     * Sets the group
     * @param group	the new group
     */
    public void setGroupField(String group) {
    	groupField.setValue(group);
    }
    
    /**
     * Sets the hour
     * @param hour	the new hour
     */
    public void setHourField(String hour) {
    	hourField.setText(hour);
    }
    
    /**
     * Sets the minute
     * @param minute	the new minute
     */
    public void setMinuteField(String minute) {
    	minuteField.setText(minute);
    }
    
    /**
     * Sets the title
     * @param title	the new title
     */
    public void setTitleField(String title) {
    	titleField.setText(title);
    }

    /**
     * This function defines the behavior of creating a task from GUI inputs
     * The above fields allow the user to input their desired information to
     * create these tasks. You must have a date to create a new task. Otherwise,
     * the function will not do anything except print an error message.
     * @param event on click of create task button this function will occur
     */
    @FXML
    void createTask(ActionEvent event)
    {
        // get description information
        String title = titleField.getText();
        String description = descriptionField.getText();
        String groupName = groupField.getValue();

        // get date information
        LocalDate date;
        if (dateField.getValue() == null) {
            System.err.println("Missing date input.\n");
            return;
        } else {
            date = dateField.getValue();
        }
        // get time information
        LocalTime time;
        if (hourField.getText().isEmpty() || minuteField.getText().isEmpty()) {
            System.err.println("Missing time input.\n");
            return;
        } else {
            time = LocalTime.of(Integer.valueOf(hourField.getText()), Integer.valueOf(minuteField.getText()));
        }

        // create the new task
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        LocalDateTime ldt = LocalDateTime.of(date, time); // create the datetime object
        newTask.setDateTime(ldt);
        newTask.setGroup(groupName);

        TaskDAO td = new TaskDAO();
        System.out.println("got here");
        td.addTask(newTask); // add the task to the database

        clear_all(); // clear fields for use next time.
        Stage stage = (Stage) dateField.getScene().getWindow();
        ControllerHelper.switchView("Calendar.fxml");
        ControllerHelper.closeTaskCreator();
        stage.close();
    }

    /**
     * Function to clear the input fields.
     */
    private void clear_all() {
        dateField.setValue(LocalDate.now());
        descriptionField.clear();
        groupField.setValue(null);
        hourField.clear();
        minuteField.clear();
        titleField.clear();
    }

    public void setEdit(){
        header.setText("Edit Task");
        createButton.setText("Save Changes");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {

    }

}
