package com.neuromed.pharmarcy.service;

import com.neuromed.pharmarcy.dto.PrescriptionMedicineDTO;

import java.util.List;

/**
 * Service interface for managing pharmacy medicine records.
 * Provides methods for creating, fetching, updating, and deleting pharmacy medicines.
 */
public interface IPrescriptionMedicineService {

    /**
     * Creates a new Prescription medicine record.
     *
     * @param prescriptionMedicineDTO the prescription medicine details to create
     * @return The created PrescriptionMedicineDTO
     */
    PrescriptionMedicineDTO createPrescriptionMedicine(PrescriptionMedicineDTO prescriptionMedicineDTO);

    /**
     * Fetches a prescription medicine record by its ID.
     *
     * @param id the ID of the prescription medicine record to fetch
     * @return the PrescriptionMedicineDTO containing the details
     */
    PrescriptionMedicineDTO fetchPrescriptionMedicine(Long id);

    /**
     * Updates an existing prescription medicine record.
     *
     * @param prescriptionMedicineDTO the pharmacy medicine details to update
     * @return true if the update was successful, false otherwise
     */
    boolean updatePrescriptionMedicine(PrescriptionMedicineDTO prescriptionMedicineDTO);

    /**
     * Deletes a prescription medicine record by its ID.
     *
     * @param id the ID of the prescription medicine record to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deletePrescriptionMedicine(Long id);

    /**
     * Fetches detailed prescription medicine information with correlation ID for tracing/logging.
     *
     * @param id the ID of the prescription medicine record to fetch
     * @param correlationId the correlation ID for request tracing
     * @return the PrescriptionMedicineDTO containing the details
     */
    PrescriptionMedicineDTO fetchPrescriptionMedicine(Long id, String correlationId);

    /**
     * Retrieves all prescription medicine records.
     *
     * @return List of PrescriptionMedicineDTOs
     */
    List<PrescriptionMedicineDTO> getAllPrescriptionMedicines();

    /**
     * Retrieves all medicines associated with a specific prescription ID.
     *
     * @param prescriptionId the ID of the prescription
     * @return List of PrescriptionMedicineDTOs associated with the given prescription ID
     */
    List<PrescriptionMedicineDTO> getMedicinesByPrescriptionId(Long prescriptionId);
}
