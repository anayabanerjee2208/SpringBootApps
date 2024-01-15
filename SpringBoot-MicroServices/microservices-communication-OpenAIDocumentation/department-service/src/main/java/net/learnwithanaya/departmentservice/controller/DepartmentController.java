package net.learnwithanaya.departmentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.learnwithanaya.departmentservice.dto.DepartmentDto;
import net.learnwithanaya.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    final HttpHeaders httpHeaders= new HttpHeaders();

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(
            summary = "Save Department REST API",
            description = "The API is used to save department object in a database"
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status 201CREATED",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = DepartmentDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status 400NotFound",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE

                                    )
                            }

                    )
            }
    )

    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping(value = "{department-code}", consumes = "application/json", produces = "application/json")
    @Operation(
            summary = "Get Department REST API",
            description = "The API is used to Get department object from a database"
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status 200SUCCESS",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = DepartmentDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status 400NotFound",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE

                                    )
                            }
                    )
            }
    )

    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
