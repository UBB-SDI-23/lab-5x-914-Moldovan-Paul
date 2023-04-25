package com.example.hospitalorganizer.service;

import com.example.hospitalorganizer.dto.HospitalWithAverageAgeDto;
import com.example.hospitalorganizer.dto.HospitalWithPatientIdListDto;
import com.example.hospitalorganizer.dto.HospitalWithoutPatientsShiftsDto;
import com.example.hospitalorganizer.dto.PatientIdsDto;
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
public class HospitalService {
    private final PatientRepository patientRepository;

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository, PatientRepository patientRepository) {
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
    }
    private HospitalWithoutPatientsShiftsDto convertToDtoWithoutPatientsShifts(Hospital hospital) {
        return GlobalModelMapper.modelMapper.map(hospital, HospitalWithoutPatientsShiftsDto.class);
    }
    private void updatePatientsHospitalId(Hospital hospital, List<Integer> patientIds) throws PatientNotFoundException {
        if (patientIds.size() != 0) {
            for(int patientId: patientIds) {
                Patient patient = patientRepository.findById(patientId).orElseThrow(PatientNotFoundException::new);
                patient.setHospital(hospital);
                patientRepository.save(patient);
            }
        }
    }

    public List<HospitalWithoutPatientsShiftsDto> findAll() {
        return hospitalRepository.findAll().stream()
                .map(this::convertToDtoWithoutPatientsShifts)
                .collect(Collectors.toList());
    }

    public Hospital findById(int id) throws HospitalNotFoundException {
        return hospitalRepository.findById(id).orElseThrow(HospitalNotFoundException::new);
    }

    public Hospital create(HospitalWithPatientIdListDto hospitalDto) throws PatientNotFoundException {
        Hospital hospital = GlobalModelMapper.modelMapper.map(hospitalDto, Hospital.class);
        hospitalRepository.save(hospital);
        updatePatientsHospitalId(hospital, hospitalDto.getPatientIds());
        return hospital;
    }

    public void update(HospitalWithPatientIdListDto hospitalDto,int id) throws HospitalNotFoundException, PatientNotFoundException {
        if (hospitalRepository.existsById(id)) {
            create(hospitalDto);
        }
        else {
            throw new HospitalNotFoundException();
        }
    }

    public void linkPatients(PatientIdsDto patientIds, int id) throws HospitalNotFoundException, PatientNotFoundException {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(HospitalNotFoundException::new);
        updatePatientsHospitalId(hospital, patientIds.getPatientIds());
    }

    public void delete(int id) throws HospitalNotFoundException {
        if (hospitalRepository.existsById(id)) {
            hospitalRepository.deleteById(id);
        }
        else {
            throw new HospitalNotFoundException();
        }
    }

    public List<HospitalWithoutPatientsShiftsDto> filterByMaximumCapacity(int capacity) {
        return hospitalRepository.findByMaximumCapacityGreaterThan(capacity)
                .stream()
                .map(this::convertToDtoWithoutPatientsShifts)
                .collect(Collectors.toList());
    }

    public List<HospitalWithAverageAgeDto> orderByAverageAgeOfPatients() {
        return hospitalRepository.order();
    }

}
