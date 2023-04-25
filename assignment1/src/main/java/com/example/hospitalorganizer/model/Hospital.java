package com.example.hospitalorganizer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospitals")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hospital{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String name;
        private String address;
        private String specialties;
        private boolean privateHospital;
        private boolean takesEmergencies;
        private int maximumCapacity;

        @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference(value = "patient-list")
        private List<Patient> patients;

        @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference(value = "hospital-shift-list")
        private List<Shift> shifts;
}

