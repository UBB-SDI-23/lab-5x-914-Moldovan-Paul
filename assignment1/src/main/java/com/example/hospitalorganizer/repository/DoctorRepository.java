package com.example.hospitalorganizer.repository;

import com.example.hospitalorganizer.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
