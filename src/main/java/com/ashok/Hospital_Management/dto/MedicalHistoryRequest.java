package com.ashok.Hospital_Management.dto;

import java.time.LocalDate;

public record MedicalHistoryRequest(Long id, String diagnosis, String prescribedTests, String prescribedMedicines, LocalDate nextVisitDate ) {
}
