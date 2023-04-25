package com.example.hospitalorganizer.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String symptomsDescription;
    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @JsonBackReference(value = "patient-list")
    private Hospital hospital;

}


