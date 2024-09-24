package com.maybank.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tables")
@Data
@NoArgsConstructor
public class Project {

    @Id
    @SequenceGenerator(name = "project_seq",
            sequenceName = "project_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    private long id;
    private String name;
    private String description;

//    Many to many relationship with project-employee
    @ManyToMany(mappedBy = "projectList")
    private List<Employee> employeeList;
}
