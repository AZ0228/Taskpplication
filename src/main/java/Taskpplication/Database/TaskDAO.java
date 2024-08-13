package Taskpplication.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Taskpplication.*;

public class TaskDAO {
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (timestamp, description, title, group_name) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(task.getDateTime()));
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getTitle());
            statement.setString(4, task.getGroup());
            statement.executeUpdate();

            // Get the generated key (ID)
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                task.setId(generatedKeys.getInt(1));
            }
            //success message
            System.out.println("successfully inserted task");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task getTask(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection connection = DatabaseInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setDateTime(resultSet.getTimestamp("timestamp").toLocalDateTime());
                task.setDescription(resultSet.getString("description"));
                task.setTitle(resultSet.getString("title"));
                task.setGroup(resultSet.getString("group_name"));
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getTasksByDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        System.out.println("getting tasks");
        String sql ="SELECT * FROM tasks WHERE FORMATDATETIME(timestamp, 'yyyy-MM-dd') = ?";
        try (Connection connection = DatabaseInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setDateTime(resultSet.getTimestamp("timestamp").toLocalDateTime());
                task.setDescription(resultSet.getString("description"));
                task.setTitle(resultSet.getString("title"));
                task.setGroup(resultSet.getString("group_name"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection connection = DatabaseInitializer.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setDateTime(resultSet.getTimestamp("timestamp").toLocalDateTime());
                task.setDescription(resultSet.getString("description"));
                task.setTitle(resultSet.getString("title"));
                task.setGroup(resultSet.getString("group_name"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET timestamp = ?, description = ?, title = ?, group_name = ? WHERE id = ?";
        try (Connection connection = DatabaseInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, Timestamp.valueOf(task.getDateTime()));
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getTitle());
            statement.setString(4, task.getGroup());
            statement.setInt(5, task.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection connection = DatabaseInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}