package com.winiardev.nails.task.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winiardev.nails.task.dto.DtoTask;

import jakarta.validation.constraints.NotNull;

/**
 * Controller interface for managing old tasks.
 * Provides endpoints for fetching and deleting old tasks.
 * 
 * @author winiar.dev
 */
public interface IControllerOldTask {

    /**
     * Fetches a list of all old tasks.
     * Accessible only by users with the "ADMIN" role.
     *
     * @return a response entity containing a list of all old tasks
     */
    @Secured({ "ADMIN" })
    @GetMapping(path = "/old-task/all")
    public ResponseEntity<List<DtoTask>> fetchAllOldTask();

    /**
     * Deletes an old task based on the provided task ID.
     * Accessible only by users with the "ADMIN" role.
     *
     * @param taskId the ID of the task to be deleted
     * @return a response entity with no content
     */
    @Secured({ "ADMIN" })
    @DeleteMapping(path = "/old-task/delete")
    public ResponseEntity<Void> deleteOldTask(@NotNull @RequestParam String taskId);
}
