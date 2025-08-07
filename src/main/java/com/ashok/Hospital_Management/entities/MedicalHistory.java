package com.ashok.Hospital_Management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "medical_history")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosis;

    private String prescribedTests;

    private String prescribedMedicines;

    private LocalDate visitDate = LocalDate.now();

    private LocalDate nextVisitDate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
