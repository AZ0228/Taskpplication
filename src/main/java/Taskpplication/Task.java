package Taskpplication;

import Taskpplication.Database.TaskDAO;

import java.time.LocalDateTime;

/**
 * The Task class represents a task, its organization,
 * and when it takes place.
 * @author Chev Kodama
 * @author Vincent Tran
 * @author James Liu
 * @author Kirsten Szeto
 * @version 1.0
 */
public class Task {

	private String title;
	private String description;
	private String group;
	private LocalDateTime date_time;
	private int task_id;
	private boolean complete;
	private TaskDAO taskDAO;

	/**
	 * Task constructor
	 * @param t		title
	 * @param d 	description
	 * @param g 	group name
	 * @param dt 	date and time
	 * @param id 	this task's id
	 */
	public Task(String t, String d, String g, LocalDateTime dt, int id, int dayId, boolean c) {
		title = t;
		description = d;
		group = g;
		date_time = dt;
		task_id = id;
		complete = c;
	}

	/**
	 * Task constructor
	 * @param t		title
	 * @param d 	description
	 * @param g 	group name
	 * @param dt 	date and time
	 */
	public Task(String t, String d, String g, LocalDateTime dt) {
		title = t;
		description = d;
		group = g;
		date_time = dt;
	}

	public Task(){}

	/**
	 * Sets the id.
	 * @param id 	the new group name.
	 */
	public void setId(int id) {
		task_id=id;
	}


	/**
	 * Sets the title.
	 * @param t 	the new title.
	 */
	public void setTitle(String t) {
		title = t;
	}

	/**
	 * Sets the description.
	 * @param d 	the new description.
	 */
	public void setDescription(String d) {
		description = d;
	}

	/**
	 * Sets the group name.
	 * @param g 	the new group name.
	 */
	public void setGroup(String g) {
		group = g;
	}

	/**
	 * Sets the date and time.
	 * @param dt 	the new date and time.
	 */
	public void setDateTime(LocalDateTime dt) {
		date_time = dt;
	}
	
	/**
	 * Sets the completion of this task.
	 * @param c	the completion.
	 */
	public void setComplete(boolean c) {
		complete = c;
	}

	public void complete(){
		Task task = taskDAO.getTask(task_id);
		
	}

	/**
	 * Returns the title of this task.
	 * @return 	title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the description of this task.
	 * @return 	description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the group of this task.
	 * @return 	group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Returns the title of this task.
	 * @return 	title
	 */
	public LocalDateTime getDateTime() {
		return date_time;
	}

	/**
	 * Returns the id of this task.
	 * @return 	task_id
	 */
	public int getId() {
		return task_id;
	}
	
	/**
	 * Returns the completion of this task.
	 * @return	complete
	 */
	public boolean getComplete() {
		return complete;
	}

	/**
	 * Compares Task objects by date_time.
	 * If not a Task object returns false.
	 * @param o 	the object to compare against.
	 * @return 		true if date_time is equal.
	 */
	@Override
	public boolean equals(Object o) {
		if ( o == this ) {
			return true;
		}
		
		if (!(o instanceof Task)) {
			return false;
	    }
		
		Task other = ( Task ) o;
		if ( date_time.equals(other.getDateTime()) ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a string version of this Task.
	 * @return a string containing relevant information related to this task.
	 */
	@Override
	public String toString() {
		return "Task{" +
				"id=" + task_id +
				", timestamp=" + date_time +
				", description='" + description + '\'' +
				", title='" + title + '\'' +
				", groupName='" + group + '\'' +
				'}';
	}
}