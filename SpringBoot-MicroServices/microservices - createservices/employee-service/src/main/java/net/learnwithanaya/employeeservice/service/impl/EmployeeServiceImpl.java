package net.learnwithanaya.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.learnwithanaya.employeeservice.dto.EmployeeDto;
import net.learnwithanaya.employeeservice.entity.Employee;
import net.learnwithanaya.employeeservice.exception.ResourceNotFoundException;
import net.learnwithanaya.employeeservice.mapper.AutoUserMapper;
import net.learnwithanaya.employeeservice.repository.EmployeeRepository;
import net.learnwithanaya.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        /*Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );*/

        Employee employee = modelMapper.map(employeeDto, Employee.class);

        Employee savedEmployee= employeeRepository.save(employee);
        /*EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail()
        );*/

        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () ->new ResourceNotFoundException("Employee", "id", employeeId)
        );
        /*EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );*/
        EmployeeDto employeeDto = AutoUserMapper.MAPPER.mapToEmployeeDto(employee);
        return employeeDto;
    }
}
