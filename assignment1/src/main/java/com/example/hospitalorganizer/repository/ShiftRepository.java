package com.example.hospitalorganizer.repository;

import com.example.hospitalorganizer.dto.StatsDto;
import com.example.hospitalorganizer.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
    @Query("SELECT NEW com.example.hospitalorganizer.dto.StatsDto(s.doctor.id, COUNT(p)) " +
            "FROM Shift s " +
            "JOIN s.hospital h " +
            "JOIN h.patients p " +
            "GROUP BY s.doctor ")
    List<StatsDto> order();

}
