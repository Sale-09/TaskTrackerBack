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

import com.tasktracker.entity.Project;
import com.tasktracker.service.ProjectService;

@RestController
@RequestMapping(value = "/api/projects")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping()
	public ResponseEntity<List<Project>> getProjects() {
		List<Project> retVal = projectService.findProjects();
		if(retVal != null) {
			return new ResponseEntity<List<Project>>(retVal, HttpStatus.OK);
		}
		return new ResponseEntity<List<Project>>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
		Project retVal = projectService.findProjectById(id);
		if(retVal != null) {
			return new ResponseEntity<Project>(retVal, HttpStatus.OK);
		}
		return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping()
	public ResponseEntity<Project> create(@RequestBody Project project) {
		Project retVal = projectService.create(project);
		if(retVal != null) {
			return new ResponseEntity<Project>(retVal, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Project project) {
		if(projectService.update(id, project)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(projectService.delete(id)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
