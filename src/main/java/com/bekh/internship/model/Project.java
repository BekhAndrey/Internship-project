package com.bekh.internship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Project {
  @Schema(description = "Unique identifier of the project.", example = "1", required = true)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(description = "Title of the project.", example = "Ariba", required = true)
  private String title;

  @Schema(description = "Start date of the project.", required = true)
  @Column(name = "start_date")
  private LocalDate startDate;

  @Schema(description = "End date of the project.", required = true)
  @Column(name = "end_date")
  private LocalDate endDate;

  @JsonIgnore
  @OneToMany(mappedBy = "projectId")
  private Set<EmployeeProject> employeeProjects;
}
