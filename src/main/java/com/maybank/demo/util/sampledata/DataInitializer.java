package com.maybank.demo.util.sampledata;

import com.maybank.demo.model.Department;
import com.maybank.demo.model.Employee;
import com.maybank.demo.repo.DepartmentRepository;
import com.maybank.demo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) throws Exception {

        Department department = new Department();
        department.setName("Engineering");
        departmentRepository.save(department);

        Employee emp = new Employee();
        emp.setName("Alex");
        emp.setAge(21);
        emp.setEmail("alex@gmail.com");

        Employee emp1 = new Employee();
        emp1.setName("James");
        emp1.setAge(21);
        emp1.setEmail("alex@gmail.com");
//        emp.setDepartment(department);
//        if(emp.getProjectList() != null){
//            emp.getProjectList().add(project);
//        }else{
//            emp.setProjectList(List.of(project));
//        }
        employeeRepository.save(emp);
        employeeRepository.save(emp1);

    }
}
