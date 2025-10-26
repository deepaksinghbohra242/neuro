    package com.neuromed.reports.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDate;

    @Entity
    @Table(name = "tests")
    @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
    public class Tests extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private LocalDate date;

        @Column(name = "prescription_id")
        private Long prescriptionId;

        @Column(name = "consultant_id")
        private Long consultantId;

        @Column(name = "appointment_id")
        private Long appointmentId;

    }
