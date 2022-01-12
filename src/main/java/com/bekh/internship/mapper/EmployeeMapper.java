package com.bekh.internship.mapper;

import com.bekh.internship.dto.DepartmentDto;
import com.bekh.internship.dto.EmployeeRequestDto;
import com.bekh.internship.dto.EmployeeResponseDto;
import com.bekh.internship.model.Employee;

public class EmployeeMapper {

    public static EmployeeResponseDto mapToResponseDto(Employee employee){
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(employee.getId());
        employeeResponseDto.setFirstName(employee.getFirstName());
        employeeResponseDto.setLastName(employee.getLastName());
        employeeResponseDto.setEmail(employee.getEmail());
        employeeResponseDto.setPosition(employee.getJobTitle());
        employeeResponseDto.setDepartmentTitle(employee.getDepartment().getTitle());
        return employeeResponseDto;
    }

    public static EmployeeResponseDto mapToResponseDto(EmployeeRequestDto requestEmployeeDto) {
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(requestEmployeeDto.getId());
        employeeResponseDto.setFirstName(requestEmployeeDto.getFirstName());
        employeeResponseDto.setLastName(requestEmployeeDto.getLastName());
        employeeResponseDto.setEmail(requestEmployeeDto.getEmail());
        employeeResponseDto.setPosition(requestEmployeeDto.getPosition());
        employeeResponseDto.setDepartmentTitle(requestEmployeeDto.getDepartmentTitle());
        return employeeResponseDto;
    }

    public static EmployeeRequestDto mapToRequestDto(Employee employee) {
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setId(employee.getId());
        employeeRequestDto.setFirstName(employee.getFirstName());
        employeeRequestDto.setLastName(employee.getLastName());
        employeeRequestDto.setEmail(employee.getEmail());
        employeeRequestDto.setPosition(employee.getJobTitle());
        employeeRequestDto.setDepartmentTitle(employee.getDepartment().getTitle());
        return employeeRequestDto;
    }

    public static Employee mapToEntity(EmployeeRequestDto requestEmployeeDto, DepartmentDto departmentDto) {
        Employee employee = new Employee();
        employee.setId(requestEmployeeDto.getId());
        employee.setFirstName(requestEmployeeDto.getFirstName());
        employee.setLastName(requestEmployeeDto.getLastName());
        employee.setEmail(requestEmployeeDto.getEmail());
        employee.setPassword(requestEmployeeDto.getPassword());
        employee.setJobTitle(requestEmployeeDto.getPosition());
        employee.setDepartment(DepartmentMapper.mapToEntity(departmentDto));
        return employee;
    }
}
