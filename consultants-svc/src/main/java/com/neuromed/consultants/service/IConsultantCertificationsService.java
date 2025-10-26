package com.neuromed.consultants.service;

import com.neuromed.consultants.dto.ConsultantCertificationsDTO;

import java.util.List;

/**
 * Service interface for managing consultantCertifications operations.
 */
public interface IConsultantCertificationsService {
    /**
     * Creates a new consultants Certifications record.
     *
     * @param certificationsDTO Data transfer object containing consultantCertifications details.
     * @return The created ConsultantCertificationsDTO.
     */
    ConsultantCertificationsDTO createConsultantCertifications(ConsultantCertificationsDTO certificationsDTO);

    /**
     * Retrieves all consultant certifications records.
     *
     * @return List of ConsultantCertificationsDTO.
     */
    List<ConsultantCertificationsDTO> getConsultantCertifications();

    /**
     * Updates an existing consultant Certifications record.
     *
     * @param id The ID of the consultantCertifications to update.
     * @param certificationsDTO Data transfer object with updated consultantCertifications details.
     * @return The updated certificationsDTO, or null if not found.
     */
    ConsultantCertificationsDTO updateConsultantCertifications(Long id, ConsultantCertificationsDTO certificationsDTO);

    /**
     * Deletes a consultant record by ID.
     *
     * @param id The ID of the consultantCertifications to delete.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deleteConsultantCertifications(Long id);

    /**
     * Fetches consultantCertifications details by ID.
     *
     * @param id            The ID of the consultants to fetch.
     * @param correlationId The correlation ID for tracing requests.
     * @return The ConsultantCertificationsDTO containing consultants details.
     */
    ConsultantCertificationsDTO fetchConsultantCertifications(Long id, String correlationId);
}


