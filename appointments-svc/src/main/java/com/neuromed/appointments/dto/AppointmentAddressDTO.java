package com.neuromed.appointments.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentAddressDTO {
    private Long id;
    private String fullAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
