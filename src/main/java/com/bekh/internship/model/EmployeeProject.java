package com.bekh.internship.model;

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
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employeeId;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project projectId;

  @Column(name = "position_start_date")
  private LocalDate positionStartDate;

  @Column(name = "position_end_date")
  private LocalDate positionEndDate;
}
