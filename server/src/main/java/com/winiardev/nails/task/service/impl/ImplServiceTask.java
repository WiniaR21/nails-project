package com.winiardev.nails.task.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.winiardev.nails.exception.exceptions.ResourceNotFoundException;
import com.winiardev.nails.task.dto.DtoTask;
import com.winiardev.nails.task.entity.OldTask;
import com.winiardev.nails.task.entity.Task;
import com.winiardev.nails.task.repo.RepoOldTask;
import com.winiardev.nails.task.repo.RepoTask;
import com.winiardev.nails.task.service.IServiceTask;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplServiceTask implements IServiceTask {

    private final RepoTask repoTask;
    private final RepoOldTask repoOldTask;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoTask> fetchAllTask() {
        List<DtoTask> tasksDto = new ArrayList<>();

        repoTask.findAll().forEach(task -> {
            tasksDto.add(new DtoTask(task));
        });

        return tasksDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DtoTask fetchTaskById(String taskId) {
        return new DtoTask(repoTask.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("task", "id", taskId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTask(DtoTask dtoTask) {
        Task task = new Task(dtoTask);
        task.setCreatedAt(LocalDateTime.now());
        repoTask.save(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTask(DtoTask dtoTask) {
        if (dtoTask.getTaskId() == null)
            throw new IllegalArgumentException("Id of updated task can not be null");

        Task task = repoTask
                .findById(dtoTask.getTaskId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("task", "taskId", dtoTask.getTaskId()));

        repoOldTask.save(new OldTask(task));

        Task updatedTask = new Task(dtoTask);
        updatedTask.setTaskId(task.getTaskId());
        updatedTask.setCreatedAt(task.getCreatedAt());
        updatedTask.setUpdatedAt(LocalDateTime.now());

        repoTask.save(updatedTask);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTask(String taskId) {
        repoOldTask.save(new OldTask(repoTask.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("task", "taskId", taskId))));

        repoTask.deleteById(taskId);
    }

}
