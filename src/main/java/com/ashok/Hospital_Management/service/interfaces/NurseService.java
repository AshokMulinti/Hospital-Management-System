package com.ashok.Hospital_Management.service.interfaces;

import com.ashok.Hospital_Management.dto.PatientHealthRecordRequest;
import com.ashok.Hospital_Management.dto.PatientHealthRecordResponse;

import java.util.List;


public interface NurseService {

    PatientHealthRecordResponse saveRecord(PatientHealthRecordRequest request);
    List<PatientHealthRecordResponse> getRecordsByPatientId(Long patientId);

    List<PatientHealthRecordResponse> getAllHealthRecords();
}
