package com.neuromed.reports.service;


import com.neuromed.reports.dto.TestDocumentsDTO;
import java.util.List;

/**
 * Service interface for managing test document operations.
 */
public interface ITestDocumentsService {

    /**
     * Creates a new test document record.
     *
     * @param testDocumentsDTO Data transfer object containing document details.
     * @return The created TestDocumentsDTO.
     */
    TestDocumentsDTO createTestDocument(TestDocumentsDTO testDocumentsDTO);

    /**
     * Retrieves all test document records.
     *
     * @return List of TestDocumentsDTOs.
     */
    List<TestDocumentsDTO> getAllTestDocuments();

    /**
     * Retrieves all test documents by test ID.
     *
     * @param testId The ID of the test whose documents to retrieve.
     * @return List of TestDocumentsDTOs related to the given test.
     */
    List<TestDocumentsDTO> getDocumentsByTestId(Long testId);

    /**
     * Updates an existing test document record.
     *
     * @param id The ID of the document to update.
     * @param testDocumentsDTO Data transfer object with updated document details.
     * @return The updated TestDocumentsDTO, or null if not found.
     */
    TestDocumentsDTO updateTestDocument(Long id, TestDocumentsDTO testDocumentsDTO);

    /**
     * Deletes a test document by ID.
     *
     * @param id The ID of the document to delete.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deleteTestDocument(Long id);

    /**
     * Fetches a single test document by ID.
     *
     * @param id The ID of the document to fetch.
     * @return The TestDocumentsDTO containing document details.
     */
    TestDocumentsDTO getTestDocumentById(Long id);
}
