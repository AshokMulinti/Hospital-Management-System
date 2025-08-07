package com.ashok.Hospital_Management.service.implementation;

import com.ashok.Hospital_Management.dto.MedicalHistoryRequest;
import com.ashok.Hospital_Management.dto.MedicalHistoryResponse;
import com.ashok.Hospital_Management.entities.MedicalHistory;
import com.ashok.Hospital_Management.entities.Patient;
import com.ashok.Hospital_Management.exceptions.UserNotFoundException;

import com.ashok.Hospital_Management.repository.MedicalHistoryRepository;
import com.ashok.Hospital_Management.repository.PatientRepository;
import com.ashok.Hospital_Management.service.interfaces.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientRepository patientRepository;

    @CacheEvict(value = "medicalHistoryCache", key = "#request.id()")
    @Override
    public MedicalHistoryResponse addMedicalHistory(MedicalHistoryRequest request) {
        Patient patient = patientRepository.findById(request.id())
                .orElseThrow(() -> new UserNotFoundException("Patient not found with "+request.id()));

        MedicalHistory history = MedicalHistory.builder()
                .diagnosis(request.diagnosis())
                .prescribedTests(request.prescribedTests())
                .prescribedMedicines(request.prescribedMedicines())
                .nextVisitDate(request.nextVisitDate())
                .patient(patient)
                .build();

        MedicalHistory saved = medicalHistoryRepository.save(history);

        return new MedicalHistoryResponse(
                saved.getId(),
                saved.getDiagnosis(),
                saved.getPrescribedTests(),
                saved.getPrescribedMedicines(),
                saved.getVisitDate(),
                saved.getNextVisitDate()
        );
    }
    @Cacheable(value = "medicalHistoryCache", key = "#patientId")
    @Override
    public List<MedicalHistoryResponse> getMedicalHistoryByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new UserNotFoundException("Patient not found with: "+patientId));

        List<MedicalHistory> historyList = medicalHistoryRepository.findByPatient(patient);

        return historyList.stream()
                .map(h -> new MedicalHistoryResponse(
                        h.getId(),
                        h.getDiagnosis(),
                        h.getPrescribedTests(),
                        h.getPrescribedMedicines(),
                        h.getVisitDate(),
                        h.getNextVisitDate()
                )).collect(Collectors.toList());
    }
}
