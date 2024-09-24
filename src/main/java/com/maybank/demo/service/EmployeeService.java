package com.maybank.demo.service;

import com.maybank.demo.dto.EmployeeDTO;
import com.maybank.demo.dto.ProjectDTO;
import com.maybank.demo.model.Employee;
import com.maybank.demo.model.Project;
import com.maybank.demo.repo.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee emp : employees) {
            employeeDTOS.add(convertToDTO(emp));
        }
        return employeeDTOS;
    }

    @Transactional
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return convertToDTO(employee.get());
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDepartmentId(employee.getDepartment().getId());
        employeeDTO.setProjectIds(employee.getProjectList().stream().map(Project::getId).toList());
        return employeeDTO;
    }


}
