package Taskpplication;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import Taskpplication.Database.TaskDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    private int id;
    private TaskDAO taskDao;

    @FXML
    void deleteTask(ActionEvent event)
    {
        Task task = taskDao.getTask(id);

        taskDao.deleteTask(id);
    }

    @FXML
    void editTask(ActionEvent event)
    {
        Stage stage = (Stage) dateText.getScene().getWindow();
        ControllerHelper.setId(id);
        Task task = taskDao.getTask(id);
        taskDao.deleteTask(id);
        LocalDateTime dt = task.getDateTime();
        ControllerHelper.openTaskCreator(dt.toLocalDate(), task.getDescription(),
        		task.getGroup(), String.format("%d", dt.getHour()),
        		String.format("%d", dt.getMinute()), task.getTitle(), id);
        stage.close();

    }

    @FXML
    void completeTask(){
        Task task = taskDao.getTask(id);
        task.setDateTime(task.getDateTime().with(LocalTime.of(0,0,1)));
        taskDao.updateTask(task);
        ControllerHelper.switchView("Week.fxml");
        Stage stage = (Stage) dateText.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        this.id = ControllerHelper.getId();
        this.taskDao = new TaskDAO();
        Task task = taskDao.getTask(id);
        if(task.getDateTime().toLocalTime().equals(LocalTime.of(0,0,1))){
            checkBox.setSelected(true);
            checkBox.setDisable(true);
        }
        dateText.setText(task.getDateTime().toLocalDate().toString());
        descriptionField.setText(task.getDescription());
        groupText.setText(task.getGroup());
        timeText.setText(task.getDateTime().toLocalTime().toString());
        titleText.setText(task.getTitle());
        checkBox.setSelected(task.getComplete());
    }
}