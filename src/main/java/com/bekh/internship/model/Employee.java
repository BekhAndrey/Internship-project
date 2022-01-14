package com.bekh.internship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String email;

  private String password;

  @Column(name = "job_title")
  private String jobTitle;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  @JsonIgnore
  @OneToMany(mappedBy = "employee")
  private List<ProjectPosition> employeeProjects = new ArrayList<>();
}
