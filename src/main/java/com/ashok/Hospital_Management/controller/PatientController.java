package com.ashok.Hospital_Management.controller;
import com.ashok.Hospital_Management.dto.PatientFullHealthResponse;
import com.ashok.Hospital_Management.service.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/health-info")
    public ResponseEntity<PatientFullHealthResponse> getHealthInfoByEmail(
            @RequestParam String email) {
        PatientFullHealthResponse response = patientService.getFullHealthInfoByEmail(email);
        return ResponseEntity.ok(response);
    }
}