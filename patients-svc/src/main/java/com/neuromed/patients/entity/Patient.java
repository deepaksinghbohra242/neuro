package com.neuromed.patients.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Patient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String phoneNumber;

    private LocalDate dateOfBirth;
    private String occupation;
    private String nationality;
    private String ppsn;
    @Column(name = "address_line1")
    private String addressLine1;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Status status = Status.UNARCHIVED;

    public enum Status {
        ARCHIVED, UNARCHIVED
    }
}
