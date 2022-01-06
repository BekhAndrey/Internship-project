package com.bekh.internship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
  @Schema(description = "Unique identifier of the Employee.", example = "1", required = true)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(description = "First name of the employee.", example = "Andrey", required = true)
  @Column(name = "first_name")
  private String firstName;

  @Schema(description = "Last name of the employee.", example = "Bekh", required = true)
  @Column(name = "last_name")
  private String lastName;

  @JsonIgnore private String email;
  @JsonIgnore private String password;

  @Schema(description = "Position of the employee.", example = "Java Developer", required = true)
  private String position;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department departmentId;

  @JsonIgnore
  @OneToMany(mappedBy = "employeeId")
  private Set<EmployeeProject> employeeProjects;
}
