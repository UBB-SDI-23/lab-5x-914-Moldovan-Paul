package com.example.hospitalorganizer.controller;

import com.example.hospitalorganizer.dto.ShiftWithIdsDto;
import com.example.hospitalorganizer.dto.ShiftWithObjectsDto;
import com.example.hospitalorganizer.dto.StatsDto;
import com.example.hospitalorganizer.exception.ConsultationNotFoundException;
import com.example.hospitalorganizer.exception.DoctorNotFoundException;
import com.example.hospitalorganizer.exception.HospitalNotFoundException;
import com.example.hospitalorganizer.model.Shift;
import com.example.hospitalorganizer.service.ShiftService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
@Validated
public class ShiftController {

    private final ShiftService service;

    public ShiftController(ShiftService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShiftWithIdsDto> findAllConsultations() {
        return service.findAllConsultations();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShiftWithObjectsDto findById(@PathVariable int id) throws ConsultationNotFoundException, DoctorNotFoundException {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shift create(@Valid @RequestBody ShiftWithIdsDto shiftDto) throws DoctorNotFoundException, HospitalNotFoundException {
        return service.create(shiftDto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody ShiftWithIdsDto shiftDto, @PathVariable int id) throws ConsultationNotFoundException, DoctorNotFoundException, HospitalNotFoundException {
        service.update(shiftDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws ConsultationNotFoundException {
        service.delete(id);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<StatsDto> stats(){
        return service.stats();
    }
}
