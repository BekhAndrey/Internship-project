package com.bekh.internship.service.impl;

import com.bekh.internship.model.EmployeeProject;
import com.bekh.internship.repository.EmployeeProjectRepository;
import com.bekh.internship.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("employeeProjectService")
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

  @Autowired private EmployeeProjectRepository employeeProjectRepository;

  @Override
  public void save(EmployeeProject employeeProject) {
    employeeProjectRepository.save(employeeProject);
  }

  @Override
  public void update(EmployeeProject employeeProject) {
    EmployeeProject employeeProjectToUpdate =
        employeeProjectRepository
            .findById(employeeProject.getId())
            .orElseThrow(EntityNotFoundException::new);
    employeeProjectToUpdate.setPositionStartDate(employeeProject.getPositionStartDate());
    employeeProjectToUpdate.setPositionEndDate(employeeProject.getPositionEndDate());
    employeeProjectRepository.save(employeeProjectToUpdate);
  }

  @Override
  public void delete(EmployeeProject employeeProject) {
    employeeProjectRepository.delete(employeeProject);
  }

  @Override
  public void deleteById(Long id) {
    employeeProjectRepository.deleteById(id);
  }

  @Override
  public List<EmployeeProject> findAll() {
    return employeeProjectRepository.findAll();
  }

  @Override
  public EmployeeProject findById(Long id) {
    return employeeProjectRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee-project with such id does not exist"));
  }
}
