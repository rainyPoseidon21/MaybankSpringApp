package com.maybank.demo.controller;

import com.maybank.demo.dto.EmployeeDTO;
import com.maybank.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getEmployees(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<EmployeeDTO> employees = employeeService.getEmployees(pageable);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employeeDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO empDTO = employeeService.addEmployee(employeeDTO);
            return new ResponseEntity<>(empDTO, HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO empDTO = employeeService.updateEmployeeById(id, employeeDTO);
            return new ResponseEntity<>(empDTO, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable long id) {
        try {
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
