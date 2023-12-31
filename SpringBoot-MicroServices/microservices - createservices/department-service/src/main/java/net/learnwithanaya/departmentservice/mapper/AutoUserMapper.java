package net.learnwithanaya.departmentservice.mapper;

import net.learnwithanaya.departmentservice.dto.DepartmentDto;
import net.learnwithanaya.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    DepartmentDto mapToDepartmentDto(Department department);

    Department mapToDepartmentJpaEntity(DepartmentDto departmentDto);
}
