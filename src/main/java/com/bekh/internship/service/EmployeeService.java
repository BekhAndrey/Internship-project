package com.bekh.internship.service;

import com.bekh.internship.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
  void save(Employee employee);

  void update(Employee employee);

  void delete(Employee employee);

  void deleteById(Long id);

  List<Employee> findAll();

  Employee findById(Long id);
}
