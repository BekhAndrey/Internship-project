package com.bekh.internship.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
  @JsonIgnore
  private Long id;

  @Schema(description = "First name of the employee.", example = "Andrey", required = true)
  private String firstName;

  @Schema(description = "Last name of the employee.", example = "Bekh", required = true)
  private String lastName;

  @Schema(description = "Email of the employee.", example = "test@gmail.com", required = true)
  private String email;

  @Schema(description = "Password of the employee.", example = "Notqwerty", required = true)
  private String password;

  @Schema(description = "Position of the employee.", example = "Java Developer", required = true)
  private String position;

  @Schema(description = "Department of the employee.", example = "Mobile & Web", required = true)
  private String departmentTitle;
}
