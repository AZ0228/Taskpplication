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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {

    }

}
