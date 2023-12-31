package net.learnwithanaya.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.learnwithanaya.employeeservice.dto.ApiResponseDto;
import net.learnwithanaya.employeeservice.dto.DepartmentDto;
import net.learnwithanaya.employeeservice.dto.EmployeeDto;
import net.learnwithanaya.employeeservice.entity.Employee;
import net.learnwithanaya.employeeservice.exception.ResourceNotFoundException;
import net.learnwithanaya.employeeservice.mapper.AutoUserMapper;
import net.learnwithanaya.employeeservice.repository.EmployeeRepository;
import net.learnwithanaya.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;
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
    public ApiResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () ->new ResourceNotFoundException("Employee", "id", employeeId)
        );
        EmployeeDto employeeDto = AutoUserMapper.MAPPER.mapToEmployeeDto(employee);
        employeeDto.setDepartmentCode(employee.getDepartmentCode());
        //Create/call the API Request
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode()
        , DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

        /*EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );*/

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
