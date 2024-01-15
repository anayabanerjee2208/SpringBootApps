package net.learnwithanaya.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "DepartmentDto Model Information"
)
public class DepartmentDto {
    private Long id;
    @Schema(
            description = "department name"
    )
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
