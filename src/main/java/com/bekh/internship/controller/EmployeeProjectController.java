package com.bekh.internship.controller;

import com.bekh.internship.model.Employee;
import com.bekh.internship.model.EmployeeProject;
import com.bekh.internship.model.Project;
import com.bekh.internship.service.EmployeeProjectService;
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
@RequestMapping("/employee-project")
@Tag(name = "employeeProject", description = "Employee-project API")
public class EmployeeProjectController {

  private final EmployeeProjectService employeeProjectService;

  public EmployeeProjectController(EmployeeProjectService employeeProjectService) {
    this.employeeProjectService = employeeProjectService;
  }

  @Operation(
          summary = "Find all employee-project records",
          description = "Returns the list of all employee-project records",
          tags = {"employeeProject"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content =
                          @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeProject.class))))
          })
  @GetMapping("/")
  public ResponseEntity<List<EmployeeProject>> getProjects() {
    return ResponseEntity.ok(employeeProjectService.findAll());
  }

  @Operation(
          summary = "Create employee-project record",
          description = "Creates new employee-project record",
          tags = {"employeeProject"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content = @Content(schema = @Schema(implementation = EmployeeProject.class)))
          })
  @PostMapping("/")
  public ResponseEntity<EmployeeProject> createProject(
      @RequestBody EmployeeProject employeeProject) {
    employeeProjectService.save(employeeProject);
    return ResponseEntity.ok(employeeProject);
  }

  @Operation(
          summary = "Update employee-project record",
          description = "Update existing employee-project record",
          tags = {"employeeProject"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content = @Content(schema = @Schema(implementation = EmployeeProject.class)))
          })
  @PutMapping("/")
  public ResponseEntity<EmployeeProject> updateProject(
      @RequestBody EmployeeProject employeeProject) {
    employeeProjectService.update(employeeProject);
    return ResponseEntity.ok(employeeProject);
  }

  @Operation(
          summary = "Delete employee-project record",
          description = "Delete existing employee-project record",
          tags = {"employeeProject"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @DeleteMapping("/{id}")
  public void deleteProject(@PathVariable Long id) {
    employeeProjectService.deleteById(id);
  }
}
