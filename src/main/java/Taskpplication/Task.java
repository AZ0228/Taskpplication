package Taskpplication;

import java.time.LocalDateTime;

class Task {

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
	public Task(String t, String d, String g, LocalDateTime dt, int id) {
		title = t;
		description = d;
		group = g;
		date_time = dt;
		task_id = id;
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
	 * @param d 	the new group name.
	 */
	public void setGroup(String g) {
		group = g;
	}

	/**
	 * Sets the date and time.
	 * @param d 	the new date and time.
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
    /*
	@Override
	public boolean equals(Object o) {
		if ( o.getType() != this.getType() ) {
			return false;
		}
		Task other = ( Task )o;

		if ( date_time.isEqual(other) ) {
			return true;
		}
		return false;
	}
    */
}