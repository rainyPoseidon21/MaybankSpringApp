package com.maybank.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {

    private long id;
    private String name;
    private String description;
    private List<Long> employeeIds;
}
