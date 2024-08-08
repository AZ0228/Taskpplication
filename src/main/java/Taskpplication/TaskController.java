package Taskpplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class TaskController implements Initializable
{

    @FXML
    private CheckBox checkBox;

    @FXML
    private Text dateText;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Text groupText;

    @FXML
    private Text timeText;

    @FXML
    private Text titleText;

    @FXML
    void deleteTask(ActionEvent event) 
    {

    }

    @FXML
    void editTask(ActionEvent event) 
    {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// TODO Auto-generated method stub
		
	}

}
