package com.ashok.Hospital_Management.controller;

import com.ashok.Hospital_Management.dto.*;
import com.ashok.Hospital_Management.service.interfaces.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/receptionist")
@RequiredArgsConstructor
public class ReceptionistController {

    private final ReceptionistService receptionistService;

    @PostMapping("/register-patient")
    public ResponseEntity<PatientResponse> registerPatient(@RequestBody PatientRequest request){
        PatientResponse response = receptionistService.registerPatient(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/schedule-appointment")
    public ResponseEntity<AppointmentResponse> scheduleAppointment(@RequestBody AppointmentRequest request) {
        AppointmentResponse response = receptionistService.scheduleAppointment(request);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update-appointment")
    public ResponseEntity<AppointmentResponse> updateAppointment(@RequestBody UpdateAppointmentRequest request) {
        AppointmentResponse response = receptionistService.updateAppointment(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> response = receptionistService.getAllAppointments();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        List<PatientResponse> response = receptionistService.getAllPatients();
        return ResponseEntity.ok(response);
    }
}
