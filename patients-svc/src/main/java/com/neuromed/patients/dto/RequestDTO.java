package com.neuromed.patients.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDTO {
    private Long id;
    private String transferId;
    private LocalDateTime date;
    private Long consultantId;
    private String requestType;
    private String description;
    private Long patientId;
    private String status;
    private String reason;
    private Integer duration;
    private String doctorName;
    private String transferredTo;
}
