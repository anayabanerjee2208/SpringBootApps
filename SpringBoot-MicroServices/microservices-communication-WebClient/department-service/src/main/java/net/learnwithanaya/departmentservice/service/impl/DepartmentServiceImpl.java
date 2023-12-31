package net.learnwithanaya.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.learnwithanaya.departmentservice.dto.DepartmentDto;
import net.learnwithanaya.departmentservice.entity.Department;
import net.learnwithanaya.departmentservice.exception.ResourceNotFoundException;
import net.learnwithanaya.departmentservice.mapper.AutoUserMapper;
import net.learnwithanaya.departmentservice.repository.DepartmentRepository;
import net.learnwithanaya.departmentservice.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    //Use modelmapper bean to map dto <-> jpa entity
    @Autowired
    private ModelMapper modelMapper;
    @Override

    //Use Modelmapper to map the Dto object to JPA entity and vice versa
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        //Convert DepartmentDto to Department jpa entity
//        Department department = new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );
        Department department =modelMapper.map(departmentDto, Department.class);

        Department savedDepartment = departmentRepository.save(department);

        /*DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );*/

        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);
        return savedDepartmentDto;
    }

    @Override
    //Used MapStruct as mapping library
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        if (department != null){
            /*DepartmentDto departmentDto = new DepartmentDto(
                    department.getId(),
                    department.getDepartmentName(),
                    department.getDepartmentDescription(),
                    department.getDepartmentCode()
            );*/
            DepartmentDto departmentDto = AutoUserMapper.MAPPER.mapToDepartmentDto(department);
            return departmentDto;
        }
        else {
            throw new ResourceNotFoundException("Department", "id", departmentCode);
        }
    }
}
