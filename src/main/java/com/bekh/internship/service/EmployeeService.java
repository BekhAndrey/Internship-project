package com.bekh.internship.service;

import com.bekh.internship.dto.EmployeeRequestDto;
import com.bekh.internship.dto.EmployeeResponseDto;
import com.bekh.internship.model.Employee;

import java.util.List;

public interface EmployeeService {
  EmployeeResponseDto save(EmployeeRequestDto employee);

  EmployeeResponseDto update(EmployeeRequestDto employee);

  void deleteById(Long id);

  List<EmployeeResponseDto> findAll();

  EmployeeResponseDto findById(Long id);

  EmployeeResponseDto findByEmail(String email);

  EmployeeRequestDto getEmployeeRequestDtoByEmail(String email);
}
