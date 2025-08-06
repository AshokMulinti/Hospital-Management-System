package com.ashok.Hospital_Management.dto;

import com.ashok.Hospital_Management.entities.Role;

import java.util.Set;

public record UpdateRequest(Long id, String username, String email, String password, String phoneNo, String role ) {
}
