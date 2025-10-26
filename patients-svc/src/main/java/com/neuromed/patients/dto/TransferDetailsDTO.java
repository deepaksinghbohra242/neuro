package com.neuromed.patients.dto;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDetailsDTO {
    private String transferId;
    private LocalDate date;
    private String transferredBy;
    private String transferredTo;
    private String reason;
    private String status;

    private PatientDTO patient;
    private MedicalDetailsDTO medicalOverview;
    private GpDetailsDTO gpDetails;
    private AppointmentOtherDetailsDTO appointmentOtherDetails;
}