package com.neuromed.pharmarcy.service;

import com.neuromed.pharmarcy.dto.PharmacyDTO;
import java.util.List;

/**
 * Service interface for managing pharmacy operations.
 */
public interface IPharmacyService {

    /**
     * Creates a new pharmacy record.
     *
     * @param pharmacyDTO Data transfer object containing pharmacy details.
     * @return The created PharmacyDTO.
     */
    PharmacyDTO createPharmacy(PharmacyDTO pharmacyDTO);

    /**
     * Retrieves all pharmacy records.
     *
     * @return List of PharmacyDTOs.
     */
    List<PharmacyDTO> getPharmacies();

    /**
     * Updates an existing pharmacy record.
     *
     * @param id The ID of the pharmacy to update.
     * @param pharmacyDTO Data transfer object with updated pharmacy details.
     * @return The updated PharmacyDTO, or null if not found.
     */
    PharmacyDTO updatePharmacy(Long id, PharmacyDTO pharmacyDTO);

    /**
     * Deletes a pharmacy record by ID.
     *
     * @param id The ID of the pharmacy to delete.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deletePharmacy(Long id);

    /**
     * Fetches pharmacy details by ID.
     *
     * @param pharmacyId The ID of the pharmacy to fetch.
     * @param correlationId The correlation ID for tracing requests.
     * @return The PharmacyDTO containing pharmacy details.
     */
    PharmacyDTO fetchPharmacy(String pharmacyId, String correlationId);
}
