package com.neuromed.consultants.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "consultants")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Consultant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(length = 100)
    private String specialization;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(length = 255)
    private String qualification;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ARCHIVED','UNARCHIVED') DEFAULT 'UNARCHIVED'")
    private Status status = Status.UNARCHIVED;

    public enum Status {
        ARCHIVED, UNARCHIVED
    }
}
