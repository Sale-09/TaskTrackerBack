package com.tasktracker.dto;

import java.util.Date;
import java.util.List;

import com.tasktracker.entity.Task;

public class TaskDTO {

	private Long id;
	private Date date;
	private String text;
	private Boolean isComplete;
	private UserDTO userDTO;
	private Long projectId;
	private String projectName;
	private List<CommentDTO> comments;

	public TaskDTO() {
		
	}

	public TaskDTO(Date date, UserDTO userDTO ,String text, Boolean isComplete, Long projectId, String projectName) {
		this.date = date;
		this.userDTO = userDTO;
		this.text = text;
		this.isComplete = isComplete;
		this.projectId = projectId;
		this.projectName = projectName;
	}

	public TaskDTO(Task task) {
		this.id = task.getId();
		this.text = task.getText();
		this.date = task.getDate();
		this.isComplete = task.getIsCompleted();
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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
