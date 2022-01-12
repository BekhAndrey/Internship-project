package com.bekh.internship.service;

import com.bekh.internship.dto.ProjectDto;
import com.bekh.internship.model.Project;

import java.util.List;

public interface ProjectService {
  ProjectDto save(ProjectDto projectDto);

  ProjectDto update(ProjectDto projectDto);

  void deleteById(Long id);

  List<ProjectDto> findAll();

  ProjectDto findById(Long id);

  ProjectDto findByTitle(String title);
}
