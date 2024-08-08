package Taskpplication;

import java.util.*;
import java.io.*;
import java.time.*;

import Taskpplication.Database.*;

public class Day {
    private LocalDate current_date;
    private HashMap<Integer, Task> task_list;
    private int dayId;
    private TaskDAO taskDao;


    // constructors
    public Day(LocalDate ld, Task[] tl, int id) {
        this.current_date = ld;
        this.task_list = new HashMap<Integer, Task>();
        for (int i = 0; i < tl.length; i++) {
            task_list.put(tl[i].getId(), tl[i]);
        }
        this.dayId = id;
        this.taskDao = new TaskDAO();
    }
    
    public Day(LocalDate ld) {
        this.current_date = ld;
        this.task_list = new HashMap<Integer, Task>();
        this.taskDao = new TaskDAO();
        List<Task> tasks = taskDao.getTasksByDate(ld);
        for(Task t : tasks) {
            task_list.put(t.getId(), t);
        }

    }
    
    // accessors
    public LocalDate return_date() { return this.current_date; }

    public List<Task> return_taskList() {
        Collection<Task> values = task_list.values();
        return new ArrayList<>(values);
    }
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
    public boolean add_task(String title, String description, String group, LocalDateTime dt) {
        Task t = new Task(title, description, group, dt);
        // put into db?
        taskDao.addTask(t);
        task_list.put(t.getId(), t);
        return true;
    }

    // modifiers
    public boolean add_task(Task t) {
        // put into db?
        taskDao.addTask(t);
        task_list.put(t.getId(), t);
        return true;
    }
    
    public boolean edit_title(int task_id, String new_title) {
        Task t  = task_list.get(task_id);
        t.setTitle(new_title);
        taskDao.updateTask(t);
        return true;        
    }

    public boolean edit_description(int task_id, String new_description) {
        Task t  = task_list.get(task_id);
        t.setDescription(new_description);
        taskDao.updateTask(t);
        return true;        
    }

    public boolean edit_group(int task_id, String new_group) {
        Task t  = task_list.get(task_id);
        t.setGroup(new_group);
        taskDao.updateTask(t);
        return true;        
    }

    public boolean edit_dateandtime(int task_id, LocalDateTime ldt) {
        // assumes upper class checks if the day attribute of class has not changed, just time
        // if date changes, call below delete function
        Task t  = task_list.get(task_id);
        t.setDateTime(ldt);
        taskDao.updateTask(t);
        return true;        
    }

    public boolean remove_task(int task_id) {
        task_list.remove(task_id);
        taskDao.deleteTask(task_id);
        return true;
    }   
}