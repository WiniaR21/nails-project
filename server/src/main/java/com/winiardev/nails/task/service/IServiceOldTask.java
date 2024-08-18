package com.winiardev.nails.task.service;

import java.util.List;

import com.winiardev.nails.task.dto.DtoTask;

/**
 * Service interface for managing old tasks.
 * Provides methods to fetch and delete old tasks.
 * 
 * @author winiar.dev
 */
public interface IServiceOldTask {

    /**
     * Retrieves a list of all old tasks.
     *
     * @return a list of all old tasks
     */
    public List<DtoTask> fetchAllOldTask();

    /**
     * Deletes an old task identified by the given task ID.
     *
     * @param taskId the ID of the task to be deleted
     */
    public void deleteOldTask(String taskId);
}
