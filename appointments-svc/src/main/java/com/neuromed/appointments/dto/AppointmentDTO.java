package com.neuromed.appointments.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Schema(
        name = "Appointment",
        description = "Schema to hold Appointment information"
)
public class AppointmentDTO {

    private Long id;
    private Long patientId;
    private Long consultantId;
    private LocalDate date;
    @Schema(type = "string", example = "14:30:00", description = "Time slot in HH:mm:ss format")
    private LocalTime timeSlot;
    private LocalTime endTime;
    private String status;
    private String visitType;
    private String reason;
    private Long billingId;
    private Integer duration;
    private UserModel userModel;

    public Integer getDuration() {
        if (timeSlot != null && endTime != null) {
            return (int) java.time.Duration.between(timeSlot, endTime).toMinutes();
        }
        return null;
    }
}
