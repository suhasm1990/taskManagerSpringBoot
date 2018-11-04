package com.project.taskmanager.service;

import com.project.taskmanager.model.Employee;
import com.project.taskmanager.model.Project;
import com.project.taskmanager.model.Task;

public interface TaskmanagerService {

    Iterable<Project> getAllProjects();

    Iterable<Project> getProjectById(Long ProjectId);

    Iterable<Employee> getAvailableEmployees();

    void assignTask(Long projectDropDown, String taskDescription, String taskStartDate, String taskDueDate, String empList);

}
