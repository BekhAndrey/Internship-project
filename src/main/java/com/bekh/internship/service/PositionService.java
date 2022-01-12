package com.bekh.internship.service;

import com.bekh.internship.dto.PositionDto;
import com.bekh.internship.dto.RequestEmployeeDto;
import com.bekh.internship.dto.ResponseEmployeeDto;
import com.bekh.internship.model.Employee;
import com.bekh.internship.model.Position;

import java.util.List;

public interface PositionService {
  PositionDto save(PositionDto positionDto);

  PositionDto update(PositionDto positionDto);

  Position mapToEntity(PositionDto positionDto);

  PositionDto mapToDto(Position position);

  void deleteById(Long id);

  List<PositionDto> findAll();

  PositionDto findById(Long id);
}
