package com.example.hospitalorganizer.repository;

import com.example.hospitalorganizer.dto.HospitalWithAverageAgeDto;
import com.example.hospitalorganizer.dto.StatsDto;
import com.example.hospitalorganizer.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByMaximumCapacityGreaterThan(int capacity);

    @Query("SELECT NEW com.example.hospitalorganizer.dto.HospitalWithAverageAgeDto(h.id, h.name, h.address, " +
            "h.specialties, h.privateHospital, h.takesEmergencies, h.maximumCapacity, AVG(p.age)) " +
            "FROM Hospital h " +
            "JOIN h.patients p " +
            "GROUP BY h " +
            "ORDER BY AVG(p.age) DESC")
    List<HospitalWithAverageAgeDto> order();
}
