package com.neuromed.billing.service;

import com.neuromed.billing.dto.BillingDetailDTO;
import com.neuromed.billing.dto.BillingsDTO;

import java.util.List;

/**
 * Service interface for managing patient billing records.
 * Provides methods for creating, fetching, updating, and deleting billing entries.
 */
public interface IBillingsService {

    /**
     * Creates a new billing record.
     *
     * @param dto the billing details to create
     */
    void createBillings(BillingsDTO dto);

    /**
     * Fetches a billing record by its ID.
     *
     * @param id            the ID of the billing record to fetch
     * @param correlationId the correlation ID for tracing requests
     * @return the billing record details
     */
    BillingDetailDTO fetchBillings(Long id, String correlationId);

    /**
     * Retrieves all billing records.
     *
     * @return a list of all billing records
     */
    List<BillingsDTO> getAllBillings();

    /**
     * Fetches one billing record by ID.
     *
     * @param id the ID of the billing record to fetch
     * @return the billing details if found
     * @throws RuntimeException if billing not found
     */
    BillingsDTO getBillingById(Long id);

    /**
     * Updates an existing billing record.
     *
     * @param dto the billing record details to update
     */
    void updateBillings(BillingsDTO dto);

    /**
     * Deletes a billing record by its ID.
     *
     * @param id the ID of the billing record to delete
     */
    void deleteBillings(Long id);

    /**
     * Retrieves all billing records by appointmentId.
     *
     * @return a list of all billing records
     */
    List<BillingsDTO> getBillingsByAppointmentId(Long appointmentId);
}
