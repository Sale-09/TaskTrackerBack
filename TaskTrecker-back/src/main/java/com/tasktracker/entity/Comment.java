package com.tasktracker.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.tasktracker.security.model.user.SecurityUser;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Long id;
	private String text;
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	private Task task;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	private SecurityUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public SecurityUser getUser() {
		return user;
	}

	public void setUser(SecurityUser user) {
		this.user = user;
	}

	
	
}
