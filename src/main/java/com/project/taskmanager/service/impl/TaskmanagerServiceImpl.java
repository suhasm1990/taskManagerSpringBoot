package com.project.taskmanager.service.impl;

import com.project.taskmanager.dao.EmployeeRepository;
import com.project.taskmanager.dao.ProjectRepository;
import com.project.taskmanager.dao.TaskRepository;
import com.project.taskmanager.model.Employee;
import com.project.taskmanager.model.Project;
import com.project.taskmanager.model.Task;
import com.project.taskmanager.service.TaskmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskmanagerServiceImpl implements TaskmanagerService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Iterable<Employee> getAvailableEmployees() {
        Iterable<Employee> availableEmps = new ArrayList<Employee>();

        for (Employee emp : employeeRepository.findAll()) {
            System.out.println(emp.getEmployeeName());
            if (emp.getProject() == null) {
                ((ArrayList<Employee>) availableEmps).add(emp);
            }
        }
        return availableEmps;
    }

    @Override
    @Transactional
    public void assignTask(Long projectDropDown, String taskDescription, String taskStartDate, String taskDueDate, String empList) {
        Task newTask = new Task();
        Project project = projectRepository.findById(projectDropDown).orElseThrow(() -> new EntityNotFoundException("Not Found-->" + projectDropDown));
        ArrayList<Employee> employeesList = new ArrayList<>();
        for (String employeeId : empList.split(",")) {
            Employee emp = employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException("Not Found-->" + employeeId));
            emp.setProject(project);
            emp.setTask(newTask);
            employeesList.add(emp);
        }
        newTask.setTaskDescription(taskDescription);
        try {
            newTask.setTaskStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(taskStartDate));
            newTask.setTaskEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(taskDueDate));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        newTask.setProject(project);
        newTask.setEmployeeList(employeesList);
        taskRepository.save(newTask);
    }

    @Override
    public Iterable<Project> getProjectById(Long ProjectId) {
        List<Project> projects = new ArrayList<>();
        projects.add(projectRepository.findById(ProjectId).orElseThrow(() -> new EntityNotFoundException("Not Found-->" + ProjectId)));
        return projects;
    }

}
