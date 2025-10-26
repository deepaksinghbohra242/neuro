package com.neuromed.appointments.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class PatientDTO {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
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
