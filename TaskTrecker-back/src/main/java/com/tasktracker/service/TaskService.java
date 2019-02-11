package com.tasktracker.service;

import java.util.List;

import com.tasktracker.dto.TaskDTO;

public interface TaskService {
	
	public List<TaskDTO> findAll();
	public TaskDTO findOne(Long id);
	public TaskDTO create(TaskDTO taskDTO);
	public Boolean update(Long id, TaskDTO taskDTO);
	public Boolean delete(Long id);
	
}
