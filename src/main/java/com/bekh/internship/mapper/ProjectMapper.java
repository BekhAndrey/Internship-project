package com.bekh.internship.mapper;

import com.bekh.internship.dto.EmployeeRequestDto;
import com.bekh.internship.dto.ProjectDto;
import com.bekh.internship.model.Department;
import com.bekh.internship.model.Employee;
import com.bekh.internship.model.Project;

import javax.persistence.EntityNotFoundException;

public class ProjectMapper {

    public static ProjectDto mapToDto(Project project){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setEndDate(project.getEndDate());
        return projectDto;
    }

    public static Project mapToEntity(ProjectDto projectDto){
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setTitle(projectDto.getTitle());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        return project;
    }
}
