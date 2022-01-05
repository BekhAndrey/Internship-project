package com.bekh.internship.service;

import com.bekh.internship.model.Department;

import java.util.List;

public interface DepartmentService {
  void save(Department department);

  void delete(Department department);

  void deleteById(Long id);

  List<Department> findAll();

  Department findById(Long id);
}
