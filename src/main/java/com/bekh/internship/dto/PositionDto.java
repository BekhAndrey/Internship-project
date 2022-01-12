package com.bekh.internship.dto;

import com.bekh.internship.model.Employee;
import com.bekh.internship.model.Project;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    @Schema(description = "Unique identifier of the position.", example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Email of the employee.", example = "test@gmail.com", required = true)
    private String employeeEmail;

    @Schema(description = "Title of the project.", example = "Ariba", required = true)
    private String projectTitle;

    @Schema(description = "Start date of the employee on project.", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate positionStartDate;

    @Schema(description = "End date of the employee on project.", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate positionEndDate;
}
