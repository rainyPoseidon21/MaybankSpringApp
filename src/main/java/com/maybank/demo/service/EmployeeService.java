package com.maybank.demo.service;

import com.maybank.demo.dto.EmployeeDTO;
import com.maybank.demo.model.Department;
import com.maybank.demo.model.Employee;
import com.maybank.demo.repo.DepartmentRepository;
import com.maybank.demo.repo.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Page<EmployeeDTO> getEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee emp : employees) {
            employeeDTOS.add(convertToDTO(emp));
        }

        PageImpl<EmployeeDTO> employeeDTOPage = new PageImpl<>(employeeDTOS, pageable, employees.getTotalElements());
        return employeeDTOPage;
    }

    @Transactional
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Employee not found with id: " + id);
                });
        return convertToDTO(employee);
    }

    @Transactional
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setAge(employee.getAge());
            employee.setEmail(employee.getEmail());
            if (employeeDTO.getDepartmentId() != null) {
                setDepartmentToEmployeeById(employee, employeeDTO.getDepartmentId());
            }
            employeeRepository.save(employee);
            return convertToDTO(employee);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> {
                        throw new RuntimeException("Employee not found with id: " + id);
                    });
            employee.setName(employeeDTO.getName());
            employee.setAge(employeeDTO.getAge());
            employee.setEmail(employeeDTO.getEmail());
            if (employeeDTO.getDepartmentId() != null) {
                setDepartmentToEmployeeById(employee, employeeDTO.getDepartmentId());
            }
            employeeRepository.save(employee);
            return convertToDTO(employee);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Transactional
    public void deleteEmployeeById(Long id) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> {
                        throw new RuntimeException("Cannot find employee with id " + id);
                    });
            employeeRepository.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private void setDepartmentToEmployeeById(Employee employee, Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Cannot find department with id " + id);
                });
        employee.setDepartment(department);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setEmail(employee.getEmail());
        if (employee.getDepartment() != null) {
            employeeDTO.setDepartmentId(employee.getDepartment().getId());
        }
        return employeeDTO;
    }
}
