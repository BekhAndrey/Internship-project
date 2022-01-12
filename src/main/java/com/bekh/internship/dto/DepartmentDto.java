package com.bekh.internship.dto;

import com.bekh.internship.model.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDto {
  @Schema(description = "Unique identifier of the Department.", example = "1")
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @Schema(description = "Title of the department.", example = "Mobile & Web", required = true)
  private String title;
}
