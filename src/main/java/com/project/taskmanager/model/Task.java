package com.project.taskmanager.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {

    @Id
    @GeneratedValue
    private Long taskId;

    private String taskDescription;

    private Date taskStartDate;

    private Date taskEndDate;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
}
