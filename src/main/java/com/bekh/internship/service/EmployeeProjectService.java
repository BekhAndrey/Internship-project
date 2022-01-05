package com.bekh.internship.service;

import com.bekh.internship.model.Employee;
import com.bekh.internship.model.EmployeeProject;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeProjectService {
  void save(EmployeeProject employeeProject);

  void update(EmployeeProject employeeProject);

  void delete(EmployeeProject employeeProject);

  void deleteById(Long id);

  List<EmployeeProject> findAll();

  EmployeeProject findById(Long id);
}
