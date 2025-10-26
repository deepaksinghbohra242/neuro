package com.neuromed.appointments.service;


import com.neuromed.appointments.dto.AppointmentDTO;
import com.neuromed.appointments.dto.AppointmentDetailsDTO;
import java.util.List;
/**
 * Service interface for managing appointments.
 * Provides methods for creating, fetching, updating, and deleting appointments.
 */
public interface IAppointmentService {

    /**
     * Creates a new appointment.
     *
     * @param appointmentDto the appointment details to create
     */
    void createAppointment(AppointmentDTO appointmentDto);

    /**
     * Fetches an appointment by its ID.
     *
     * @param appointmentId the ID of the appointment to fetch
     * @return the appointment details
     */
    AppointmentDTO fetchAppointment(String correlationId, Long appointmentId);

    /**
     * Updates an existing appointment.
     *
     * @param appointmentDto the appointment details to update
     * @return true if the update was successful, false otherwise
     */
    boolean updateAppointment(AppointmentDTO appointmentDto);

    /**
     * Deletes an appointment by its ID.
     *
     * @param appointmentId the ID of the appointment to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteAppointment(Long appointmentId);

    /**
     * Fetches detailed information about an appointment, including patient details.
     *
     * @param appointmentId the ID of the appointment to fetch details for
     * @param correlationId the correlation ID for tracing requests
     * @return the detailed appointment information
     */
    AppointmentDetailsDTO fetchAppointmentDetails(Long appointmentId, String correlationId);
    List<AppointmentDTO> listAppointments(String correlationId);
}
