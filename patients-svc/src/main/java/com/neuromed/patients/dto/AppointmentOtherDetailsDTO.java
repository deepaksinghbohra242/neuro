package com.neuromed.patients.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentOtherDetailsDTO {
    private Long id;
    private Long appointmentId;
    private String fullName;
    private String phone;
    private String email;
    private String relation;
    private String insuranceCompany;
    private String policyNumber;
}
