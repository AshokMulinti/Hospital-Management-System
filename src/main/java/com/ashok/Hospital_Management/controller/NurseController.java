package com.ashok.Hospital_Management.controller;

import com.ashok.Hospital_Management.dto.PatientHealthRecordRequest;
import com.ashok.Hospital_Management.dto.PatientHealthRecordResponse;
import com.ashok.Hospital_Management.service.interfaces.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurse")
@RequiredArgsConstructor
public class NurseController {
    private final NurseService healthRecordService;

    @PostMapping("/health-records")
    public ResponseEntity<PatientHealthRecordResponse> addHealthRecord(
            @RequestBody PatientHealthRecordRequest request) {
          PatientHealthRecordResponse response = healthRecordService.saveRecord(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health-records/{patientId}")
    public ResponseEntity<List<PatientHealthRecordResponse>> getPatientHealthRecords(
            @PathVariable Long patientId) {
           List<PatientHealthRecordResponse> response = healthRecordService.getRecordsByPatientId(patientId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/health-records")
    public ResponseEntity<List<PatientHealthRecordResponse>> getAllHealthRecords() {
        List<PatientHealthRecordResponse> response = healthRecordService.getAllHealthRecords();
        return ResponseEntity.ok(response);
    }

}
