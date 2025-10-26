package com.neuromed.pharmarcy.service;


import com.neuromed.pharmarcy.dto.OrdersDTO;

import java.util.List;

/**
 * Service interface for managing orders operations.
 */
public interface IOrdersService {

    /**
     * Creates a new orders record.
     *
     * @param ordersDTO Data transfer object containing orders details.
     * @return The created OrdersDTO.
     */
    OrdersDTO createOrders(OrdersDTO ordersDTO);

    /**
     * Retrieves all orders records.
     *
     * @return List of OrdersDTO.
     */
    List<OrdersDTO> getOrders();

    /**
     * Updates an existing orders record.
     *
     * @param id The ID of the orders to update.
     * @param ordersDTO Data transfer object with updated orders details.
     * @return The updated PatientDTO, or null if not found.
     */
    OrdersDTO updateOrders(Long id, OrdersDTO ordersDTO);

    /**
     * Deletes a patient record by ID.
     *
     * @param id The ID of the orders to delete.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deleteOrders(Long id);

    /**
     * Fetches patient details by orders ID.
     *
     * @param id The id of the orders to fetch.
     * @param correlationId The correlation ID for tracing requests.
     * @return The OrdersDTO containing orders details.
     */
    OrdersDTO fetchOrders(Long id, String correlationId);

    /**
     * Fetches orders by prescriptionId.
     *
     * @param prescriptionId The ID of the orders to delete.
     * @return The List of OrdersDTO containing orders details.
     */
    List<OrdersDTO> getOrdersByPrescriptionId(Long prescriptionId);
}
