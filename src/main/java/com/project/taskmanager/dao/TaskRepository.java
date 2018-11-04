package com.project.taskmanager.dao;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
