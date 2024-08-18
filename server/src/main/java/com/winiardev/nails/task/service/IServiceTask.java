package com.winiardev.nails.task.service;

import java.util.List;

import com.winiardev.nails.task.dto.DtoTask;

/**
 * Interface defining the service layer for managing tasks.
 * Provides methods to perform CRUD operations on tasks.
 * 
 * @author winiar.dev
 */
public interface IServiceTask {

    /**
     * Fetches all tasks.
     *
     * @return a list of all tasks in {@link DtoTask} format.
     */
    List<DtoTask> fetchAllTask();

    /**
     * Fetches a task by its ID.
     *
     * @param taskId the ID of the task to fetch.
     * @return the task matching the given ID in {@link DtoTask} format.
     */
    DtoTask fetchTaskById(String taskId);

    /**
     * Adds a new task.
     *
     * @param dtoTask the task data to be added in {@link DtoTask} format.
     */
    void addTask(DtoTask dtoTask);

    /**
     * Updates an existing task.
     *
     * @param dtoTask the updated task data in {@link DtoTask} format.
     */
    void updateTask(DtoTask dtoTask);

    /**
     * Deletes a task by its ID. Before deleting saves to old tasks repo.
     *
     * @param taskId the ID of the task to be deleted.
     */
    void deleteTask(String taskId);
}
