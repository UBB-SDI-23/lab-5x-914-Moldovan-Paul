package com.example.hospitalorganizer.controller;

import com.example.hospitalorganizer.dto.DoctorWithoutShiftsDto;
import com.example.hospitalorganizer.exception.DoctorNotFoundException;
import com.example.hospitalorganizer.model.Doctor;
import com.example.hospitalorganizer.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@Validated
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorWithoutShiftsDto> findAllDoctors() {
        return service.findAllDoctors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor findById(@PathVariable int id) throws DoctorNotFoundException {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor create(@Valid @RequestBody Doctor doctor) {
        return service.create(doctor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody Doctor doctor, @PathVariable int id) throws DoctorNotFoundException {
        service.update(doctor, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws DoctorNotFoundException {
        service.delete(id);
    }
}
