package com.bekh.internship.service.impl;

import com.bekh.internship.model.Project;
import com.bekh.internship.repository.ProjectRepository;
import com.bekh.internship.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

  @Autowired private ProjectRepository projectRepository;

  @Override
  public void save(Project project) {
    projectRepository.save(project);
  }

  @Override
  public void update(Project project) {
    Project projectToUpdate =
        projectRepository.findById(project.getId()).orElseThrow(EntityNotFoundException::new);
    projectToUpdate.setTitle(project.getTitle());
    projectToUpdate.setStartDate(project.getStartDate());
    projectToUpdate.setEndDate(project.getEndDate());
    projectRepository.save(projectToUpdate);
  }

  @Override
  public void delete(Project project) {
    projectRepository.delete(project);
  }

  @Override
  public void deleteById(Long id) {
    projectRepository.deleteById(id);
  }

  @Override
  public List<Project> findAll() {
    return projectRepository.findAll();
  }

  @Override
  public Project findById(Long id) {
    return projectRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Project with such id does not exist"));
  }
}
