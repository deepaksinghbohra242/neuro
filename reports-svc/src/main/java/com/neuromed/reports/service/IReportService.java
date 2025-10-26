package com.neuromed.reports.service;

import com.neuromed.reports.dto.ReportDTO;
import java.util.List;

public interface IReportService {

    /**
     * Creates a new report record.
     *
     * @param reportDTO Data transfer object containing report details.
     * @return The created ReportDTO.
     */
    ReportDTO createReport(ReportDTO reportDTO);

    /**
     * Retrieves all report records.
     *
     * @return List of ReportDTOs.
     */
    List<ReportDTO> getReports();

    /**
     * Updates an existing report record.
     *
     * @param id The ID of the report to update.
     * @param reportDTO Data transfer object with updated report details.
     * @return The updated ReportDTO, or null if not found.
     */
    ReportDTO updateReport(Long id, ReportDTO reportDTO);

    /**
     * Deletes a report record by ID.
     *
     * @param id The ID of the report to delete.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deleteReport(Long id);

    /**
     * Fetches report details by ID.
     *
     * @param id The ID of the report to fetch.
     * @param correlationId The correlation ID for tracing requests.
     * @return The ReportDTO containing report details.
     */
    ReportDTO fetchReport(String id, String correlationId);
}
