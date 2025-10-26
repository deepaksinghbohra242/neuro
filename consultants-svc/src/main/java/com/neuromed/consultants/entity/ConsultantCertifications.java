package com.neuromed.consultants.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "certifications")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsultantCertifications extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "consultant_id", nullable = false)
    private Long consultantId;
    @Column(name = "url", length = 255)
    private String url;

}
