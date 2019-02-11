package com.tasktracker.dto;

import java.util.Date;

import com.tasktracker.entity.Comment;

public class CommentDTO {
	
	private Long id;
	private Date date;
	private String text;
	private Long taskId;
	private UserDTO userDTO;
	
	public CommentDTO() {
		
	}
	
	public CommentDTO(Comment comment) {
		id = comment.getId();
		date = comment.getDate();
		text = comment.getText();
		taskId = comment.getTask().getId();
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
	
	public Long getTaskId() {
		return taskId;
	}
	
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	public UserDTO getUserDTO() {
		return userDTO;
	}
	
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	
	
}
