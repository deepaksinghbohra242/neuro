package com.neuromed.appointments.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "appointments")
public class Appointment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "consultant_id", nullable = false)
    private Long consultantId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time_slot", nullable = false)
    private LocalTime timeSlot;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "visit_type")
    private String visitType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "billing_id")
    private Long billingId;

    @Transient
    public Integer getDuration() {
        if (timeSlot != null && endTime != null) {
            return (int) java.time.Duration.between(timeSlot, endTime).toMinutes();
        }
        return null;
    }

    public enum Status {
        PENDING, SCHEDULED, COMPLETED, CANCELLED
    }
}

