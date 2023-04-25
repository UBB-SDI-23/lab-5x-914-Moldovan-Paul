package com.example.hospitalorganizer.service;

import com.example.hospitalorganizer.dto.HospitalWithoutPatientsShiftsDto;
import com.example.hospitalorganizer.dto.PatientWithHospitalDto;
import com.example.hospitalorganizer.dto.PatientWithHospitalIdDto;
import com.example.hospitalorganizer.exception.HospitalNotFoundException;
import com.example.hospitalorganizer.exception.PatientNotFoundException;
import com.example.hospitalorganizer.model.Hospital;
import com.example.hospitalorganizer.model.Patient;
import com.example.hospitalorganizer.modelmapper.GlobalModelMapper;
import com.example.hospitalorganizer.repository.HospitalRepository;
import com.example.hospitalorganizer.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final HospitalRepository hospitalRepository;

    public PatientService(PatientRepository patientRepository, HospitalRepository hospitalRepository) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }

    private PatientWithHospitalIdDto convertToDtoWithHospitalId(Patient patient) {
        PatientWithHospitalIdDto patientDto = GlobalModelMapper.modelMapper.map(patient, PatientWithHospitalIdDto.class);
        if (patient.getHospital() != null)
            patientDto.setHospitalId(patient.getHospital().getId());
        return patientDto;
    }

    private PatientWithHospitalDto convertToDtoWithHospital(Patient patient) {
        PatientWithHospitalDto patientDto = GlobalModelMapper.modelMapper.map(patient, PatientWithHospitalDto.class);
        if (patient.getHospital() != null)
        {
            patientDto.setHospital(GlobalModelMapper.modelMapper
                    .map(patient.getHospital(), HospitalWithoutPatientsShiftsDto.class));
        }
        return patientDto;
    }

    public List<PatientWithHospitalIdDto> findAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDtoWithHospitalId)
                .collect(Collectors.toList());
    }

    public PatientWithHospitalDto findById(int id) throws PatientNotFoundException {
        return convertToDtoWithHospital(patientRepository.findById(id).orElseThrow(PatientNotFoundException::new));
    }

    public Patient create(PatientWithHospitalIdDto patientWithHospitalIdDto) throws HospitalNotFoundException {
        Patient patient = GlobalModelMapper.modelMapper.map(patientWithHospitalIdDto, Patient.class);
        if (patientWithHospitalIdDto.getHospitalId() != 0) {
            Hospital hospital = hospitalRepository.findById(patientWithHospitalIdDto.getHospitalId()).orElseThrow(
                    HospitalNotFoundException::new
            );
            patient.setHospital(hospital);
        }
        return patientRepository.save(patient);
    }

    public void update(PatientWithHospitalIdDto patientDto, int id) throws PatientNotFoundException, HospitalNotFoundException {
        if (patientRepository.existsById(id)) {
            create(patientDto);
        }
        else {
            throw new PatientNotFoundException();
        }
    }

    public void delete(int id) throws PatientNotFoundException {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        }
        else {
            throw new PatientNotFoundException();
        }
    }
}
