package net.learnwithanaya.employeeservice.mapper;

import net.learnwithanaya.employeeservice.dto.EmployeeDto;
import net.learnwithanaya.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    EmployeeDto mapToEmployeeDto(Employee employee);

    Employee mapToEmployeeJpaEntity(EmployeeDto employeeDto);
}
