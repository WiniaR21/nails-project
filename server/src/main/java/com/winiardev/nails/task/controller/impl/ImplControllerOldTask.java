package com.winiardev.nails.task.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.winiardev.nails.task.controller.IControllerOldTask;
import com.winiardev.nails.task.dto.DtoTask;
import com.winiardev.nails.task.service.IServiceOldTask;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class ImplControllerOldTask implements IControllerOldTask {

    private final IServiceOldTask iOldTask;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DtoTask>> fetchAllOldTask() {
        List<DtoTask> dtoTasks = iOldTask.fetchAllOldTask();

        return ResponseEntity.status(HttpStatus.OK).body(dtoTasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> deleteOldTask(String taskId) {
        iOldTask.deleteOldTask(taskId);

        return ResponseEntity.noContent().build();
    }
}
