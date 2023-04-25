package com.example.hospitalorganizer.controller;

import com.example.hospitalorganizer.dto.PatientWithHospitalDto;
import com.example.hospitalorganizer.dto.PatientWithHospitalIdDto;
import com.example.hospitalorganizer.exception.HospitalNotFoundException;
import com.example.hospitalorganizer.exception.PatientNotFoundException;
import com.example.hospitalorganizer.model.Patient;
import com.example.hospitalorganizer.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Validated
@CrossOrigin
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientWithHospitalIdDto> findAllPatients() {
        return service.findAllPatients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientWithHospitalDto findById(@PathVariable int id) throws PatientNotFoundException {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@Valid @RequestBody PatientWithHospitalIdDto patientDto) throws HospitalNotFoundException {
        return service.create(patientDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody PatientWithHospitalIdDto patientDto, @PathVariable int id) throws PatientNotFoundException, HospitalNotFoundException {
        service.update(patientDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws PatientNotFoundException {
        service.delete(id);
    }
}
