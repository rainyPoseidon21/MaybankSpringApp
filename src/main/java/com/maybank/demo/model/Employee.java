package com.maybank.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @SequenceGenerator(name = "emp_seq", sequenceName = "emp_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    private Long id;
    private String name;
    private int age;
    private String email;

    //    Many to one relation with employee-department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


}
