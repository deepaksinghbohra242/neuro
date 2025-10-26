package com.neuromed.patients.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GpDetailsDTO {
    private Long id;
    private Long profileId;
    private String doctorName;
    private String clinicName;
    private String phoneNumber;
    private String email;
    private String licenceNumber;
    private LocalDate patientSince;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
