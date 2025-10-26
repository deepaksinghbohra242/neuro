package com.neuromed.patients.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalDetailsDTO {
    private Long id;
    private Long profileId;
    private String bloodGroup;
    private Integer creyOSScore;
    private Boolean physicallyChallenged;
    private Double weight;
    private String height;
    private String bloodPressure;
    private Integer heartRate;
    private String otherMedicalCondition;
    private String allergies;
    private Integer smoking;
    private Integer drinking;
}
