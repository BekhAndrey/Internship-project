package com.bekh.internship.repository;

import com.bekh.internship.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    void deleteById(Long id);
}
