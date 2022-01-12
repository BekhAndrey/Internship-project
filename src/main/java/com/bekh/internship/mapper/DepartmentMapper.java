package com.bekh.internship.mapper;

import com.bekh.internship.dto.DepartmentDto;
import com.bekh.internship.model.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDto(Department department){
        return new DepartmentDto(department.getId(), department.getTitle());
    }

    public static Department mapToEntity(DepartmentDto departmentDto){
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setTitle(departmentDto.getTitle());
        return department;
    }
}
