package com.bekh.internship.controller;

import com.bekh.internship.model.Department;
import com.bekh.internship.service.DepartmentService;
import com.bekh.internship.service.impl.DepartmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@Tag(name = "department", description = "Department API")
public class DepartmentController {

  private final DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @Operation(
      summary = "Find all departments",
      description = "Returns the list of all available departments",
      tags = {"department"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = Department.class))))
      })
  @GetMapping("/")
  public ResponseEntity<List<Department>> getDepartments() {
    return ResponseEntity.ok(departmentService.findAll());
  }

  @Operation(
      summary = "Create department",
      description = "Creates new department",
      tags = {"department"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = Department.class)))
      })
  @PostMapping("/")
  public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
    departmentService.save(department);
    return ResponseEntity.ok(department);
  }

  @Operation(
      summary = "Update department",
      description = "Update existing department",
      tags = {"department"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = Department.class)))
      })
  @PutMapping("/")
  public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
    Department departmentToUpdate = departmentService.findById(department.getId());
    departmentToUpdate.setTitle(department.getTitle());
    departmentService.save(departmentToUpdate);
    return ResponseEntity.ok(departmentToUpdate);
  }

  @Operation(
      summary = "Delete department",
      description = "Delete existing department",
      tags = {"department"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @DeleteMapping("/{id}")
  public void deleteDepartment(@PathVariable Long id) {
    departmentService.deleteById(id);
  }
}
