package com.neuromed.consultants.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultantDto {
    private Long id;
    private Long userId;
    private String specialization;
    private Integer experienceYears;
    private String qualification;
    private String status;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
}
