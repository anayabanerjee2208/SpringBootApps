package net.learnwithanaya.employeeservice.service;

import net.learnwithanaya.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    public EmployeeDto saveEmployee(EmployeeDto employeeDto);

    public EmployeeDto getEmployeeById(Long employeeId);
}
