package com.example.hospitalorganizer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PatientWithHospitalDto {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String symptomsDescription;
    private String diagnosis;
    private HospitalWithoutPatientsShiftsDto hospital;
}