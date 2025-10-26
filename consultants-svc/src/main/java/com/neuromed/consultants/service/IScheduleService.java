package com.neuromed.consultants.service;

import com.neuromed.consultants.dto.ScheduleDto;

/**
 * Service interface for managing schedules.
 * Provides methods for creating, fetching, updating, and deleting schedules.
 */


public interface IScheduleService {

    /**
     * Creates a new schedule for a consultant.
     *
     * @param scheduleDto the schedule details to create
     */
    void createSchedule(ScheduleDto scheduleDto);

    /**
     * Fetches a schedule by its ID.
     *
     * @param scheduleId the ID of the schedule to fetch
     * @return the schedule details
     */
    ScheduleDto fetchSchedule(Long scheduleId);

    /**
     * Updates an existing schedule.
     *
     * @param scheduleDto the schedule details to update
     * @return true if the update was successful, false otherwise
     */
    boolean updateSchedule(ScheduleDto scheduleDto);

    /**
     * Deletes a schedule by its ID.
     *
     * @param scheduleId the ID of the schedule to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteSchedule(Long scheduleId);

    /**
     * Fetches detailed information about a schedule for a consultant.
     *
     * @param scheduleId the ID of the schedule to fetch details for
     * @param correlationId the correlation ID for tracing requests
     * @return the detailed schedule information
     */
    ScheduleDto fetchScheduleDetails(Long scheduleId, String correlationId);
}
