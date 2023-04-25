package com.example.hospitalorganizer.controller;

import com.example.hospitalorganizer.dto.HospitalWithAverageAgeDto;
import com.example.hospitalorganizer.dto.HospitalWithPatientIdListDto;
import com.example.hospitalorganizer.dto.HospitalWithoutPatientsShiftsDto;
import com.example.hospitalorganizer.dto.PatientIdsDto;
import com.example.hospitalorganizer.exception.HospitalNotFoundException;
import com.example.hospitalorganizer.exception.PatientNotFoundException;
import com.example.hospitalorganizer.model.Hospital;
import com.example.hospitalorganizer.service.HospitalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
@Validated
@CrossOrigin
public class HospitalController {

    private final HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HospitalWithoutPatientsShiftsDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hospital findById(@PathVariable int id) throws HospitalNotFoundException {
        return service.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hospital create(@Valid @RequestBody HospitalWithPatientIdListDto hospitalDto) throws PatientNotFoundException {
        return service.create(hospitalDto);
    }

    @PutMapping("/{id}/patients")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody PatientIdsDto patientIds, @PathVariable int id) throws HospitalNotFoundException, PatientNotFoundException {
        service.linkPatients(patientIds, id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody HospitalWithPatientIdListDto hospitalDto, @PathVariable int id) throws HospitalNotFoundException, PatientNotFoundException {
        service.update(hospitalDto, id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws HospitalNotFoundException {
        service.delete(id);
    }

    @GetMapping(value = "/filter/{capacity}")
    @ResponseStatus(HttpStatus.OK)
    public List<HospitalWithoutPatientsShiftsDto> filterByMaximumCapacity(@PathVariable @Min(1) int capacity) {
        return service.filterByMaximumCapacity(capacity);
    }

    @GetMapping(value = "/order-by/average-age")
    @ResponseStatus(HttpStatus.OK)
    public List<HospitalWithAverageAgeDto> orderByAverageAgeOfPatients() {
        return service.orderByAverageAgeOfPatients();
    }

}
