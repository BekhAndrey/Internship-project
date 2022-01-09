package com.bekh.internship.service;

import com.bekh.internship.dto.DepartmentDto;
import com.bekh.internship.model.Department;

import java.util.List;

public interface DepartmentService {
  DepartmentDto save(DepartmentDto departmentDto);

  DepartmentDto update(DepartmentDto departmentDto);


  DepartmentDto mapToDto(Department departmentDto);

  Department mapToEntity(DepartmentDto departmentDto);

  DepartmentDto findByTitle(String title);

  void deleteById(Long id);

  List<DepartmentDto> findAll();

  DepartmentDto findById(Long id);
}
