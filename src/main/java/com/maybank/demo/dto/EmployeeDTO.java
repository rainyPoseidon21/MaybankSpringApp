package com.maybank.demo.dto;

import lombok.Builder;

@Builder
public class EmployeeDTO {
    private Long id;
    private String name;
    private int age;
    private String email;

}
