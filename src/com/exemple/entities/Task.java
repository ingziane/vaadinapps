package com.exemple.entities;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Task implements Serializable{
	
	private int id;
	private String title;
	private String task;
	private Date taskDate;
	
/*------------------Constructor----------------------------*/

	public Task() {
		super();
	}
	
	public Task(int id, String title, String task, Date taskDate) {
		super();
		this.id = id;
		this.title = title;
		this.task = task;
		this.taskDate = taskDate;
	}
	
/*------------------Getters & Setters----------------------*/
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	
	
}
