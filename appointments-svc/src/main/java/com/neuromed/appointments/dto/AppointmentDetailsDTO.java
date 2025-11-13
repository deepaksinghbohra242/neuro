package com.neuromed.appointments.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "AppointmentDetails",
        description = "Schema to hold Appointment and Patient information"
)
public class AppointmentDetailsDTO {

    @Schema(description = "Consultant User Model")
    private UserModel consultantUserModel;

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
