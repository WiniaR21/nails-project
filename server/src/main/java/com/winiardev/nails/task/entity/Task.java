package com.winiardev.nails.task.entity;

import java.time.LocalDateTime;

import com.winiardev.nails.task.dto.DtoTask;
import com.winiardev.nails.task.embeddable.DurationTime;
import com.winiardev.nails.task.embeddable.Price;
import com.winiardev.nails.task.embeddable.TaskDescription;
import com.winiardev.nails.task.embeddable.TaskName;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "task_id")
    private String taskId;

    @Embedded
    private TaskName name;

    @Embedded
    private Price price;

    @Embedded
    private DurationTime durationTime;

    @Embedded
    private TaskDescription description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Task(DtoTask dtoTask) {
        this.name = new TaskName(dtoTask.getName());
        this.price = new Price(dtoTask.getPrice());
        this.durationTime = new DurationTime(dtoTask.getDurationTime());
        this.description = new TaskDescription(dtoTask.getDescription());
    }
}
