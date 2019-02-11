package com.tasktracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktracker.dto.CommentDTO;
import com.tasktracker.dto.TaskDTO;
import com.tasktracker.dto.UserDTO;
import com.tasktracker.entity.Comment;
import com.tasktracker.entity.Project;
import com.tasktracker.entity.Task;
import com.tasktracker.repository.CommentRepository;
import com.tasktracker.repository.ProjectRepository;
import com.tasktracker.repository.TaskRepository;
import com.tasktracker.repository.UserRepository;
import com.tasktracker.security.model.user.SecurityUser;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<TaskDTO> findAll() {
		List<Task> tasks = taskRepository.findAll();
		if(tasks != null) {
			List<TaskDTO> tasksDTO = new ArrayList<>();
			for(Task task : tasks) {
				TaskDTO taskDTO = new TaskDTO(task);
				taskDTO.setUserDTO(new UserDTO(task.getUser()));
				taskDTO.setProjectId(task.getProject().getId());
				taskDTO.setProjectName(task.getProject().getName());
				tasksDTO.add(taskDTO);
			}
			return tasksDTO;
		}
		return null;
	}

	@Override
	public TaskDTO findOne(Long id) {
		Task task = taskRepository.findOne(id);
		if(task != null) {
			TaskDTO taskDTO = new TaskDTO(task);
			taskDTO.setUserDTO(new UserDTO(task.getUser()));
			taskDTO.setProjectId(task.getProject().getId());
			taskDTO.setProjectName(task.getProject().getName());
			List<CommentDTO> commentsDTO = new ArrayList<>();
			for(Comment comment : task.getComments()) {
				CommentDTO commentDTO = new CommentDTO(comment);
				UserDTO userDTO = new UserDTO(comment.getUser());
				commentDTO.setUserDTO(userDTO);
				commentsDTO.add(commentDTO);
			}
			taskDTO.setComments(commentsDTO);
			return taskDTO;
		}
		return null;
	}

	@Override
	public TaskDTO create(TaskDTO taskDTO) {
		Task task = new Task(taskDTO);
		Project project = projectRepository.findOne(taskDTO.getProjectId());
		if(project == null) {
			return null;
		}
		task.setProject(project);
		SecurityUser user = userRepository.findByUsername(taskDTO.getUserDTO().getUsername());
		task.setUser(user);
		Task savedTask = taskRepository.save(task);
		return new TaskDTO(savedTask);
	}

	@Override
	public Boolean update(Long id, TaskDTO taskDTO) {
		Task task = taskRepository.findOne(id);
		if(task != null) {
			task.setId(id);
			task.setDate(taskDTO.getDate());
			task.setText(taskDTO.getText());
			task.setIsCompleted(taskDTO.getIsComplete());
			task.setProject(projectRepository.findOne(taskDTO.getProjectId()));
			task.setUser(userRepository.findByUsername(taskDTO.getUserDTO().getUsername()));
			task.setComments(taskRepository.findOne(id).getComments());
			taskRepository.save(task);
			return true;
		}
		return false;
	}

	@Override
	public Boolean delete(Long id) {
		Task task = taskRepository.findOne(id);
		if(task != null) {
			taskRepository.delete(task);
			return true;
		}
		return false;
	}
	
	
	
	

}
