package com.neuromed.patients.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;

@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_id")
    private String transferId;
    
    private LocalDateTime date;

    @Column(name = "consultant_id")
    private Long consultantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type", length = 30)
    private RequestType requestType;

    @Column(name = "transferred_to")
    private String transferredTo;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String doctorName;

    @Column(name = "patient_id")
    private Long patientId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status = Status.PENDING;

    @Column(columnDefinition = "TEXT")
    private String reason;

    private Integer duration; // duration in days/minutes/hours based on request type

    public enum RequestType {
        LEAVE_LETTER,
        APPOINTMENT,
        PRESCRIPTIONS, 
        PATIENT_TRANSFER,
        GENERAL;
        @JsonCreator
        public static RequestType fromString(String value) {
            return RequestType.valueOf(value.toUpperCase());
        }
    }

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
}
