package com.bekh.internship.service.impl;

import com.bekh.internship.dto.PositionDto;
import com.bekh.internship.mapper.ProjectPositionMapper;
import com.bekh.internship.model.ProjectPosition;
import com.bekh.internship.repository.PositionRepository;
import com.bekh.internship.service.DepartmentService;
import com.bekh.internship.service.EmployeeService;
import com.bekh.internship.service.PositionService;
import com.bekh.internship.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.Position;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

  private final PositionRepository positionRepository;
  private final EmployeeService employeeService;
  private final DepartmentService departmentService;
  private final ProjectService projectService;

  @Override
  public PositionDto save(PositionDto positionDto) {
    return ProjectPositionMapper.mapToDto(
        positionRepository.save(
            ProjectPositionMapper.mapToEntity(
                positionDto,
                employeeService.getEmployeeRequestDtoByEmail(positionDto.getEmployeeEmail()),
                departmentService.findByTitle(
                    employeeService
                        .findByEmail(positionDto.getEmployeeEmail())
                        .getDepartmentTitle()),
                projectService.findByTitle(positionDto.getProjectTitle()))),
        positionDto.getEmployeeEmail(),
        positionDto.getProjectTitle());
  }

  @Override
  public PositionDto update(PositionDto positionDto) {
    ProjectPosition employeeProjectToUpdate =
        positionRepository
            .findById(positionDto.getId())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such employee"));
    employeeProjectToUpdate.setPositionStartDate(positionDto.getPositionStartDate());
    employeeProjectToUpdate.setPositionEndDate(positionDto.getPositionEndDate());
    return ProjectPositionMapper.mapToDto(
        positionRepository.save(employeeProjectToUpdate),
        positionDto.getEmployeeEmail(),
        positionDto.getProjectTitle());
  }

  @Override
  public void deleteById(Long id) {
    positionRepository.deleteById(id);
  }

  @Override
  public List<PositionDto> findAll() {
    return positionRepository.findAll().stream()
        .map(
            x ->
                ProjectPositionMapper.mapToDto(
                    x, x.getEmployee().getEmail(), x.getProject().getTitle()))
        .collect(toList());
  }

  @Override
  public PositionDto findById(Long id) {
    ProjectPosition projectPosition =
        positionRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Employee-project with such id does not exist"));
    return ProjectPositionMapper.mapToDto(
        projectPosition,
        projectPosition.getEmployee().getEmail(),
        projectPosition.getProject().getTitle());
  }
}
