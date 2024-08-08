package Taskpplication;

import java.time.LocalDateTime;

/**
 * The Task class represents a task, its organization,
 * and when it takes place.
 * @version 1.0
 */
public class Task {

	private String title;
	private String description;
	private String group;
	private LocalDateTime date_time;
	private int task_id;


	/**
	 * Task constructor
	 * @param t		title
	 * @param d 	description
	 * @param g 	group name
	 * @param dt 	date and time
	 * @param id 	this task's id
	 */
	public Task(String t, String d, String g, LocalDateTime dt, int id, int dayId) {
		title = t;
		description = d;
		group = g;
		date_time = dt;
		task_id = id;
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