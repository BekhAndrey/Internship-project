package com.bekh.internship.service.impl;

import com.bekh.internship.model.Employee;
import com.bekh.internship.repository.EmployeeRepository;
import com.bekh.internship.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired private EmployeeRepository employeeRepository;

  @Override
  public void save(Employee employee) {
    employeeRepository.save(employee);
  }

  @Override
  public void update(Employee employee) {
    Employee employeeToUpdate =
        employeeRepository.findById(employee.getId()).orElseThrow(EntityNotFoundException::new);
    employeeToUpdate.setFirstName(employee.getFirstName());
    employeeToUpdate.setLastName(employee.getLastName());
    employeeToUpdate.setEmail(employee.getEmail());
    employeeToUpdate.setPosition(employee.getPosition());
    employeeRepository.save(employeeToUpdate);
  }

  @Override
  public void delete(Employee employee) {
    employeeRepository.delete(employee);
  }

  @Override
  public void deleteById(Long id) {
    employeeRepository.findById(id);
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee findById(Long id) {
    return employeeRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee with such id does not exist"));
  }
}
