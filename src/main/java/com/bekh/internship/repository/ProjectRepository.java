package com.bekh.internship.repository;

import com.bekh.internship.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    void deleteById(Long id);

    Optional<Project> findByTitle(String title);
}
