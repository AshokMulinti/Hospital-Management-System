package com.ashok.Hospital_Management.dto;

import java.time.LocalDate;

public record UpdateAppointmentRequest(Long id, Long patientId, LocalDate date, String time, String status) {
}
