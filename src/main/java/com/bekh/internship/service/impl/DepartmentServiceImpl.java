package com.bekh.internship.service.impl;

import com.bekh.internship.model.Department;
import com.bekh.internship.repository.DepartmentRepository;
import com.bekh.internship.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired private DepartmentRepository departmentRepository;

  @Override
  public void save(Department department) {
    departmentRepository.save(department);
  }

  @Override
  public void delete(Department department) {
    departmentRepository.delete(department);
  }

  @Override
  public void deleteById(Long id) {
    departmentRepository.deleteById(id);
  }

  @Override
  public List<Department> findAll() {
    return departmentRepository.findAll();
  }

  @Override
  public Department findById(Long id) {
    return departmentRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Department with such id does not exist"));
  }
}
