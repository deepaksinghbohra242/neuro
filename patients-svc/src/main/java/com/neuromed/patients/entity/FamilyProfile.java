package com.neuromed.patients.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "family_profiles")
public class FamilyProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId; // main patient reference

    private String firstName;
    private String lastName;
    private String relation; // e.g. "Mother", "Father", "Sibling"
    private int age;
    private String email;
}
