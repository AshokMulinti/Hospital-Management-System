package com.ashok.Hospital_Management.repository;

import com.ashok.Hospital_Management.entities.Patient;
import com.ashok.Hospital_Management.entities.PatientHealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientHealthRecordRepository extends JpaRepository<PatientHealthRecord, Long> {
    List<PatientHealthRecord> findByPatientId(Long patientId);
    List<PatientHealthRecord> findByPatient(Patient patient);
}
