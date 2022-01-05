package com.bekh.internship.service;

import com.bekh.internship.model.Department;
import com.bekh.internship.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectService {
  void save(Project project);

  void update(Project project);

  void delete(Project project);

  void deleteById(Long id);

  List<Project> findAll();

  Project findById(Long id);
}
