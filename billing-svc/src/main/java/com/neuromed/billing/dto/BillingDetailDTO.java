package com.neuromed.billing.dto;

import lombok.Data;

@Data
public class BillingDetailDTO {

    private Long id;
    private PatientDTO patientDTO;
    private AppointmentDTO appointmentDTO;

}
