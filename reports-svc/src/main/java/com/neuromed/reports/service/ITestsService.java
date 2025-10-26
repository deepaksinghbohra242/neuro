package com.neuromed.reports.service;

import com.neuromed.reports.dto.TestsDTO;

import java.util.List;

/**
 * Service interface for managing tests.
 * Provides methods for creating, fetching, updating, and deleting tests.
 */
public interface ITestsService {

    /**
     * Creates a new test.
     *
     * @param testsDTO the test details to create
     */
    void createTest(TestsDTO testsDTO);

    /**
     * Fetches a test by its ID.
     *
     * @param id the ID of the test to fetch
     * @return the test details
     */
    TestsDTO fetchTest(Long id);

    /**
     * Updates an existing test.
     *
     * @param testsDTO the test details to update
     * @return true if the update was successful, false otherwise
     */
    boolean updateTest(TestsDTO testsDTO);

    /**
     * Deletes a test by its ID.
     *
     * @param id the ID of the test to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteTest(Long id);

    /**
     * Retrieves all tests records.
     *
     * @return List of TestsDTO.
     */
    List<TestsDTO> getAllTests();

    /**
     * Retrieves all tests records by appointment id .
     *
     * @return List of TestsDTO.
     */
    List<TestsDTO> getTestsByAppointmentId(Long appointmentId);

}
