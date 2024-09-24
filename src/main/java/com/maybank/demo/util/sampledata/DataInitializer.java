package com.maybank.demo.util.sampledata;

import com.maybank.demo.model.Employee;
import com.maybank.demo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        Employee emp = new Employee();
        emp.setName("Alex");
        emp.setAge(21);
        emp.setEmail("alex@gmail.com");

        employeeRepository.save(emp);
    }
}
