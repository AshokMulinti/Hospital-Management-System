package com.ashok.Hospital_Management.service.interfaces;

import com.ashok.Hospital_Management.dto.PatientFullHealthResponse;

public interface PatientService {
    PatientFullHealthResponse getFullHealthInfoByEmail(String email);
}
