package com.ashok.Hospital_Management.controller;

import com.ashok.Hospital_Management.dto.AppointmentResponse;
import com.ashok.Hospital_Management.dto.MedicalHistoryRequest;
import com.ashok.Hospital_Management.dto.MedicalHistoryResponse;
import com.ashok.Hospital_Management.service.interfaces.DoctorService;
import com.ashok.Hospital_Management.service.interfaces.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final ReceptionistService receptionistService;
    private final DoctorService doctorService;


    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> appointments = receptionistService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }
    @PostMapping("/add-medical-history")
    public ResponseEntity<MedicalHistoryResponse> addMedicalHistory(@RequestBody MedicalHistoryRequest request) {
        MedicalHistoryResponse response = doctorService.addMedicalHistory(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/medical-history/{patientId}")
    public ResponseEntity<List<MedicalHistoryResponse>> getMedicalHistoryByPatientId(@PathVariable Long patientId) {
        List<MedicalHistoryResponse> response = doctorService.getMedicalHistoryByPatientId(patientId);
        return ResponseEntity.ok(response);
    }

}
