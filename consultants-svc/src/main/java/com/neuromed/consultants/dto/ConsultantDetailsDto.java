package com.neuromed.consultants.dto;

import lombok.*;

import java.util.Date;

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
