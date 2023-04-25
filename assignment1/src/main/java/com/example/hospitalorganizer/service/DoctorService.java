package com.example.hospitalorganizer.service;

import com.example.hospitalorganizer.dto.DoctorWithoutShiftsDto;
import com.example.hospitalorganizer.exception.DoctorNotFoundException;
import com.example.hospitalorganizer.model.Doctor;
import com.example.hospitalorganizer.modelmapper.GlobalModelMapper;
import com.example.hospitalorganizer.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }
    private DoctorWithoutShiftsDto convertToDtoWithoutShifts(Doctor doctor) {
        return GlobalModelMapper.modelMapper.map(doctor, DoctorWithoutShiftsDto.class);
    }

    public List<DoctorWithoutShiftsDto> findAllDoctors() {
        return repository.findAll().stream()
                .map(this::convertToDtoWithoutShifts)
                .collect(Collectors.toList());
    }

    public Doctor findById(int id) throws DoctorNotFoundException {
        return repository.findById(id).orElseThrow(DoctorNotFoundException::new);
    }

    public Doctor create(Doctor Doctor) {
        return repository.save(Doctor);
    }

    public void update(Doctor Doctor, int id) throws DoctorNotFoundException {
        if (repository.existsById(id)) {
            repository.save(Doctor);
        }
        else {
            throw new DoctorNotFoundException();
        }
    }

    public void delete(int id) throws DoctorNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new DoctorNotFoundException();
        }
    }
}
