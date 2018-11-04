package com.project.taskmanager.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Project {

    @Id
    @GeneratedValue
    private Long projectId;

    private String projectName;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> taskList;

}
