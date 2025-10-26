package com.neuromed.appointments.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "appointment_other_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentOtherDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "relation")
    private String relation;

    @Column(name = "insurance_company")
    private String insuranceCompany;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "documents")
    private String documents; // store comma-separated file paths

}
