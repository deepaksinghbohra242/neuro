package com.neuromed.reports.dto;
import lombok.*;
import java.time.LocalDate;

@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor
public class TestDocumentsDTO {

    private Long id;
    private Long testId;
    private String name;
    private LocalDate date;
    private String url;


}
