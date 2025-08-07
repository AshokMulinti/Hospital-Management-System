package com.ashok.Hospital_Management.service.interfaces;

import com.ashok.Hospital_Management.dto.MedicalHistoryRequest;
import com.ashok.Hospital_Management.dto.MedicalHistoryResponse;

import java.util.List;

public interface DoctorService {
    MedicalHistoryResponse addMedicalHistory(MedicalHistoryRequest request);

    List<MedicalHistoryResponse> getMedicalHistoryByPatientId(Long patientId);
}
