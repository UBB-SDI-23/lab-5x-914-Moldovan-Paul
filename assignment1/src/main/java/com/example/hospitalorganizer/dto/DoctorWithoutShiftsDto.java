package com.example.hospitalorganizer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorWithoutShiftsDto {
    private int id;
    private String firstName;
    private String lastName;
    private String speciality;
    private int yearsOfExperience;
}
