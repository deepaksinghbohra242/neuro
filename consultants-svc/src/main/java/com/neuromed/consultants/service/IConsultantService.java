package com.neuromed.consultants.service;

import com.neuromed.consultants.dto.ConsultantDetailsDto;
import com.neuromed.consultants.dto.ConsultantDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing consultants.
 * Provides methods for creating, fetching, updating, and deleting consultants.
 */
public interface IConsultantService {

    /**
     * Creates a new consultant.
     *
     * @param consultantDto the consultant details to create
     */
    void createConsultant(ConsultantDto consultantDto);

    /**
     * Fetches a consultant by its ID.
     *
     * @param consultantId the ID of the consultant to fetch
     * @return the consultant details
     */
    ConsultantDto fetchConsultant(Long consultantId);

    /**
     * Updates an existing consultant.
     *
     * @param consultantDto the consultant details to update
     * @return true if the update was successful, false otherwise
     */
    boolean updateConsultant(ConsultantDto consultantDto);

    /**
     * Deletes a consultant by its ID.
     *
     * @param consultantId the ID of the consultant to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteConsultant(Long consultantId);

    /**
     * Fetches detailed information about a consultant.
     *
     * @param consultantId  the ID of the consultant to fetch details for
     * @param correlationId the correlation ID for tracing requests
     * @return the detailed consultant information
     */
    ConsultantDetailsDto fetchConsultantDetails(Long consultantId, String correlationId);

    /**
     * Fetches a list of all consultants.
     *
     * @return a list of ConsultantDetailsDto objects
     */
    // List<ConsultantDto> listConsultants();
    List<ConsultantDetailsDto> listConsultants(String sortBy, String sortDir , String correlationId);

    /**
     * Finds consultants who are specialized for the appointmentType and available
     * on the date.
     */
    List<ConsultantDto> findAvailableConsultants(String visitType, LocalDate date);
}
