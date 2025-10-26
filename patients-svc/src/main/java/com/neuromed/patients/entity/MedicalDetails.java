package com.neuromed.patients.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medical_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalDetails extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long profileId;

    @Column(length = 10)
    private String bloodGroup;

    private Integer creyOSScore;
    private Boolean physicallyChallenged;
    private Double weight;

    @Column(length = 20)
    private String bloodPressure;

    private Integer heartRate;

    @Column(columnDefinition = "TEXT")
    private String otherMedicalCondition;

    @Column(columnDefinition = "TEXT")
    private String allergies;

    private Integer smoking;
    private Integer drinking;
}
