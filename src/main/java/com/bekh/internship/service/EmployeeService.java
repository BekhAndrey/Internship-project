package com.bekh.internship.service;

import com.bekh.internship.dto.RequestEmployeeDto;
import com.bekh.internship.dto.ResponseEmployeeDto;
import com.bekh.internship.model.Employee;

import java.util.List;

public interface EmployeeService {
  ResponseEmployeeDto save(RequestEmployeeDto employee);

  ResponseEmployeeDto update(RequestEmployeeDto employee);


  Employee mapToEntity(RequestEmployeeDto requestEmployeeDto);

  ResponseEmployeeDto mapToResponseDto(Employee employee);

  ResponseEmployeeDto mapToResponseDto(RequestEmployeeDto requestEmployeeDto);

  void deleteById(Long id);

  List<ResponseEmployeeDto> findAll();

  ResponseEmployeeDto findById(Long id);

  ResponseEmployeeDto findByEmail(String email);
}
