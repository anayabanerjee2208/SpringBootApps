package net.learnwithanaya.departmentservice.service;

import net.learnwithanaya.departmentservice.dto.DepartmentDto;
import net.learnwithanaya.departmentservice.exception.ResourceNotFoundException;

public interface DepartmentService {
    public DepartmentDto saveDepartment(DepartmentDto departmentDto);

    public DepartmentDto getDepartmentByCode(String departmentCode);
}
