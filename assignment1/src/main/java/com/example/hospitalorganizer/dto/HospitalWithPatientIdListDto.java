package com.example.hospitalorganizer.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HospitalWithPatientIdListDto {
    @NotBlank(message = "Please input name.")
    private String name;
    @NotBlank(message = "Please input address.")
    private String address;
    @NotBlank(message = "Please input specialties.")
    private String specialties;
    private boolean privateHospital;
    private boolean takesEmergencies;
    @Min(value = 1, message = "Please input a maximum capacity.")
    private int maximumCapacity;
    private List<Integer> patientIds = new ArrayList<>();
}