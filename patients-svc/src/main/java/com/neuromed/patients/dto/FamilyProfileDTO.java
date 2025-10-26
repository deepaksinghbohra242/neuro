package com.neuromed.patients.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyProfileDTO {
    private Long id;
    private Long patientId;
    private String firstName;
    private String lastName;
    private String relation;
    private int age;
    private String email;
}
