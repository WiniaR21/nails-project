package com.winiardev.nails.task.embeddable;

import jakarta.persistence.Embeddable;

/**
 * A value object representing the name of a task.
 * <p>
 * This class ensures that the task name is not null and is at least 5
 * characters long.
 * </p>
 * 
 * @param name the name of the task
 * @throws IllegalArgumentException if the name is null or less than 5
 *                                  characters
 */
@Embeddable
public record TaskName(String name) {
    public TaskName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Task name cannot be null");
        if (name.length() < 5)
            throw new IllegalArgumentException("Task name cannot be less than 5 characters");
        this.name = name;
    }
}