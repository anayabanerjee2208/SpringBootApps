package net.learnwithanaya.employeeservice.service;

import net.learnwithanaya.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient (url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
//Together with Open Feign client and Eureka server we can implement load balancing. Mention service id registered in Spring Cloud Netflix Eureka server to achieve load balancing
@FeignClient(name = "DEPARTMENT-SERVICE")
//This will make the interface a Feign Client and Feign Client library will dynamically create an implementation of the interface
public interface ApiClient {
    @GetMapping("api/departments/{department-code}")
    public DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);
    }
