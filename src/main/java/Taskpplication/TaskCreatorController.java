package Taskpplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskCreatorController 
{

    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ComboBox<?> groupField;

    @FXML
    private TextField hourField;

    @FXML
    private TextField minuteField;

    @FXML
    private TextField titleField;

    @FXML
    void createTask(ActionEvent event) 
    {
    	
    }

}
