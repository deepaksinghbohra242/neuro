package com.neuromed.consultants.dto;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class PatientDTO {
    private Long id;
    private Long userId;
    private String gender;
    private String contact;
    private String address;
    private String status; // "archive" or "unarchive"
}
