package net.learnwithanaya.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.learnwithanaya.employeeservice.dto.ApiResponseDto;
import net.learnwithanaya.employeeservice.dto.DepartmentDto;
import net.learnwithanaya.employeeservice.dto.EmployeeDto;
import net.learnwithanaya.employeeservice.entity.Employee;
import net.learnwithanaya.employeeservice.exception.ResourceNotFoundException;
import net.learnwithanaya.employeeservice.mapper.AutoUserMapper;
import net.learnwithanaya.employeeservice.repository.EmployeeRepository;
import net.learnwithanaya.employeeservice.service.ApiClient;
import net.learnwithanaya.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    //@Autowired
    //private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private ApiClient apiClient;
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
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public ApiResponseDto getEmployeeById(Long employeeId) {
        logger.info("inside getEmployeeById method");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () ->new ResourceNotFoundException("Employee", "id", employeeId)
        );
        EmployeeDto employeeDto = AutoUserMapper.MAPPER.mapToEmployeeDto(employee);
        //Create/call the API Request
       /* ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode()
        , DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();*/

       DepartmentDto departmentDto = webClient.get()
               .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
               .retrieve()
               .bodyToMono(DepartmentDto.class).block();

      //  DepartmentDto departmentDto = apiClient.getDepartmentByCode(employeeDto.getDepartmentCode());

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }

    //Implement the Circuit Breaker fall back method

    public ApiResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
        logger.info("inside getDefaultDepartment method");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () ->new ResourceNotFoundException("Employee", "id", employeeId)
        );
        EmployeeDto employeeDto = AutoUserMapper.MAPPER.mapToEmployeeDto(employee);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");
        departmentDto.setDepartmentName("R&D Department");
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
