package com.neuromed.pharmarcy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Orders extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "prescription_id", nullable = false)
    private Long prescriptionId;

    @Column(name = "status")
    private String status;

    @Column(name = "reason_for_return_reject")
    private String reasonForReturnReject;

    public enum Status {
        RETURN,
        REJECT,
        APPROVE
    }
}
