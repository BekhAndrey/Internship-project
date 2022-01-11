package com.bekh.internship.repository;

import com.bekh.internship.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
  void deleteById(Long id);

  Optional<Department> findByTitle(String title);
}
