package Taskpplication.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DatabaseInitializer class initializes and
 * helps the TaskDAO class connect to the database.
 * @author Chev Kodama
 * @author Vincent Tran
 * @author James Liu
 * @author Kirsten Szeto
 * @version 1.0
 */
public class DatabaseInitializer {
    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Create tables
//            String createDaysTable = "CREATE TABLE IF NOT EXISTS days (" +
//                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
//                    "day_date TIMESTAMP NOT NULL)";
//            statement.executeUpdate(createDaysTable);

            String createTasksTable = "CREATE TABLE IF NOT EXISTS tasks (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "timestamp TIMESTAMP NOT NULL, " +
                    "description TEXT, " +
                    "title VARCHAR(255), " +
                    "group_name VARCHAR(255))";
            statement.executeUpdate(createTasksTable);

            System.out.println("Database initialized successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
