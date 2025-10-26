package com.neuromed.appointments.service;
import com.neuromed.appointments.dto.AppointmentMedicalDetailsDTO;

/**
 * Service interface for managing appointmentMedical.
 * Provides methods for creating, fetching, updating, and deleting appointmentMedical.
 */

public interface IAppointmentMedicalService {

    /**
     * Creates a new appointmentMedical.
     *
     * @param appointmentMedicalDetailsDTO the appointmentMedical details to create
     */
    void createAppointmentMedicalDetails(AppointmentMedicalDetailsDTO appointmentMedicalDetailsDTO);

    /**
     * Fetches an appointmentMedical by its ID.
     *
     * @param id the ID of the appointment to fetch
     * @return the appointmentMedicalDetailsDTO details
     */
    AppointmentMedicalDetailsDTO fetchAppointmentMedicalDetails(Long id);

    /**
     * Updates an existing appointmentMedicalDetails.
     *
     * @param appointmentMedicalDetailsDTO the appointmentMedical details to update
     * @return true if the update was successful, false otherwise
     */
    boolean updateAppointmentMedicalDetails(AppointmentMedicalDetailsDTO appointmentMedicalDetailsDTO);

    /**
     * Deletes an appointmentMedical by its ID.
     *
     * @param id the ID of the appointmentMedical to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteAppointmentMedicalDetails(Long id);

    /**
     * Fetches detailed information about an appointmentMedical, including patient details.
     *
     * @param id the ID of the appointmentMedical to fetch details for
     * @param correlationId the correlation ID for tracing requests
     * @return the detailed appointmentMedical information
     */
    AppointmentMedicalDetailsDTO fetchAppointmentMedicalDetails(Long id, String correlationId);



}
