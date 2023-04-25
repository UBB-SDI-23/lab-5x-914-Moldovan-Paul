package com.example.hospitalorganizer.tests;

import com.example.hospitalorganizer.dto.HospitalWithAverageAgeDto;
import com.example.hospitalorganizer.dto.HospitalWithoutPatientsShiftsDto;
import com.example.hospitalorganizer.model.Hospital;
import com.example.hospitalorganizer.repository.HospitalRepository;
import com.example.hospitalorganizer.service.HospitalService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HospitalServiceTests {

    @Mock
    private HospitalRepository repository;

    @InjectMocks
    private HospitalService service;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void orderByAverageAgeOfPatients() {
        HospitalWithAverageAgeDto h1 = new HospitalWithAverageAgeDto(1, "a", "a", "a",
                true, true, 20, 45);
        HospitalWithAverageAgeDto h2 = new HospitalWithAverageAgeDto(1, "a", "a", "a",
                true, true, 20, 30);
        HospitalWithAverageAgeDto h3 = new HospitalWithAverageAgeDto(1, "a", "a", "a",
                true, true, 20, 21.6);
        List<HospitalWithAverageAgeDto> hospitals = List.of(h1,h2,h3);
        when(repository.order()).thenReturn(hospitals);
        List<HospitalWithAverageAgeDto> result = service.orderByAverageAgeOfPatients();
        assertEquals(result.get(0).getAverageAge(), 45, 0);
        assertEquals(result.get(1).getAverageAge(), 30,0 );
        assertEquals(result.get(2).getAverageAge(), 21.6, 0);
    }

    @Test
    public void filterByMaximumCapacity() {
        Hospital h1 = new Hospital(1, "a", "a", "a",
                true, true, 20, null, null);
        Hospital h2 = new Hospital(1, "a", "a", "a",
                true, true, 30, null, null);
        Hospital h3 = new Hospital(1, "a", "a", "a",
                true, true, 40, null, null);
        List<Hospital> hospitals = List.of(h1,h2,h3);
        when(repository.findByMaximumCapacityGreaterThan(20)).thenReturn(hospitals);
        List<HospitalWithoutPatientsShiftsDto> result = service.filterByMaximumCapacity(20);
        assertEquals(result.get(0).getMaximumCapacity(), 20, 0);
        assertEquals(result.get(1).getMaximumCapacity(), 30,0 );
        assertEquals(result.get(2).getMaximumCapacity(), 40, 0);
    }
}
