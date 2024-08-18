
package com.winiardev.nails.task.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.winiardev.nails.task.entity.OldTask;
import com.winiardev.nails.task.entity.Task;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DtoTask {

    private String taskId;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer durationTime;

    @NotNull
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DtoTask(Task task) {
        this.taskId = task.getTaskId();
        this.name = task.getName().name();
        this.price = task.getPrice().amount();
        this.durationTime = task.getDurationTime().minutes();
        this.description = task.getDescription().description();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }

    public DtoTask(OldTask task) {
        this.taskId = task.getTaskId();
        this.name = task.getName().name();
        this.price = task.getPrice().amount();
        this.durationTime = task.getDurationTime().minutes();
        this.description = task.getDescription().description();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }
}
