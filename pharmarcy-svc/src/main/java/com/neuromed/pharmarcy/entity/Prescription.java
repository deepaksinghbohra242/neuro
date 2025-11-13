package com.neuromed.pharmarcy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Prescription extends  BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "patient_id", nullable = false)
  private Long patientId;

  @Column(name = "consultant_id", nullable = false)
  private Long consultantId;

  @Column(name = "pharmacy_id")
  private Long pharmacyId;

  @Column(nullable = false)
  private Timestamp date;

  @Column(name = "no_of_medicines")
  private Integer noOfMedicines;

  @Column(name = "duration")
  private Integer duration;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private Status status = Status.NEW;

  @Enumerated(EnumType.STRING)
  @Column(name = "preferred_service", length = 20)
  private PreferredService preferredService;

  public enum PreferredService {
    IN_STORE_PICKUP("In-Store pickup"),
    HOME_DELIVERY("Home Delivery");

    private final String displayName;

    PreferredService(String displayName) {
      this.displayName = displayName;
    }

    public String getDisplayName() {
      return displayName;
    }
  }
  public enum Status {
    NEW , RETURNED , REJECTED
  }
}
