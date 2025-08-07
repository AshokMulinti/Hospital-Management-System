package com.ashok.Hospital_Management.service.implementation;

import com.ashok.Hospital_Management.dto.*;
import com.ashok.Hospital_Management.entities.Appointment;
import com.ashok.Hospital_Management.entities.Patient;
import com.ashok.Hospital_Management.exceptions.UserAlreadyExistException;
import com.ashok.Hospital_Management.exceptions.UserNotFoundException;
import com.ashok.Hospital_Management.repository.AppointmentRepository;
import com.ashok.Hospital_Management.repository.PatientRepository;
import com.ashok.Hospital_Management.service.interfaces.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceptionistServiceImpl implements ReceptionistService {


    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    @CacheEvict(value = "allPatients", allEntries = true)
    @Override
    public PatientResponse registerPatient(PatientRequest request){
        Optional<Patient> patientDb = patientRepository.findByEmail(request.email());
        if(patientDb.isPresent()){
           throw  new UserAlreadyExistException("Patient Already exists, plz book appointment");
        }
       Patient patient = Patient.builder()
               .username(request.username())
               .email(request.email())
               .phoneNo(request.phoneNo())
               .address(request.address())
               .gender(request.gender())
               .age(request.age())
               .build();
        Patient saved = patientRepository.save(patient);
        return new PatientResponse(saved.getId(), saved.getUsername(), saved.getEmail(), saved.getPhoneNo(), saved.getAddress(), saved.getGender(), saved.getAge());
    }
    @CacheEvict(value = "allAppointments", allEntries = true)
    @Override
    public AppointmentResponse scheduleAppointment(AppointmentRequest request){
        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(()->new UserNotFoundException("Patient with this id is  not found"));

        Appointment appointment = Appointment.builder()
                .date(request.date())
                .time(LocalTime.parse(request.time()))
                .status(request.status())
                .patient(patient)
                .build();
        Appointment savedAppointment = appointmentRepository.save(appointment);

        return new AppointmentResponse(
                savedAppointment.getId(),
                savedAppointment.getDate(),
                savedAppointment.getTime(),
                savedAppointment.getStatus());
    }
    @CacheEvict(value = "allAppointments", allEntries = true)
    @Override
    public AppointmentResponse updateAppointment(UpdateAppointmentRequest request) {
        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(()->new UserNotFoundException("Patient with this id is  not found"));

        Appointment appointment = Appointment.builder()
                .id(request.id())
                .date(request.date())
                .time(LocalTime.parse(request.time()))
                .status(request.status())
                .patient(patient)
                .build();
        Appointment savedAppointment = appointmentRepository.save(appointment);

        return new AppointmentResponse(
                savedAppointment.getId(),
                savedAppointment.getDate(),
                savedAppointment.getTime(),
                savedAppointment.getStatus());
    }
    @Cacheable(value = "allAppointments")
    @Override
    public List<AppointmentResponse> getAllAppointments() {
        System.out.println("Fetching from DB");
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream()
                .map(a -> new AppointmentResponse(
                        a.getId(),
                        a.getDate(),
                        a.getTime(),
                        a.getStatus()
                ))
                .toList();
    }
    @Cacheable(value = "allPatients")
    @Override
    public List<PatientResponse> getAllPatients() {
        System.out.println("fetching all patients");
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(p -> new PatientResponse(
                        p.getId(),
                        p.getUsername(),
                        p.getEmail(),
                        p.getPhoneNo(),
                        p.getAddress(),
                        p.getGender(),
                        p.getAge()
                ))
                .toList();
    }

}
