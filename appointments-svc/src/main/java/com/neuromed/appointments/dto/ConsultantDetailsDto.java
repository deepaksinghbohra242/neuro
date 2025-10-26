package com.neuromed.appointments.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultantDetailsDto {
    private Long id;
    private UserModel userModel;
    private String specialization;
    private Integer experienceYears;
    private String qualification;
    private String status;
}
