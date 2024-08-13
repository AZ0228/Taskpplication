package Taskpplication;

import java.util.*;
import java.io.*;
import java.time.*;

import Taskpplication.Database.*;

/**
 * This class is a representation of a day in the calendar which has it's own specific date.
 * The Day class has a list of all the tasks associated with it and a dayID for the database
 * to recognize it. It also has an instance of the TaskDAO so this code can interact with
 * the database is need be.
 * 
 * See below for details on implementation.
 */

public class Day {
    private LocalDate current_date;
    private int dayId;
    private TaskDAO taskDao;
    private boolean outsideMonth = false;

    private HashMap<Integer, Task> task_list;

    // constructors

    /**
     * This constructor intializes an instance of the Day class
     * with the given parameters. This is the most specific constructor.
     * 
     * @param ld Date of the Day to be created
     * @param tl Array of task already associated with that day in the database
     * @param id Id of the day in the database
     */
    public Day(LocalDate ld, Task[] tl, int id) {
        this.current_date = ld;
        this.task_list = new HashMap<Integer, Task>();
        for (int i = 0; i < tl.length; i++) {
            task_list.put(tl[i].getId(), tl[i]);
        }
        this.dayId = id;
        this.taskDao = new TaskDAO();
    }
    
    /**
     * This constructor just creates an instance of the day class
     * given the local date parameter.
     * @param ld Date of the Day to be created
     */
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
    /**
     * Returns the instance variable this.current_date
     * @return this.current_date, the date that the current instance
     *                              is representing
     */
    public LocalDate return_date() { return this.current_date; }

    /**
     * Returns the instance variable this.task_list in arrayList form
     * @return an ArrayLists of all task currently associated with the day
     */
    public List<Task> return_taskList() {
        Collection<Task> values = task_list.values();
        return new ArrayList<>(values);
    }

    // accessors for a task with task_id
    /**
     * Returns title of a task given its ID from the database
     * @param task_id ID number of task from database
     * @return the title of the desired task where possible
     */
    public String return_title(int task_id) {
        Task t = task_list.get(task_id);
        return t.getTitle();
    }

    /**
     * Returns description of a task given its ID from the database
     * @param task_id ID number of task from database
     * @return the description of the desired task where possible
     */
    public String return_description(int task_id) {
        Task t = task_list.get(task_id);
        return t.getDescription();
    }

    /**
     * Returns group of a task given its ID from the database
     * @param task_id ID number of task from database
     * @return the group of the desired task where possible
     */
    public String return_group(int task_id) {
        Task t = task_list.get(task_id);
        return t.getGroup();
    }

    /**
     * Returns date and time of a task given its ID from the database
     * @param task_id ID number of task from database
     * @return the date and time of the desired task
     */
    public LocalDateTime return_datetime(int task_id) {
        Task t = task_list.get(task_id);
        return t.getDateTime();
    }
    
    // modifiers
    /**
     * Add a task to the Day and the HashMap of tasks associated with the
     * day with the parameters provided to the function
     * @param title Title of the new task
     * @param description Description of the new task
     * @param group Group of the new task
     * @param dt Date and Time of the new task
     * @return true if the task was created suscessfully
     */
    public boolean add_task(String title, String description, String group, LocalDateTime dt) {
        Task t = new Task(title, description, group, dt);
        taskDao.addTask(t);
        task_list.put(t.getId(), t);
        return true;
    }

    /**
     * Add a task object provided as a parameter to the Day 
     * and the HashMap of tasks associated with the day.
     * @param t a Task to add to the database
     * @return true if the task was added suscessfully
     */
    public boolean add_task(Task t) {
        taskDao.addTask(t);
        task_list.put(t.getId(), t);
        return true;
    }
    
    /**
     * This function edits the title of an existing task. The Id, and
     * new title are provided as command line arguments.
     * @param task_id The ID of the desired task
     * @param new_title The new title for the desired task
     * @return true if the changes were made suscessfully
     */
    public boolean edit_title(int task_id, String new_title) {
        Task t  = task_list.get(task_id);
        t.setTitle(new_title);
        taskDao.updateTask(t);
        return true;        
    }
    
    /**
     * This function edits the description of an existing task. The Id, and
     * new description are provided as command line arguments.
     * @param task_id The ID of the desired task
     * @param new_title The new description for the desired task
     * @return true if the changes were made suscessfully
     */
    public boolean edit_description(int task_id, String new_description) {
        Task t  = task_list.get(task_id);
        t.setDescription(new_description);
        taskDao.updateTask(t);
        return true;        
    }

    /**
     * This function edits the group of an existing task. The Id, and
     * new group are provided as command line arguments.
     * @param task_id The ID of the desired task
     * @param new_title The new group for the desired task
     * @return true if the changes were made suscessfully
     */
    public boolean edit_group(int task_id, String new_group) {
        Task t  = task_list.get(task_id);
        t.setGroup(new_group);
        taskDao.updateTask(t);
        return true;        
    }

    /**
     * This function edits the date and time of an existing task. 
     * The Id, and new dateandtime are provided as command line arguments.
     * @param task_id The ID of the desired task
     * @param new_title The new date and time for the desired task
     * @return true if the changes were made suscessfully
     */
    public boolean edit_dateandtime(int task_id, LocalDateTime ldt) {
        // assumes upper class checks if the day attribute of class has not changed, just time
        // if date changes, call below delete function
        Task t  = task_list.get(task_id);
        t.setDateTime(ldt);
        taskDao.updateTask(t);
        return true;        
    }

    /**
     * This function deletes the task with the desired ID from the
     * list of associated tasks.
     * @param task_id The ID of the desired task to delete
     * @return true if the changes were made suscessfully
     */
    public boolean remove_task(int task_id) {
        task_list.remove(task_id);
        taskDao.deleteTask(task_id);
        return true;
    }

    public void setOutsideMonth(boolean bool)
    {
        outsideMonth = bool;
    }

    public boolean getOutsideMonth()
    {
        return outsideMonth;
    }
}