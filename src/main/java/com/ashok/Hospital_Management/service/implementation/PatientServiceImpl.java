package com.ashok.Hospital_Management.service.implementation;
import com.ashok.Hospital_Management.dto.PatientFullHealthResponse;
import com.ashok.Hospital_Management.entities.Patient;
import com.ashok.Hospital_Management.exceptions.UserNotFoundException;
import com.ashok.Hospital_Management.repository.MedicalHistoryRepository;
import com.ashok.Hospital_Management.repository.PatientHealthRecordRepository;
import com.ashok.Hospital_Management.repository.PatientRepository;
import com.ashok.Hospital_Management.service.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientHealthRecordRepository patientHealthRecordRepository;

    @Override
    @Cacheable(value = "fullHealthInfo", key = "#email")
    public PatientFullHealthResponse getFullHealthInfoByEmail(String email) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Patient not found with email: " + email));

        return new PatientFullHealthResponse(
                medicalHistoryRepository.findByPatientId(patient.getId()),
                patientHealthRecordRepository.findByPatientId(patient.getId())
        );
    }
}