package com.bekh.internship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Department {
  @Schema(description = "Unique identifier of the Department.", example = "1", required = true)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(description = "Title of the department.", example = "Mobile & Web", required = true)
  @NotBlank
  private String title;

  @JsonIgnore
  @OneToMany(mappedBy = "departmentId")
  private Set<Employee> employees;
}
