package com.ashok.Hospital_Management.service.implementation;

import com.ashok.Hospital_Management.dto.PatientHealthRecordRequest;
import com.ashok.Hospital_Management.dto.PatientHealthRecordResponse;
import com.ashok.Hospital_Management.entities.Patient;
import com.ashok.Hospital_Management.entities.PatientHealthRecord;
import com.ashok.Hospital_Management.exceptions.UserNotFoundException;
import com.ashok.Hospital_Management.repository.PatientHealthRecordRepository;
import com.ashok.Hospital_Management.repository.PatientRepository;
import com.ashok.Hospital_Management.service.interfaces.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {

    private final PatientRepository patientRepository;
    private final PatientHealthRecordRepository healthRecordRepository;


    @CacheEvict(value = {"patientRecords", "allHealthRecords"}, allEntries = true)
    @Override
    public PatientHealthRecordResponse saveRecord(PatientHealthRecordRequest request) {
        Patient patient = patientRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException("Patient not found with ID: " + request.getId()));

        PatientHealthRecord record = PatientHealthRecord.builder()
                .bloodPressure(request.getBloodPressure())
                .sugarLevel(request.getSugarLevel())
                .weight(request.getWeight())
                .temperature(request.getTemperature())
                .oxygenLevel(request.getOxygenLevel())
                .healthStatus(request.getHealthStatus())
                .notes(request.getNotes())
                .patient(patient)
                .build();

        PatientHealthRecord saved = healthRecordRepository.save(record);

        return PatientHealthRecordResponse.builder()
                .id(saved.getId())
                .bloodPressure(saved.getBloodPressure())
                .sugarLevel(saved.getSugarLevel())
                .weight(saved.getWeight())
                .temperature(saved.getTemperature())
                .oxygenLevel(saved.getOxygenLevel())
                .healthStatus(saved.getHealthStatus())
                .notes(saved.getNotes())
                .recordDate(saved.getRecordDate())
                .patientName(patient.getUsername())  // assuming patient has getName()
                .build();
    }

    @Override
    @Cacheable(value = "patientRecords", key = "#patientId")
    public List<PatientHealthRecordResponse> getRecordsByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new UserNotFoundException("Patient not found with ID: " + patientId));

        List<PatientHealthRecord> records = healthRecordRepository.findByPatient(patient);

        return records.stream()
                .map(r -> PatientHealthRecordResponse.builder()
                        .id(r.getId())
                        .bloodPressure(r.getBloodPressure())
                        .sugarLevel(r.getSugarLevel())
                        .weight(r.getWeight())
                        .temperature(r.getTemperature())
                        .oxygenLevel(r.getOxygenLevel())
                        .healthStatus(r.getHealthStatus())
                        .notes(r.getNotes())
                        .recordDate(r.getRecordDate())
                        .patientName(patient.getUsername())
                        .build())
                .collect(Collectors.toList());
    }
    @Override
    @Cacheable(value = "allHealthRecords")
    public List<PatientHealthRecordResponse> getAllHealthRecords() {
        return healthRecordRepository.findAll().stream()
                .map(record -> PatientHealthRecordResponse.builder()
                        .id(record.getId())
                        .bloodPressure(record.getBloodPressure())
                        .sugarLevel(record.getSugarLevel())
                        .weight(record.getWeight())
                        .temperature(record.getTemperature())
                        .oxygenLevel(record.getOxygenLevel())
                        .healthStatus(record.getHealthStatus())
                        .notes(record.getNotes())
                        .recordDate(record.getRecordDate())
                        .id(record.getPatient().getId())
                        .build())
                .toList();
    }

}
