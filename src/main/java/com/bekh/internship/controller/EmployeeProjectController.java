package com.bekh.internship.controller;

import com.bekh.internship.model.EmployeeProject;
import com.bekh.internship.model.Project;
import com.bekh.internship.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-project")
public class EmployeeProjectController {
  @Autowired private EmployeeProjectService employeeProjectService;

  @GetMapping("/")
  public ResponseEntity<List<EmployeeProject>> getProjects() {
    return ResponseEntity.ok(employeeProjectService.findAll());
  }

  @PostMapping("/")
  public ResponseEntity<EmployeeProject> createProject(@RequestBody EmployeeProject employeeProject) {
    employeeProjectService.save(employeeProject);
    return ResponseEntity.ok(employeeProject);
  }

  @PutMapping("/")
  public ResponseEntity<EmployeeProject> updateProject(@RequestBody EmployeeProject employeeProject) {
    employeeProjectService.update(employeeProject);
    return ResponseEntity.ok(employeeProject);
  }

  @DeleteMapping("/{id}")
  public void deleteProject(@PathVariable Long id) {
    employeeProjectService.deleteById(id);
  }
}
