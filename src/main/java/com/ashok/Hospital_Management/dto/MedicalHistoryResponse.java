package com.ashok.Hospital_Management.dto;

import java.time.LocalDate;

public record MedicalHistoryResponse(Long id, String diagnosis, String prescribedTests, String prescribedMedicines, LocalDate visitDate,
                                     LocalDate nextVisitDate) {
}
