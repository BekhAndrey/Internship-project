package com.bekh.internship.service;

import com.bekh.internship.dto.PositionDto;
import com.bekh.internship.model.ProjectPosition;

import java.util.List;

public interface PositionService {
  PositionDto save(PositionDto positionDto);

  PositionDto update(PositionDto positionDto);

  void deleteById(Long id);

  List<PositionDto> findAll();

  PositionDto findById(Long id);
}
