package com.neuromed.pharmarcy.service;

import com.neuromed.pharmarcy.dto.PrescriptionDTO;
import com.neuromed.pharmarcy.dto.ReorderRequestDTO;

import java.util.List;
import com.neuromed.pharmarcy.dto.PrescriptionDetailDTO;

/**
 * Service interface for managing prescription operations.
 * Uses mapping via {@link com.neuromed.pharmarcy.mapper.PrescriptionsMapper}.
 */
public interface IPrescriptionService {

  /**
   * Creates a new prescription using the DTO and converts it to an entity.
   *
   * @param prescriptionDTO DTO containing prescription details.
   * @return The created PrescriptionDTO after mapping from entity.
   */
  PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO);

  /**
   * Retrieves all prescriptions from the database and maps them to DTOs.
   *
   * @return List of PrescriptionDTOs.
   */
  List<PrescriptionDTO> getAllPrescriptions();

  /**
   * Updates an existing prescription by its ID using the provided DTO.
   *
   * Internally maps DTO to entity and saves updated entity.
   *
   * @param id              The ID of the prescription to update.
   * @param prescriptionDTO DTO with updated prescription details.
   * @return The updated PrescriptionDTO after saving.
   */
  PrescriptionDTO updatePrescription(Long id, PrescriptionDTO prescriptionDTO);

  /**
   * Deletes a prescription by its ID.
   *
   * @param id The ID of the prescription to delete.
   * @return true if deletion was successful, false otherwise.
   */
  boolean deletePrescription(Long id);

  /**
   * Fetches a prescription by its ID and maps the entity to DTO.
   *
   * @param id The ID of the prescription.
   * @return The PrescriptionDTO containing prescription details.
   */
  PrescriptionDTO getPrescriptionById(Long id);

  /**
   * Fetches detailed prescription information by its ID, including associated
   * medicines.
   *
   * @param id The ID of the prescription.
   * @return The PrescriptionDetailDTO containing detailed prescription
   *         information.
   */
  PrescriptionDetailDTO getPrescriptionDetailById(Long id , String correlationId);

  List<PrescriptionDTO> listPrescriptions(String correlationId);


  PrescriptionDTO reorderPrescription(ReorderRequestDTO dto);
}
