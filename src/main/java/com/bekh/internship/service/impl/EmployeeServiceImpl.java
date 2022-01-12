package com.bekh.internship.service.impl;

import com.bekh.internship.dto.RequestEmployeeDto;
import com.bekh.internship.dto.ResponseEmployeeDto;
import com.bekh.internship.model.Employee;
import com.bekh.internship.repository.DepartmentRepository;
import com.bekh.internship.repository.EmployeeRepository;
import com.bekh.internship.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Cannot find such employee";
  private final EmployeeRepository employeeRepository;
  private final DepartmentRepository departmentRepository;

  @Override
  public ResponseEmployeeDto save(RequestEmployeeDto requestEmployeeDto) {
    return mapToResponseDto(employeeRepository.save(mapToEntity(requestEmployeeDto)));
  }

  @Override
  public ResponseEmployeeDto update(RequestEmployeeDto requestEmployeeDto) {
    Employee employeeToUpdate =
        employeeRepository
            .findById(requestEmployeeDto.getId())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such department"));
    employeeToUpdate.setFirstName(requestEmployeeDto.getFirstName());
    employeeToUpdate.setLastName(requestEmployeeDto.getLastName());
    employeeToUpdate.setEmail(requestEmployeeDto.getEmail());
    employeeToUpdate.setPosition(requestEmployeeDto.getPosition());
    return mapToResponseDto(employeeRepository.save(employeeToUpdate));
  }

  @Override
  public Employee mapToEntity(RequestEmployeeDto requestEmployeeDto) {
    Employee employee = new Employee();
    employee.setId(requestEmployeeDto.getId());
    employee.setFirstName(requestEmployeeDto.getFirstName());
    employee.setLastName(requestEmployeeDto.getLastName());
    employee.setEmail(requestEmployeeDto.getEmail());
    employee.setPassword(requestEmployeeDto.getPassword());
    employee.setPosition(requestEmployeeDto.getPosition());
    employee.setDepartmentId(
        departmentRepository
            .findByTitle(requestEmployeeDto.getDepartmentTitle())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such department")));
    return employee;
  }

  @Override
  public ResponseEmployeeDto mapToResponseDto(Employee employee) {
    ResponseEmployeeDto responseEmployeeDto = new ResponseEmployeeDto();
    responseEmployeeDto.setId(employee.getId());
    responseEmployeeDto.setFirstName(employee.getFirstName());
    responseEmployeeDto.setLastName(employee.getLastName());
    responseEmployeeDto.setEmail(employee.getEmail());
    responseEmployeeDto.setPosition(employee.getPosition());
    responseEmployeeDto.setDepartmentTitle(employee.getDepartmentId().getTitle());
    return responseEmployeeDto;
  }

  @Override
  public ResponseEmployeeDto mapToResponseDto(RequestEmployeeDto requestEmployeeDto) {
    ResponseEmployeeDto responseEmployeeDto = new ResponseEmployeeDto();
    responseEmployeeDto.setId(requestEmployeeDto.getId());
    responseEmployeeDto.setFirstName(requestEmployeeDto.getFirstName());
    responseEmployeeDto.setLastName(requestEmployeeDto.getLastName());
    responseEmployeeDto.setEmail(requestEmployeeDto.getEmail());
    responseEmployeeDto.setPosition(requestEmployeeDto.getPosition());
    responseEmployeeDto.setDepartmentTitle(requestEmployeeDto.getDepartmentTitle());
    return responseEmployeeDto;
  }

  @Override
  public void deleteById(Long id) {
    employeeRepository.deleteById(id);
  }

  @Override
  public List<ResponseEmployeeDto> findAll() {
    return employeeRepository.findAll().stream().map(this::mapToResponseDto).collect(toList());
  }

  @Override
  public ResponseEmployeeDto findById(Long id) {
    return mapToResponseDto(
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE)));
  }

  @Override
  public ResponseEmployeeDto findByEmail(String email) {
    return mapToResponseDto(
        employeeRepository
            .findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE)));
  }
}
