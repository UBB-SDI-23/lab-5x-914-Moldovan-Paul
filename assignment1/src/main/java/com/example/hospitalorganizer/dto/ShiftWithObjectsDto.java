package com.example.hospitalorganizer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShiftWithObjectsDto {
    int id;
    private DoctorWithoutShiftsDto doctor;
    private HospitalWithoutPatientsShiftsDto hospital;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateAndTime;
    private String description;

}
