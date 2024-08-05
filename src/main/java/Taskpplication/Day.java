package Taskpplication;

import java.util.*;
import java.io.*;
import java.time.*;

public class Day {
    private LocalDate current_date;
    private HashMap<Integer, Task> task_list;

    // constructors
    public Day(LocalDate ld, Task[] tl) {
        this.current_date = ld;
        this.task_list = new HashMap<Integer, Task>();
        for (int i = 0; i < tl.length; i++) {
            task_list.put(tl[i].getId(), tl[i]);
        }
    }
    
    public Day(LocalDate ld) {
        this.current_date = ld;
        this.task_list = new HashMap<Integer, Task>();
    }
    
    // accessors
    public LocalDate return_date() { return this.current_date; }
    public HashMap<Integer, Task> return_taskList() { return this.task_list; }
    // accessors for a task with task_id
    public String return_title(int task_id) {
        Task t = task_list.get(task_id);
        return t.getTitle();
    }

    public String return_description(int task_id) {
        Task t = task_list.get(task_id);
        return t.getDescription();
    }

    public String return_group(int task_id) {
        Task t = task_list.get(task_id);
        return t.getGroup();
    }

    public LocalDateTime return_datetime(int task_id) {
        Task t = task_list.get(task_id);
        return t.getDateTime();
    }
    
    // modifiers
    public boolean add_task(String title, String description, String group, LocalDateTime dt, int task_id) {
        Task t = new Task(title, description, group, dt, task_id);
        task_list.put(task_id, t);
        // put into db?
        return true;
    }
    
    public boolean edit_title(int task_id, String new_title) {
        Task t  = task_list.get(task_id);
        t.setTitle(new_title);
        return true;        
    }

    public boolean edit_description(int task_id, String new_description) {
        Task t  = task_list.get(task_id);
        t.setDescription(new_description);
        return true;        
    }

    public boolean edit_group(int task_id, String new_group) {
        Task t  = task_list.get(task_id);
        t.setGroup(new_group);
        return true;        
    }

    public boolean edit_dateandtime(int task_id, LocalDateTime ldt) {
        // assumes upper class checks if the day attribute of class has not changed, just time
        // if date changes, call below delete function
        Task t  = task_list.get(task_id);
        t.setDateTime(ldt);
        return true;        
    }

    public boolean remove_task(int task_id) {
        task_list.remove(task_id);
        return true;
    }

    
    public static void main(String[] args) {
    	LocalDateTime dt = LocalDateTime.now();
    	Task task1 = new Task("title", "description", "group", dt, 1111);
    	Task task2 = new Task("title2", "description2", "group2", dt, 1112);
    	System.out.println(task1.equals(task2));
    }
        
}