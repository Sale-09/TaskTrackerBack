package com.tasktracker.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tasktracker.dto.TaskDTO;
import com.tasktracker.security.model.user.SecurityUser;

@Entity
public class Task {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date date;
	
	private String text;
	
	private Boolean isCompleted;
	
	@OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Comment> comments = new HashSet<Comment>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private SecurityUser user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Project project;

	public Task() {
		
	}
	
	public Task(TaskDTO taskDTO) {
		date = taskDTO.getDate();
		text = taskDTO.getText();
		isCompleted = taskDTO.getIsComplete();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public SecurityUser getUser() {
		return user;
	}

	public void setUser(SecurityUser user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
