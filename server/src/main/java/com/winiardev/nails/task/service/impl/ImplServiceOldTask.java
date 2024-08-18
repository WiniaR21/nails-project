package com.winiardev.nails.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.winiardev.nails.exception.exceptions.ResourceNotFoundException;
import com.winiardev.nails.task.dto.DtoTask;
import com.winiardev.nails.task.repo.RepoOldTask;
import com.winiardev.nails.task.service.IServiceOldTask;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplServiceOldTask implements IServiceOldTask {

    private final RepoOldTask repoOldTask;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoTask> fetchAllOldTask() {
        List<DtoTask> dtoTasks = new ArrayList<>();

        repoOldTask
                .findAll()
                .forEach(task -> {
                    dtoTasks.add(new DtoTask(task));
                });

        return dtoTasks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOldTask(String taskId) {
        repoOldTask
                .findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("task", "taskId", taskId));

        repoOldTask
                .deleteById(taskId);
    }

}
