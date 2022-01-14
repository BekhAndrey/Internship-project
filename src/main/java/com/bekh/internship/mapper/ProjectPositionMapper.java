package com.bekh.internship.mapper;

import com.bekh.internship.dto.DepartmentDto;
import com.bekh.internship.dto.EmployeeRequestDto;
import com.bekh.internship.dto.PositionDto;
import com.bekh.internship.dto.ProjectDto;
import com.bekh.internship.model.Employee;
import com.bekh.internship.model.ProjectPosition;

import javax.persistence.EntityNotFoundException;

public class ProjectPositionMapper {

    public static ProjectPosition mapToEntity(PositionDto positionDto, EmployeeRequestDto employeeRequestDto, DepartmentDto departmentDto, ProjectDto projectDto) {
        ProjectPosition position = new ProjectPosition();
        position.setId(positionDto.getId());
        position.setEmployee(EmployeeMapper.mapToEntity(employeeRequestDto, departmentDto));
        position.setProject(ProjectMapper.mapToEntity(projectDto));
        position.setPositionStartDate(positionDto.getPositionStartDate());
        position.setPositionEndDate(positionDto.getPositionEndDate());
        return position;
    }

    public static PositionDto mapToDto(ProjectPosition position, String employeeEmail, String projectTitle) {
        PositionDto positionDto = new PositionDto();
        positionDto.setId(position.getId());
        positionDto.setEmployeeEmail(employeeEmail);
        positionDto.setProjectTitle(projectTitle);
        positionDto.setPositionStartDate(position.getPositionStartDate());
        positionDto.setPositionEndDate(position.getPositionEndDate());
        return positionDto;
    }
}
