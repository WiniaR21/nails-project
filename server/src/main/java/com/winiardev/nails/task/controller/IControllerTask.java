package com.winiardev.nails.task.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.winiardev.nails.task.dto.DtoTask;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Interface defining RESTful API endpoints for managing tasks.
 * 
 * @author winiar.dev
 */
@Validated
public interface IControllerTask {

    /**
     * Fetches all tasks.
     *
     * @return a {@link ResponseEntity} containing a list of all tasks in
     *         {@link DtoTask} format.
     */
    @GetMapping(path = "/task/fetch/all")
    ResponseEntity<List<DtoTask>> fetchAllTask();

    /**
     * Fetches a task by its ID.
     *
     * @param taskId the ID of the task to fetch.
     * @return a {@link ResponseEntity} with containing the matching task in
     *         {@link DtoTask} format.
     */
    @GetMapping(path = "/task/fetch/by-id")
    ResponseEntity<DtoTask> fetchTaskById(@NotNull @RequestParam String taskId);

    /**
     * Adds a new task.
     * This operation is secured and can only be performed by users with the "ADMIN"
     * role.
     *
     * @param dtoTask the task data to be added in {@link DtoTask} format.
     * @return a {@link ResponseEntity} with no content (HTTP status 204) indicating
     *         the task was added successfully.
     */
    @Secured({ "ADMIN" })
    @PostMapping(path = "/task/add")
    ResponseEntity<Void> addTask(@Valid @RequestBody DtoTask dtoTask);

    /**
     * Updates an existing task.
     * This operation is secured and can only be performed by users with the "ADMIN"
     * role.
     *
     * @param dtoTask the updated task data in {@link DtoTask} format.
     * @return a {@link ResponseEntity} with no content (HTTP status 204) indicating
     *         the task was updated successfully.
     */
    @Secured({ "ADMIN" })
    @PutMapping(path = "/task/update")
    ResponseEntity<Void> updateTask(@Valid @RequestBody DtoTask dtoTask);

    /**
     * Deletes a task by its ID.
     * This operation is secured and can only be performed by users with the "ADMIN"
     * role.
     *
     * @param taskId the ID of the task to be deleted.
     * @return a {@link ResponseEntity} with no content (HTTP status 204) indicating
     *         the task was deleted successfully.
     */
    @Secured({ "ADMIN" })
    @DeleteMapping(path = "/task/delete")
    ResponseEntity<Void> deleteTask(@NotNull @RequestParam String taskId);
}
