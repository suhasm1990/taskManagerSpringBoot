package com.project.taskmanager.controller;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.service.TaskmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskmanagerController {

    @Autowired
    TaskmanagerService taskmanagerService;

    @RequestMapping("/newTask")
    public String newTask(Model model) {
        model.addAttribute("projects", taskmanagerService.getAllProjects());
        model.addAttribute("employees", taskmanagerService.getAvailableEmployees());
        return "assignTask";
    }

    @RequestMapping(value = "/assignTask", method = RequestMethod.POST)
    public String assignTask(Long projectDropDown, String taskDescription, String taskStartDate, String taskDueDate, String empList) {
        taskmanagerService.assignTask(projectDropDown, taskDescription, taskStartDate, taskDueDate, empList);
        return "index";
    }

    @RequestMapping("/viewTasks")
    public String viewTasks(Model model, @RequestParam(value = "projectDropDown", required = false) Long projectDropDown) {

        Iterable<Project> allProjects = taskmanagerService.getAllProjects();

        if (projectDropDown == null) {
            model.addAttribute("projectsDropDownValue", allProjects);
            model.addAttribute("projects", allProjects);
        } else {
            model.addAttribute("projectsDropDownValue", allProjects);
            model.addAttribute("projects", taskmanagerService.getProjectById(projectDropDown));
            model.addAttribute("selectedValue", projectDropDown);
        }
        return "viewTasks";
    }
}
