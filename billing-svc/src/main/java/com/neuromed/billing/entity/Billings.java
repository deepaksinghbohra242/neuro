package com.neuromed.billing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "billings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Billings  extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('PAID','UNPAID') default 'UNPAID'")
    private Status status = Status.UNPAID;

    public enum Status {
        PAID,
        UNPAID
    }
}

