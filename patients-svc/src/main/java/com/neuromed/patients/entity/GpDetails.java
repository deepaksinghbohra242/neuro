package com.neuromed.patients.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "gp_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GpDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "doctor_name", length = 100)
    private String doctorName;

    @Column(name = "clinic_name", length = 100)
    private String clinicName;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "licence_number", length = 50)
    private String licenceNumber;

    @Column(name = "patient_since")
    private LocalDate patientSince;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String city;
    private String state;
    private String country;
    private String zipCode;
}
