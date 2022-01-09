package com.bekh.internship.controller;

import com.bekh.internship.dto.PositionDto;
import com.bekh.internship.model.Position;
import com.bekh.internship.service.PositionService;
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
@RequestMapping("/positions")
@Tag(name = "position", description = "Position API")
@AllArgsConstructor
public class PositionController {

  private final PositionService positionService;

  @Operation(
      summary = "Find all positions",
      description = "Returns the list of all positions",
      tags = {"position"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = PositionDto.class))))
      })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PositionDto> getAll() {
    return positionService.findAll();
  }

  @Operation(
      summary = "Create position",
      description = "Creates new position",
      tags = {"position"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = PositionDto.class)))
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PositionDto create(@RequestBody PositionDto positionDto) {
    return positionService.save(positionDto);
  }

  @Operation(
      summary = "Update position record",
      description = "Update existing position",
      tags = {"position"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = PositionDto.class)))
      })
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public PositionDto update(@RequestBody PositionDto positionDto) {
    return positionService.update(positionDto);
  }

  @Operation(
      summary = "Delete position",
      description = "Delete existing position",
      tags = {"position"})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation")})
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id) {
    positionService.deleteById(id);
  }
}
