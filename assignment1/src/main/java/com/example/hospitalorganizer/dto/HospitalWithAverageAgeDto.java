package com.example.hospitalorganizer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HospitalWithAverageAgeDto {
    private int id;
    private String name;
    private String address;
    private String specialties;
    private boolean privateHospital;
    private boolean takesEmergencies;
    private int maximumCapacity;
    private double averageAge;
}
