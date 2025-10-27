package com.neuromed.appointments.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "AppointmentDetails",
        description = "Schema to hold Appointment and Patient information"
)
public class AppointmentDetailsDTO {


    @Schema(description = "Patient Full Name")
    private String patientName;

    @Schema(description = "Consultant Full Name")
    private String consultantName;

    @Schema(
            description = "ID of the appointment",
            example = "123"
    )
    private String appointmentId;

    @Schema(
            description = "Name of the consultant",
            example = "Dr. Smith"
    )
    private String consultantId;

    @Schema(
            description = "Detailed information of the patient"
    )
    private PatientDetailsDTO patientDetails;
}
