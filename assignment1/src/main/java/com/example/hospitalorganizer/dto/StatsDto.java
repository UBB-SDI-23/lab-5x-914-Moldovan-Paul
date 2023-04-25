package com.example.hospitalorganizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsDto {
    private int doctorId;
    private long numberOfPatientsUnderCare;
}
