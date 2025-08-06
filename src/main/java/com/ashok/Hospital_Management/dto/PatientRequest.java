package com.ashok.Hospital_Management.dto;

public record PatientRequest(String username, String email, String phoneNo, String address, String gender, int age) {
}
