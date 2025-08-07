package com.ashok.Hospital_Management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "patient_health_records")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientHealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodPressure;
    private int sugarLevel;
    private double weight;
    private double temperature;
    private int oxygenLevel;
    private String healthStatus;
    private String notes;

    @Builder.Default
    private LocalDate recordDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
