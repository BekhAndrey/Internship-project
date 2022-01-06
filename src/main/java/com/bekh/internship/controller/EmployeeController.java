package com.bekh.internship.controller;

import com.bekh.internship.model.Employee;
import com.bekh.internship.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "employee", description = "Employee API")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Operation(
          summary = "Find all employees",
          description = "Returns the list of all available employees",
          tags = {"employee"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content =
                          @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
          })
  @GetMapping("/")
  public ResponseEntity<List<Employee>> getEmployees() {
    return ResponseEntity.ok(employeeService.findAll());
  }

  @Operation(
          summary = "Create employee",
          description = "Creates new employee",
          tags = {"employee"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content = @Content(schema = @Schema(implementation = Employee.class)))
          })
  @PostMapping("/")
  public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    employeeService.save(employee);
    return ResponseEntity.ok(employee);
  }

  @Operation(
          summary = "Update employee",
          description = "Update existing employee",
          tags = {"employee"})
  @ApiResponses(
          value = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          content = @Content(schema = @Schema(implementation = Employee.class)))
          })
  @PutMapping("/")
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    employeeService.update(employee);
    return ResponseEntity.ok(employee);
  }

  @Operation(
          summary = "Delete employee",
          description = "Delete existing employee",
          tags = {"employee"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable Long id) {
    employeeService.deleteById(id);
  }
}
