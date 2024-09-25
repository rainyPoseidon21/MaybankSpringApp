package com.maybank.demo.service;

import com.maybank.demo.dto.DepartmentDTO;
import com.maybank.demo.dto.EmployeeDTO;
import com.maybank.demo.model.Department;
import com.maybank.demo.model.Employee;
import com.maybank.demo.repo.DepartmentRepository;
import com.maybank.demo.repo.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public List<DepartmentDTO> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (Department department : departments) {
            departmentDTOS.add(convertToDTO(department));
        }
        return departmentDTOS;
    }

    @Transactional
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Department not found with id: " + id);
                });
        return convertToDTO(department);
    }

    @Transactional
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        try {
            Department department = new Department();
            department.setName(departmentDTO.getName());
            department.setEmployeeList(employeeRepository.findAllById(departmentDTO.getEmployeeIds()));
            Department savedDepartment = departmentRepository.save(department);
            setDepartmentIdToEmployees(departmentDTO.getEmployeeIds(), savedDepartment);
            return convertToDTO(savedDepartment);
        } catch (Exception ex) {
            throw new RuntimeException("Error creating department with department name " + departmentDTO.getName());
        }
    }

    @Transactional
    public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO, Long id) {
        try {
            Department department = departmentRepository.findById(id)
                    .orElseThrow(
                            () -> {
                                throw new RuntimeException("Department not found with id: " + id);
                            }
                    );
            department.setName(departmentDTO.getName());
            department.setEmployeeList(employeeRepository.findAllById(departmentDTO.getEmployeeIds()));
            Department savedDepartment = departmentRepository.save(department);
            setDepartmentIdToEmployees(departmentDTO.getEmployeeIds(), savedDepartment);
            return convertToDTO(savedDepartment);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Transactional
    public void deleteDepartmentById(Long id) {
        try {
            if (!departmentRepository.existsById(id)) {
                throw new RuntimeException("Couldn't find department with id " + id);
            }
            departmentRepository.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private List<Employee> setDepartmentIdToEmployees(List<Long> ids, Department department) {
        List<Employee> employeeList = employeeRepository.findAllById(ids);
        for (Employee emp : employeeList) {
            emp.setDepartment(department);
        }
        return employeeRepository.saveAll(employeeList);
    }

    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setEmployeeIds(department.getEmployeeList().stream().map(Employee::getId).toList());
        return departmentDTO;
    }
}
