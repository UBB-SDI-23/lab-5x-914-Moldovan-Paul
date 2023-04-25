package com.example.hospitalorganizer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PatientWithHospitalIdDto {
    private int id;
    @NotBlank(message = "Please input first name.")
    private String firstName;
    @NotBlank(message = "Please input last name.")
    private String lastName;
    @Min(value = 1, message = "Please input age." )
    private int age;
    @NotBlank(message = "Please input a description of symptoms.")
    private String symptomsDescription;
    private String diagnosis;
    private int hospitalId = 0;
}