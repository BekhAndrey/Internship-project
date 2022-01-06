package com.bekh.internship.controller;

import com.bekh.internship.model.Employee;
import com.bekh.internship.model.Project;
import com.bekh.internship.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@Tag(name = "project", description = "Project API")
public class ProjectController {

  private final ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @Operation(
          summary = "Find all projects",
          description = "Returns the list of all available projects",
          tags = {"project"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content =
                          @Content(array = @ArraySchema(schema = @Schema(implementation = Project.class))))
          })
  @GetMapping("/")
  public ResponseEntity<List<Project>> getProjects() {
    return ResponseEntity.ok(projectService.findAll());
  }

  @Operation(
          summary = "Create project",
          description = "Creates new project",
          tags = {"project"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content = @Content(schema = @Schema(implementation = Project.class)))
          })
  @PostMapping("/")
  public ResponseEntity<Project> createProject(@RequestBody Project project) {
    projectService.save(project);
    return ResponseEntity.ok(project);
  }

  @Operation(
          summary = "Update project",
          description = "Update existing project",
          tags = {"project"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content = @Content(schema = @Schema(implementation = Project.class)))
          })
  @PutMapping("/")
  public ResponseEntity<Project> updateProject(@RequestBody Project project) {
    projectService.update(project);
    return ResponseEntity.ok(project);
  }

  @Operation(
          summary = "Delete project",
          description = "Delete existing project",
          tags = {"project"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @DeleteMapping("/{id}")
  public void deleteProject(@PathVariable Long id) {
    projectService.deleteById(id);
  }
}
