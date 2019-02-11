package com.tasktracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasktracker.entity.Project;
import com.tasktracker.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public List<Project >findProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects;
	}

	@Override
	public Project findProjectById(Long id) {
		Project project = projectRepository.findOne(id);
		return project;
	}

	@Override
	public Project create(Project project) {
		Project newProject = projectRepository.save(project);
		return newProject;
	}

	@Override
	public Boolean update(Long id, Project project) {
		Project newProject = projectRepository.findOne(id);
		if(newProject != null) {
			newProject.setId(id);
			newProject.setName(project.getName());
			newProject.setTasks(project.getTasks());
			projectRepository.save(newProject);
			return true;
		}
		return false;
	}

	@Override
	public Boolean delete(Long id) {
		Project project = projectRepository.findOne(id);
		if(project != null) {
			projectRepository.delete(project);
			return true;
		}
		return false;
	}

}
