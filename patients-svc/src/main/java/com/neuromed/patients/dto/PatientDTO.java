package com.neuromed.patients.dto;

import java.time.LocalDate;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class PatientDTO {
    private Long id;
    private Long userId;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String occupation;
    private String nationality;
    private String ppsn;
    private String addressLine1;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String status;// "archive" or "unarchive"
}
