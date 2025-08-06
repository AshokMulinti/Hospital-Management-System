package com.ashok.Hospital_Management.dto;

public record CreateUserRequest(String username, String email, String password, String phoneNo, String role) {
}
