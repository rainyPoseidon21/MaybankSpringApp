package com.maybank.demo.service;

import com.maybank.demo.dto.ProjectDTO;
import com.maybank.demo.model.Employee;
import com.maybank.demo.model.Project;
import com.maybank.demo.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getProjects() {
        return null;
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(project.getName());
        projectDTO.setId(project.getId());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setEmployeeIds(project.getEmployeeList().stream().map(Employee::getId).toList());
        return projectDTO;
    }
}
