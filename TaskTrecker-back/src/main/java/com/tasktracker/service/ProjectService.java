package com.tasktracker.service;

import java.util.List;

import com.tasktracker.entity.Project;

public interface ProjectService {
	
	public List<Project> findProjects();
	public Project findProjectById(Long id);
	public Project create(Project project);
	public Boolean update(Long id, Project project); 
	public Boolean delete(Long id);
	
}
