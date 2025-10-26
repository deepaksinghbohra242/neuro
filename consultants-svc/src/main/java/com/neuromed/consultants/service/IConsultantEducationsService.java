package com.neuromed.consultants.service;

import com.neuromed.consultants.dto.ConsultantEducationsDTO;
import java.util.List;

/**
 * Service interface for managing consultant education records.
 */
public interface IConsultantEducationsService {

    /**
     * Creates a new consultant education record.
     *
     * @param educationDTO Data transfer object containing education details.
     * @return The created ConsultantEducationsDTO.
     */
    ConsultantEducationsDTO createConsultantEducation(ConsultantEducationsDTO educationDTO);

    /**
     * Retrieves all consultant education records.
     *
     * @return List of ConsultantEducationsDTO.
     */
    List<ConsultantEducationsDTO> getAllConsultantEducations();

    /**
     * Updates an existing consultant education record.
     *
     * @param id The ID of the education record to update.
     * @param educationDTO Data transfer object with updated education details.
     * @return The updated ConsultantEducationsDTO, or null if not found.
     */
    ConsultantEducationsDTO updateConsultantEducation(Long id, ConsultantEducationsDTO educationDTO);

    /**
     * Deletes a consultant education record by ID.
     *
     * @param id The ID of the education record to delete.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deleteConsultantEducation(Long id);

    /**
     * Fetches consultant education details by ID.
     *
     * @param id The ID of the education record to fetch.
     * @param correlationId The correlation ID for tracing requests.
     * @return The ConsultantEducationsDTO containing education details.
     */
    ConsultantEducationsDTO fetchConsultantEducation(Long id, String correlationId);
}
