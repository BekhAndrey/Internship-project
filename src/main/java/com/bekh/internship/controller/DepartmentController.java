package com.bekh.internship.controller;

import com.bekh.internship.dto.DepartmentDto;
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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@Tag(name = "department", description = "Department API")
@AllArgsConstructor
public class DepartmentController {

  private final DepartmentService departmentService;

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
                @Content(array = @ArraySchema(schema = @Schema(implementation = DepartmentDto.class))))
      })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<DepartmentDto> getAll() {
    return departmentService.findAll();
  }

  @Operation(
      summary = "Create department",
      description = "Creates new department",
      tags = {"department"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = DepartmentDto.class)))
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DepartmentDto create(@RequestBody DepartmentDto departmentDto) {
    return departmentService.save(departmentDto);
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
            content = @Content(schema = @Schema(implementation = DepartmentDto.class)))
      })
  @PutMapping("/edit/{id}")
  @ResponseStatus(HttpStatus.OK)
  public DepartmentDto update(@PathVariable Long id,@RequestBody DepartmentDto departmentDto) {
    departmentDto.setId(id);
    return departmentService.update(departmentDto);
  }

  @Operation(
      summary = "Delete department",
      description = "Delete existing department",
      tags = {"department"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id) {
    departmentService.deleteById(id);
  }
}
