package com.bekh.internship.controller;

import com.bekh.internship.dto.EmployeeRequestDto;
import com.bekh.internship.dto.EmployeeResponseDto;
import com.bekh.internship.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "employee", description = "Employee API")
@AllArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

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
                @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDto.class))))
      })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<EmployeeResponseDto> getAll() {
    return employeeService.findAll();
  }

  @Operation(
      summary = "Create employee",
      description = "Creates new employee",
      tags = {"employee"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = EmployeeResponseDto.class)))
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EmployeeResponseDto create(@RequestBody EmployeeRequestDto requestEmployeeDto) {
    return employeeService.save(requestEmployeeDto);
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
            content = @Content(schema = @Schema(implementation = EmployeeResponseDto.class)))
      })
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EmployeeResponseDto update(@PathVariable Long id, @RequestBody EmployeeRequestDto requestEmployeeDto) {
    requestEmployeeDto.setId(id);
    return employeeService.update(requestEmployeeDto);
  }

  @Operation(
      summary = "Delete employee",
      description = "Delete existing employee",
      tags = {"employee"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id) {
    employeeService.deleteById(id);
  }
}
