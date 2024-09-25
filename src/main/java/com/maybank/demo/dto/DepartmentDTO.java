package com.maybank.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {

    private long id;
    private String name;
    private List<Long> employeeIds;
}
