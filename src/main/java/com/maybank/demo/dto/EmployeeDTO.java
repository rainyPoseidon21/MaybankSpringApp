package com.maybank.demo.dto;


import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private int age;
    private String email;
    private Long departmentId;
    private String favCatFact;

}
