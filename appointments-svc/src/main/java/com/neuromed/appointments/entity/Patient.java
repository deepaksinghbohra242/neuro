package com.neuromed.appointments.entity;

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

    @Column(length = 20)
    private String gender;

    @Column(length = 50)
    private String contact;

    @Column(length = 200, nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Status status = Status.UNARCHIVED;

    public enum Status {
        ARCHIVED, UNARCHIVED
    }
}
