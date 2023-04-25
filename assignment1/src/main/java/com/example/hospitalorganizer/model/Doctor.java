package com.example.hospitalorganizer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "doctors")
public class Doctor {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        @NotBlank(message = "Please input first name.")
        private String firstName;
        @NotBlank(message = "Please input last name.")
        private String lastName;
        @NotBlank(message = "Please input specialty name.")
        private String speciality;
        @Min(value = 1, message = "Please input years of experience.")
        private int yearsOfExperience;

        @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference(value = "doctor-shift-list")
        private List<Shift> shifts;
}
