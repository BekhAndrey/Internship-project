package com.bekh.internship.controller;

import com.bekh.internship.model.Department;
import com.bekh.internship.model.Employee;
import com.bekh.internship.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired private EmployeeService employeeService;

  @GetMapping("/")
  public ResponseEntity<List<Employee>> getEmployees() {
    return ResponseEntity.ok(employeeService.findAll());
  }

  @PostMapping("/")
  public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    employeeService.save(employee);
    return ResponseEntity.ok(employee);
  }

  @PutMapping("/")
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    employeeService.update(employee);
    return ResponseEntity.ok(employee);
  }

  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable Long id) {
    employeeService.deleteById(id);
  }
}
