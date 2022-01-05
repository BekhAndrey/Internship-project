package com.bekh.internship.controller;

import com.bekh.internship.model.Department;
import com.bekh.internship.service.DepartmentService;
import com.bekh.internship.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

  @Autowired private DepartmentService departmentService;

  @GetMapping("/")
  public ResponseEntity<List<Department>> getDepartments() {
    return ResponseEntity.ok(departmentService.findAll());
  }

  @PostMapping("/")
  public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
    departmentService.save(department);
    return ResponseEntity.ok(department);
  }

  @PutMapping("/")
  public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
    Department departmentToUpdate = departmentService.findById(department.getId());
    departmentToUpdate.setTitle(department.getTitle());
    departmentService.save(departmentToUpdate);
    return ResponseEntity.ok(departmentToUpdate);
  }

  @DeleteMapping("/{id}")
  public void deleteDepartment(@PathVariable Long id) {
    departmentService.deleteById(id);
  }
}
