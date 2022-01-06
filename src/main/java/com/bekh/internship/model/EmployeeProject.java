package com.bekh.internship.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee_project")
public class EmployeeProject {
  @Schema(description = "Unique identifier of the employee-project record.", example = "1", required = true)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(description = "Unique identifier of the employee.", required = true)
  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employeeId;

  @Schema(description = "Unique identifier of the project.", required = true)
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project projectId;

  @Schema(description = "Start date of the employee on project.", required = true)
  @Column(name = "position_start_date")
  private LocalDate positionStartDate;

  @Schema(description = "End date of the employee on project.", required = true)
  @Column(name = "position_end_date")
  private LocalDate positionEndDate;
}
