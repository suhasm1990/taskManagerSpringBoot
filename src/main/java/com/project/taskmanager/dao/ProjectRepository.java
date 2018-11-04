package com.project.taskmanager.dao;

import com.project.taskmanager.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
