package com.neuromed.consultants.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class ConsultantEducations extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consultant_id", nullable = false)
    private Long consultantId;

    @Column(name = "degree_name", length = 255)
    private String degreeName;

    @Column(name = "field_of_study", length = 255)
    private String fieldOfStudy;

    @Column(name = "institution", length = 255)
    private String institution;

    @Column(name = "start_year")
    private LocalDate startYear;

    @Column(name = "end_year")
    private LocalDate endYear;

}
