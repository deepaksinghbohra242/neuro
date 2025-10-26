package com.neuromed.patients.service;

import com.neuromed.patients.dto.PatientDTO;
import com.neuromed.patients.dto.PatientDetailsDTO;

import java.util.List;

/**
 * Service interface for managing patient operations.
 */
public interface IPatientService {

    /**
     * Creates a new patient record.
     *
     * @param patientDTO Data transfer object containing patient details.
     * @return The created PatientDTO.
     */
    PatientDTO createPatient(PatientDTO patientDTO);

    /**
     * Retrieves all patient records.
     *
     * @return List of PatientDTOs.
     */
    List<PatientDTO> getPatients();

    /**
     * Updates an existing patient record.
     *
     * @param id The ID of the patient to update.
     * @param patientDTO Data transfer object with updated patient details.
     * @return The updated PatientDTO, or null if not found.
     */
    PatientDTO updatePatient(Long id, PatientDTO patientDTO);

    /**
     * Deletes a patient record by ID.
     *
     * @param id The ID of the patient to delete.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deletePatient(Long id);

    /**
     * Fetches patient details by patient ID.
     *
     * @param patientId The ID of the patient to fetch.
     * @param correlationId The correlation ID for tracing requests.
     * @return The PatientDTO containing patient details.
     */
    PatientDetailsDTO fetchPatient(Long patientId, String correlationId);
}
