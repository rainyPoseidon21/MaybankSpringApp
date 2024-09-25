package com.maybank.demo.controller;

import com.maybank.demo.dto.DepartmentDTO;
import com.maybank.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        List<DepartmentDTO> departmentDTOS = departmentService.getDepartments();
        return ResponseEntity.ok(departmentDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        try {
            DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
            return ResponseEntity.ok(departmentDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            DepartmentDTO DeptDTO = departmentService.addDepartment(departmentDTO);
            return new ResponseEntity<>(DeptDTO, HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartmentById(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        try {
            DepartmentDTO returnedDepartmentDTO = departmentService.updateDepartmentById(departmentDTO, id);
            return new ResponseEntity<>(returnedDepartmentDTO, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable Long id) {
        try {
            departmentService.deleteDepartmentById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
