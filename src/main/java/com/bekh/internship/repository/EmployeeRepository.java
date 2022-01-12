package com.bekh.internship.repository;

import com.bekh.internship.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  void deleteById(Long id);

  Optional<Employee> findByEmail(String email);
}
