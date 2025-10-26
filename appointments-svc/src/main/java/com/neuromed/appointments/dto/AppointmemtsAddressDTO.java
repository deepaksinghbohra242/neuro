package com.neuromed.appointments.dto;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class AppointmemtsAddressDTO {
    private Long id;
    private Long  appointmentId;
    private String fullAddress;
    private String city;
    private String state;
    private String country; // "archive" or "unarchive"
    private String zipCode;



}
