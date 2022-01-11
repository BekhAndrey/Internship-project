package com.bekh.internship.controller;

import com.bekh.internship.dto.ProjectDto;
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
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@Tag(name = "project", description = "Project API")
@AllArgsConstructor
public class ProjectController {

  private final ProjectService projectService;

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
                @Content(array = @ArraySchema(schema = @Schema(implementation = ProjectDto.class))))
      })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProjectDto> getAll() {
    return projectService.findAll();
  }

  @Operation(
      summary = "Create project",
      description = "Creates new project",
      tags = {"project"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ProjectDto.class)))
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProjectDto create(@RequestBody ProjectDto projectDto) {
    return projectService.save(projectDto);
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
            content = @Content(schema = @Schema(implementation = ProjectDto.class)))
      })
  @PutMapping("/edit/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProjectDto update(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
    projectDto.setId(id);
    return projectService.update(projectDto);
  }

  @Operation(
      summary = "Delete project",
      description = "Delete existing project",
      tags = {"project"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id) {
    projectService.deleteById(id);
  }
}
