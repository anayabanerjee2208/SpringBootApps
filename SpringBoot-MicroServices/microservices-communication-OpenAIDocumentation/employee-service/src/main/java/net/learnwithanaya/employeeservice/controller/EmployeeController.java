package net.learnwithanaya.employeeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.learnwithanaya.employeeservice.dto.ApiResponseDto;
import net.learnwithanaya.employeeservice.dto.EmployeeDto;
import net.learnwithanaya.employeeservice.exception.ErrorDetails;
import net.learnwithanaya.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
@Tag(
        name = "Employee Controller",
        description = "Employee Controller Exposes Apis for Department Service"
)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping

    @Operation(
            summary = "Save Employee REST API",
            description = "The API is used to save employee object in a database"
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status 201CREATED",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = EmployeeDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status 400NotFound",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorDetails.class)

                                    )
                            }

                    )
            }
    )

    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto saveEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<ApiResponseDto> getEmployeeById(@PathVariable("employeeId") Long employeeId){
        ApiResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
