package com.bekh.internship.service.impl;

import com.bekh.internship.dto.DepartmentDto;
import com.bekh.internship.mapper.DepartmentMapper;
import com.bekh.internship.model.Department;
import com.bekh.internship.repository.DepartmentRepository;
import com.bekh.internship.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  public DepartmentDto save(DepartmentDto departmentDto) {
    return DepartmentMapper.mapToDto(
        departmentRepository.save(DepartmentMapper.mapToEntity(departmentDto)));
  }

  @Override
  public DepartmentDto update(DepartmentDto departmentDto) {
    Department departmentToUpdate =
        departmentRepository
            .findById(departmentDto.getId())
            .orElseThrow(
                () -> new EntityNotFoundException("Department with such id does not exist"));
    departmentToUpdate.setTitle(departmentDto.getTitle());
    return DepartmentMapper.mapToDto(departmentRepository.save(departmentToUpdate));
  }

  @Override
  public DepartmentDto findByTitle(String title) {
    return DepartmentMapper.mapToDto(
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
    return departmentRepository.findAll().stream()
        .map(DepartmentMapper::mapToDto)
        .collect(toList());
  }

  @Override
  public DepartmentDto findById(Long id) {
    return DepartmentMapper.mapToDto(
        departmentRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Department with such id does not exist")));
  }
}
