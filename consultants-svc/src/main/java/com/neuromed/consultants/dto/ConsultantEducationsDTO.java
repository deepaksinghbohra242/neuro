package com.neuromed.consultants.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class ConsultantEducationsDTO {
    private Long id;
    private Long consultantId;
    private String degreeName;
    private String fieldOfStudy;
    private String institution;
    private LocalDate  startYear;
    private LocalDate endYear;
}