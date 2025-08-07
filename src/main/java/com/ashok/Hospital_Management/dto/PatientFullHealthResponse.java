package com.ashok.Hospital_Management.dto;

import com.ashok.Hospital_Management.entities.MedicalHistory;
import com.ashok.Hospital_Management.entities.PatientHealthRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientFullHealthResponse {
    private List<MedicalHistory> medicalHistories;
    private List<PatientHealthRecord> healthRecords;
}
