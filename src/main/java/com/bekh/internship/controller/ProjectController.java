package com.bekh.internship.controller;

import com.bekh.internship.model.Employee;
import com.bekh.internship.model.Project;
import com.bekh.internship.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        projectService.save(project);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        projectService.update(project);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
    }
}
