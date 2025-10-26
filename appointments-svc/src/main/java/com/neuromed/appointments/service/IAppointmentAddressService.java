package com.neuromed.appointments.service;

import com.neuromed.appointments.dto.AppointmemtsAddressDTO;

/**
 * Service interface for managing appointment addresses.
 * Provides methods for creating, fetching, updating, and deleting appointment addresses.
 */
public interface IAppointmentAddressService {

    /**
     * Creates a new appointment address.
     *
     * @param addressDto the appointment address details to create
     */
    void createAppointmentAddress(AppointmemtsAddressDTO addressDto);

    /**
     * Fetches an appointment address by its ID.
     *
     * @param addressId the ID of the appointment address to fetch
     * @return the appointment address details
     */
    AppointmemtsAddressDTO fetchAppointmentAddress(Long addressId);

    /**
     * Updates an existing appointment address.
     *
     * @param addressDto the appointment address details to update
     * @return true if the update was successful, false otherwise
     */
    boolean updateAppointmentAddress(AppointmemtsAddressDTO addressDto);

    /**
     * Deletes an appointment address by its ID.
     *
     * @param addressId the ID of the appointment address to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteAppointmentAddress(Long addressId);
}
