package com.neuromed.consultants.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consultant_id", nullable = false)
    private Long consultantId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime ;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "end_time", nullable = false)
    private  LocalTime endTime;

    @Column(name = "reason_for_unavailability", nullable = false)
    private String reasonForUnavailability;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('schedule','completed','pending','cancelled','internal_meeting')")
    private Status status;

    public enum Status {
       schedule, completed, pending, cancelled, internal_meeting
    }
}