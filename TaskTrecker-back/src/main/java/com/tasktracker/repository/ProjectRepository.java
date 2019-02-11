package com.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasktracker.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
