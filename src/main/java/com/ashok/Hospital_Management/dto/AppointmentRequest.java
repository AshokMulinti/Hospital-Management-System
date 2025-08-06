package com.ashok.Hospital_Management.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequest(Long patientId, LocalDate date, String time,String status) {
}
