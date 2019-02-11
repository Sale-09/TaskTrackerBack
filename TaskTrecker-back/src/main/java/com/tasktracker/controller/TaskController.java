package com.tasktracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasktracker.dto.TaskDTO;
import com.tasktracker.service.TaskService;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping()
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		List<TaskDTO> retVal = taskService.findAll();
		if(retVal != null) {
			return new ResponseEntity<List<TaskDTO>>(retVal, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
		TaskDTO retVal = taskService.findOne(id);
		if(retVal != null) {
			return new ResponseEntity<>(retVal, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping()
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
		TaskDTO retVal = taskService.create(taskDTO);
		if(retVal != null) {
			return new ResponseEntity<TaskDTO>(retVal, HttpStatus.CREATED);
		}
		return new ResponseEntity<TaskDTO>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
		if(taskService.update(id, taskDTO)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(taskService.delete(id)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}











