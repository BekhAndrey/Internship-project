package com.bekh.internship.service.impl;

import com.bekh.internship.dto.ProjectDto;
import com.bekh.internship.mapper.ProjectMapper;
import com.bekh.internship.model.Project;
import com.bekh.internship.repository.DepartmentRepository;
import com.bekh.internship.repository.ProjectRepository;
import com.bekh.internship.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private static final String PROJECT_NOT_FOUND_MESSAGE = "Cannot find such project";
  private final ProjectRepository projectRepository;

  @Override
  public ProjectDto save(ProjectDto projectDto) {
    return ProjectMapper.mapToDto(projectRepository.save(ProjectMapper.mapToEntity(projectDto)));
  }

  @Override
  public ProjectDto update(ProjectDto projectDto) {
    Project projectToUpdate =
        projectRepository.findById(projectDto.getId()).orElseThrow(EntityNotFoundException::new);
    projectToUpdate.setTitle(projectDto.getTitle());
    projectToUpdate.setStartDate(projectDto.getStartDate());
    projectToUpdate.setEndDate(projectDto.getEndDate());
    return ProjectMapper.mapToDto(projectRepository.save(projectToUpdate));
  }

  @Override
  public void deleteById(Long id) {
    projectRepository.deleteById(id);
  }

  @Override
  public List<ProjectDto> findAll() {
    return projectRepository.findAll().stream().map(ProjectMapper::mapToDto).collect(toList());
  }

  @Override
  public ProjectDto findById(Long id) {
    return ProjectMapper.mapToDto(projectRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND_MESSAGE)));
  }

  @Override
  public ProjectDto findByTitle(String title) {
    return ProjectMapper.mapToDto(projectRepository
            .findByTitle(title)
            .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND_MESSAGE)));
  }
}
