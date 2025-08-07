package com.ashok.Hospital_Management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientHealthRecordRequest {
    private Long id;
    private String bloodPressure;
    private int sugarLevel;
    private double weight;
    private double temperature;
    private int oxygenLevel;
    private String healthStatus;
    private String notes;
}
