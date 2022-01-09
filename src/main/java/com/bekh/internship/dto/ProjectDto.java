package com.bekh.internship.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
  @Schema(description = "Unique identifier of the project.", example = "1", required = true)
  private Long id;

  @Schema(description = "Title of the project.", example = "Ariba", required = true)
  private String title;

  @Schema(description = "Start date of the project.", required = true)
  private LocalDate startDate;

  @Schema(description = "End date of the project.", required = true)
  private LocalDate endDate;
}
