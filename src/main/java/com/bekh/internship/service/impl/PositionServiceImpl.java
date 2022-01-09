package com.bekh.internship.service.impl;

import com.bekh.internship.dto.PositionDto;
import com.bekh.internship.model.Position;
import com.bekh.internship.repository.EmployeeRepository;
import com.bekh.internship.repository.PositionRepository;
import com.bekh.internship.repository.ProjectRepository;
import com.bekh.internship.service.EmployeeService;
import com.bekh.internship.service.PositionService;
import com.bekh.internship.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

  private final PositionRepository positionRepository;
  private final EmployeeRepository employeeRepository;
  private final ProjectRepository projectRepository;

  @Override
  public PositionDto save(PositionDto positionDto) {
    return mapToDto(positionRepository.save(mapToEntity(positionDto)));
  }

  @Override
  public PositionDto update(PositionDto positionDto) {
    Position employeeProjectToUpdate =
        positionRepository
            .findById(positionDto.getId())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such employee"));
    employeeProjectToUpdate.setPositionStartDate(positionDto.getPositionStartDate());
    employeeProjectToUpdate.setPositionEndDate(positionDto.getPositionEndDate());
    return mapToDto(positionRepository.save(employeeProjectToUpdate));
  }

  @Override
  public Position mapToEntity(PositionDto positionDto) {
    Position position = new Position();
    position.setId(positionDto.getId());
    position.setEmployeeId(
        employeeRepository
            .findByEmail(positionDto.getEmployeeEmail())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such employee")));
    position.setProjectId(
        projectRepository
            .findByTitle(positionDto.getProjectTitle())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such project")));
    position.setPositionStartDate(positionDto.getPositionStartDate());
    position.setPositionEndDate(positionDto.getPositionEndDate());
    return position;
  }

  @Override
  public PositionDto mapToDto(Position position) {
    PositionDto positionDto = new PositionDto();
    positionDto.setId(position.getId());
    positionDto.setEmployeeEmail(
        employeeRepository
            .findByEmail(position.getEmployeeId().getEmail())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such employee"))
            .getEmail());
    positionDto.setProjectTitle(
        projectRepository
            .findByTitle(position.getProjectId().getTitle())
            .orElseThrow(() -> new EntityNotFoundException("Cannot find such project"))
            .getTitle());
    positionDto.setPositionStartDate(position.getPositionStartDate());
    positionDto.setPositionEndDate(position.getPositionEndDate());
    return positionDto;
  }

  @Override
  public void deleteById(Long id) {
    positionRepository.deleteById(id);
  }

  @Override
  public List<PositionDto> findAll() {
    return positionRepository.findAll().stream().map(this::mapToDto).collect(toList());
  }

  @Override
  public PositionDto findById(Long id) {
    return mapToDto(
        positionRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Employee-project with such id does not exist")));
  }
}
