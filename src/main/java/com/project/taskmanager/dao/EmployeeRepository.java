package com.project.taskmanager.dao;

import com.project.taskmanager.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
