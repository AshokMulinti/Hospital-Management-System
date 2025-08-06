package com.ashok.Hospital_Management.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponse(Long id, LocalDate date, LocalTime time,String status) {
}
