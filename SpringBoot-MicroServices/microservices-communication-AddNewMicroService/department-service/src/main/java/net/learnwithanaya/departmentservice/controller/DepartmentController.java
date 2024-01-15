package net.learnwithanaya.departmentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.learnwithanaya.departmentservice.dto.DepartmentDto;
import net.learnwithanaya.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor

@Tag(
        name = "Department Controller",
        description = "Department Controller Exposes Apis for Department Service"
)
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(consumes = "application/json",
    produces = "application/json"
    )
    @Operation(
            summary = "Save Department REST API",
            description = "The API is used to save department object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Department REST API",
            description = "The API is used to Get department object from a database"
    )

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 Success"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status 404 NotFound"
            )
    }
    )
    @GetMapping(value = "{department-code}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
