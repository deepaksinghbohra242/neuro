package com.neuromed.appointments.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class AppointmentMedicalDetailsDTO {

    private Long id;

    private Long appointmentId;

    private BigDecimal weight;

    private String bloodPressure;

    private Integer heartRate;

    private LocalDate date;

    private String doctorName;

    private String clinicName;

    private String Address;


}
