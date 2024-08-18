package com.winiardev.nails.task.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winiardev.nails.task.controller.IControllerTask;
import com.winiardev.nails.task.dto.DtoTask;
import com.winiardev.nails.task.service.IServiceTask;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class ImplControllerTask implements IControllerTask {

    private final IServiceTask iTask;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DtoTask>> fetchAllTask() {
        List<DtoTask> dtoTasks = iTask.fetchAllTask();

        return ResponseEntity.status(HttpStatus.OK).body(dtoTasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<DtoTask> fetchTaskById(String taskId) {
        DtoTask dtoTask = iTask.fetchTaskById(taskId);
        System.out.println(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(dtoTask);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> addTask(DtoTask dtoTask) {
        iTask.addTask(dtoTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> updateTask(DtoTask dtoTask) {
        iTask.updateTask(dtoTask);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> deleteTask(String taskId) {
        iTask.deleteTask(taskId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
