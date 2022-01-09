package com.bekh.internship.dto;

import com.bekh.internship.model.Department;
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
  @Schema(description = "Unique identifier of the Department.", example = "1", required = true)
  private Long id;

  @Schema(description = "Title of the department.", example = "Mobile & Web", required = true)
  private String title;
}
