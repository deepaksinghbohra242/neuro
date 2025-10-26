package com.neuromed.pharmarcy.entity;

import com.neuromed.pharmarcy.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "license_number", length = 50, nullable = false)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private Status status = Status.UNARCHIVE;

    public enum Status {
        ARCHIVE, UNARCHIVE
    }
}
