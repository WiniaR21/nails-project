package com.winiardev.nails.task.entity;

import java.time.LocalDateTime;

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

/**
 * Represents a task with a name, price, duration, and description.
 * This entity is used to store tasks in the database.
 * <p>
 * The class uses embedded value objects for task properties such as name,
 * price, duration, and description.
 * It also keeps track of the task's creation and update timestamps.
 * </p>
 * <p>
 * The class is marked as an entity for persistence in the "old_task" table.
 * </p>
 * 
 * @author winiar.dev
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "old_task")
public class OldTask {

    /**
     * The unique identifier for the task.
     * <p>
     * This ID is generated using a UUID strategy.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "task_id")
    private String taskId;

    /**
     * The name of the task.
     * <p>
     * This field is embedded from the {@link TaskName} value object.
     * </p>
     */
    @Embedded
    private TaskName name;

    /**
     * The price of the task.
     * <p>
     * This field is embedded from a {@link Price} value object (not provided in the
     * example).
     * </p>
     */
    @Embedded
    private Price price;

    /**
     * The duration of the task in minutes.
     * <p>
     * This field is embedded from the {@link DurationTime} value object.
     * </p>
     */
    @Embedded
    private DurationTime durationTime;

    /**
     * The description of the task.
     * <p>
     * This field is embedded from the {@link TaskDescription} value object.
     * </p>
     */
    @Embedded
    private TaskDescription description;

    /**
     * The timestamp of when the task was created.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * The timestamp of when the task was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Constructs an OldTask entity based on the provided {@link Task} object.
     * <p>
     * This constructor copies the task's properties to initialize the OldTask
     * entity.
     * </p>
     *
     * @param task the task to copy properties from
     */
    public OldTask(Task task) {
        this.name = task.getName();
        this.price = task.getPrice();
        this.durationTime = task.getDurationTime();
        this.description = task.getDescription();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }
}
