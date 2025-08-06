package com.ashok.Hospital_Management.service.interfaces;

import com.ashok.Hospital_Management.dto.*;

import java.util.List;

public interface ReceptionistService {
    PatientResponse registerPatient(PatientRequest request);

    AppointmentResponse scheduleAppointment(AppointmentRequest request);

    AppointmentResponse updateAppointment(UpdateAppointmentRequest request);

    List<AppointmentResponse> getAllAppointments();
}
