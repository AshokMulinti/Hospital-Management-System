package com.ashok.Hospital_Management.repository;

import com.ashok.Hospital_Management.entities.MedicalHistory;
import com.ashok.Hospital_Management.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Long> {
    List<MedicalHistory> findByPatient(Patient patient);
    List<MedicalHistory> findByPatientId(Long patientId);

}
