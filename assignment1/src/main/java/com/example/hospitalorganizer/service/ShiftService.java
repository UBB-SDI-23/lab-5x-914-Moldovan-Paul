package com.example.hospitalorganizer.service;

import com.example.hospitalorganizer.dto.*;
import com.example.hospitalorganizer.exception.ConsultationNotFoundException;
import com.example.hospitalorganizer.exception.DoctorNotFoundException;
import com.example.hospitalorganizer.exception.HospitalNotFoundException;
import com.example.hospitalorganizer.model.Shift;
import com.example.hospitalorganizer.modelmapper.GlobalModelMapper;
import com.example.hospitalorganizer.repository.DoctorRepository;
import com.example.hospitalorganizer.repository.HospitalRepository;
import com.example.hospitalorganizer.repository.ShiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    private final HospitalRepository hospitalRepository;

    private final DoctorRepository doctorRepository;


    public ShiftService(ShiftRepository repository,
                        HospitalRepository hospitalRepository,
                        DoctorRepository doctorRepository) {
        this.shiftRepository = repository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
    }

    private ShiftWithObjectsDto convertToDtoWithObjects(Shift shift) throws ConsultationNotFoundException, DoctorNotFoundException {
        ShiftWithObjectsDto shiftDto = GlobalModelMapper.modelMapper.map(shift, ShiftWithObjectsDto.class);
        if (shift.getHospital() != null) {
            shiftDto.setHospital(GlobalModelMapper.modelMapper
                    .map(shift.getHospital(), HospitalWithoutPatientsShiftsDto.class));
        }
        else {
            throw new ConsultationNotFoundException();
        }
        if (shift.getDoctor() != null) {
            shiftDto.setDoctor(GlobalModelMapper.modelMapper
                    .map(shift.getDoctor(), DoctorWithoutShiftsDto.class));
        }
        else {
            throw new DoctorNotFoundException();
        }
        return shiftDto;
    }

    private ShiftWithIdsDto convertToDtoWithoutObjects(Shift shift) {
        ShiftWithIdsDto shiftDto = GlobalModelMapper.modelMapper.map(shift, ShiftWithIdsDto.class);
        shiftDto.setHospitalId(shift.getHospital().getId());
        shiftDto.setDoctorId(shift.getDoctor().getId());
        return shiftDto;
    }

    public List<ShiftWithIdsDto> findAllConsultations() {
        return shiftRepository.findAll().stream()
                .map(this::convertToDtoWithoutObjects)
                .collect(Collectors.toList());
    }

    public ShiftWithObjectsDto findById(@PathVariable int id) throws ConsultationNotFoundException, DoctorNotFoundException {
        return convertToDtoWithObjects(shiftRepository.findById(id).orElseThrow(ConsultationNotFoundException::new));
    }

    public Shift create(@RequestBody ShiftWithIdsDto shiftDto) throws HospitalNotFoundException, DoctorNotFoundException {
        Shift shift = GlobalModelMapper.modelMapper.map(shiftDto, Shift.class);
        shift.setHospital(hospitalRepository.findById(shiftDto.getHospitalId())
                .orElseThrow(HospitalNotFoundException::new));
        shift.setDoctor(doctorRepository.findById(shiftDto.getDoctorId())
                .orElseThrow(DoctorNotFoundException::new));
        return shiftRepository.save(shift);
    }

    public void update(@RequestBody ShiftWithIdsDto shiftDto, @PathVariable int id) throws ConsultationNotFoundException, DoctorNotFoundException, HospitalNotFoundException {
        if (shiftRepository.existsById(id)) {
            create(shiftDto);
        }
        else {
            throw new ConsultationNotFoundException();
        }
    }

    public void delete(@PathVariable int id) throws ConsultationNotFoundException {
        if (shiftRepository.existsById(id)) {
            shiftRepository.deleteById(id);
        }
        else {
            throw new ConsultationNotFoundException();
        }
    }

    public List<StatsDto> stats(){
        return shiftRepository.order();
    }
}
