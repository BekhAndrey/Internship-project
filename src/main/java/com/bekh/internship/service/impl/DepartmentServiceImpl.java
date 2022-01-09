package com.bekh.internship.service.impl;

import com.bekh.internship.dto.DepartmentDto;
import com.bekh.internship.model.Department;
import com.bekh.internship.repository.DepartmentRepository;
import com.bekh.internship.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  public DepartmentDto save(DepartmentDto departmentDto) {
    return mapToDto(departmentRepository.save(mapToEntity(departmentDto)));
  }

  @Override
  public DepartmentDto update(DepartmentDto departmentDto) {
    Department departmentToUpdate =
        departmentRepository
            .findById(departmentDto.getId())
            .orElseThrow(
                () -> new EntityNotFoundException("Department with such id does not exist"));
    departmentToUpdate.setTitle(departmentDto.getTitle());
    return mapToDto(departmentRepository.save(departmentToUpdate));
  }

  @Override
  public DepartmentDto mapToDto(Department department) {
    return new DepartmentDto(department.getId(), department.getTitle());
  }

  @Override
  public Department mapToEntity(DepartmentDto departmentDto) {
    Department department = new Department();
    department.setId(departmentDto.getId());
    department.setTitle(departmentDto.getTitle());
    return department;
  }

  @Override
  public DepartmentDto findByTitle(String title) {
    return mapToDto(
        departmentRepository
            .findByTitle(title)
            .orElseThrow(
                () -> new EntityNotFoundException("Department with such id does not exist")));
  }

  @Override
  public void deleteById(Long id) {
    departmentRepository.deleteById(id);
  }

  @Override
  public List<DepartmentDto> findAll() {
    return departmentRepository.findAll().stream().map(this::mapToDto).collect(toList());
  }

  @Override
  public DepartmentDto findById(Long id) {
    return mapToDto(
        departmentRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Department with such id does not exist")));
  }
}
