package com.neuromed.appointments.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

    @Entity
    @Table(name = "appointment_medical_details")
    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
    public class AppointmentMedicalDetails extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "appointment_id", nullable = false)
        private Long appointmentId;

        @Column(precision = 5, scale = 2)
        private BigDecimal weight;

        @Column(name = "blood_pressure", length = 20)
        private String bloodPressure;

        @Column(name = "heart_rate")
        private Integer heartRate;

        private LocalDate date;

        @Column(name = "doctor_name", length = 100)
        private String doctorName;

        @Column(name = "clinic_name", length = 100)
        private String clinicName;

        @Column(name = "address", length = 255)
        private String address;

    }
