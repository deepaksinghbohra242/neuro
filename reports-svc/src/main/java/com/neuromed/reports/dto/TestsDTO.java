package com.neuromed.reports.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class TestsDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private Long prescriptionId;
    private Long consultantId;
    private Long appointmentId;
}
