package com.neuromed.billing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "billing_history")
public class BillingHistory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "patient_id", nullable = false)
    private Long patientId;
    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(columnDefinition = "TEXT")
    private String description;  // e.g. "Consultation Fee"
    @Column(nullable = false)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Status status = Status.UNPAID;

    public enum Status {
        PAID,
        UNPAID
    }
}
