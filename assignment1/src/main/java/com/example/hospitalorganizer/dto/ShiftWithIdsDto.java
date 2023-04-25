package com.example.hospitalorganizer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShiftWithIdsDto {
    private int id;
    private int hospitalId;
    private int doctorId;
    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "Please input date and time.")
    private Date dateAndTime;
    @NotBlank(message = "Please input description.")
    private String description;
}
