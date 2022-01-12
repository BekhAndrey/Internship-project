package com.bekh.internship.service.impl;

import com.bekh.internship.dto.EmployeeRequestDto;
import com.bekh.internship.dto.EmployeeResponseDto;
import com.bekh.internship.mapper.EmployeeMapper;
import com.bekh.internship.model.Employee;
import com.bekh.internship.repository.EmployeeRepository;
import com.bekh.internship.service.DepartmentService;
import com.bekh.internship.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Cannot find such employee";
  private final EmployeeRepository employeeRepository;
  private final DepartmentService departmentService;

  @Override
  public EmployeeResponseDto save(EmployeeRequestDto requestEmployeeDto) {
    return EmployeeMapper.mapToResponseDto(
        employeeRepository.save(
            EmployeeMapper.mapToEntity(
                requestEmployeeDto,
                departmentService.findByTitle(requestEmployeeDto.getDepartmentTitle()))));
  }

  @Override
  public EmployeeResponseDto update(EmployeeRequestDto requestEmployeeDto) {
    Employee employeeToUpdate =
        employeeRepository
            .findById(requestEmployeeDto.getId())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such department"));
    employeeToUpdate.setFirstName(requestEmployeeDto.getFirstName());
    employeeToUpdate.setLastName(requestEmployeeDto.getLastName());
    employeeToUpdate.setEmail(requestEmployeeDto.getEmail());
    employeeToUpdate.setJobTitle(requestEmployeeDto.getPosition());
    return EmployeeMapper.mapToResponseDto(employeeRepository.save(employeeToUpdate));
  }

  @Override
  public void deleteById(Long id) {
    employeeRepository.deleteById(id);
  }

  @Override
  public List<EmployeeResponseDto> findAll() {
    return employeeRepository.findAll().stream().map(EmployeeMapper::mapToResponseDto).collect(toList());
  }

  @Override
  public EmployeeResponseDto findById(Long id) {
    return EmployeeMapper.mapToResponseDto(
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE)));
  }

  @Override
  public EmployeeResponseDto findByEmail(String email) {
    return EmployeeMapper.mapToResponseDto(
        employeeRepository
            .findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE)));
  }

  @Override
  public EmployeeRequestDto getEmployeeRequestDtoByEmail(String email) {
    return EmployeeMapper.mapToRequestDto(
            employeeRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException(EMPLOYEE_NOT_FOUND_MESSAGE)));
  }
}
