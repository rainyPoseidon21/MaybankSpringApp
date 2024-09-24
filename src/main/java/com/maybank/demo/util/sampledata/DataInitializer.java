package com.maybank.demo.util.sampledata;

import com.maybank.demo.model.Department;
import com.maybank.demo.model.Employee;
import com.maybank.demo.model.Project;
import com.maybank.demo.repo.DepartmentRepository;
import com.maybank.demo.repo.EmployeeRepository;
import com.maybank.demo.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {

        Department department = new Department();
        department.setName("Engineering");
        departmentRepository.save(department);


        Project project = new Project();
        project.setName("Cloud Transition");
        project.setDescription("Cloud Transition");
        projectRepository.save(project);

        Employee emp = new Employee();
        emp.setName("Alex");
        emp.setAge(21);
        emp.setEmail("alex@gmail.com");
        emp.setDepartment(department);
        if(emp.getProjectList() != null){
            emp.getProjectList().add(project);
        }else{
            emp.setProjectList(List.of(project));
        }
        employeeRepository.save(emp);
    }
}
