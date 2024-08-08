package Taskpplication;

import Taskpplication.Database.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        Task task = new Task( "task", "task", "group", LocalDateTime.now());
        Day today = new Day(LocalDate.now());

        today.add_task(task);

        List<Task> all_tasks = today.return_taskList();
        for (Task task1 : all_tasks) {
            System.out.println(task1.toString());
        }

        today.edit_description(task.getId(), "hello there");

        all_tasks = today.return_taskList();
        for (Task task1 : all_tasks) {
            System.out.println(task1.toString());
        }


    }

}