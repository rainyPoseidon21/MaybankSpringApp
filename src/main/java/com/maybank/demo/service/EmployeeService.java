package com.maybank.demo.service;

import com.maybank.demo.model.Employee;
import com.maybank.demo.repo.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

}
