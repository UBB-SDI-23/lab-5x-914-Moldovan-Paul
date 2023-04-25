package com.example.hospitalorganizer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @JsonBackReference(value = "hospital-shift-list")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference(value = "doctor-shift-list")
    private Doctor doctor;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateAndTime;

    private String description;

}