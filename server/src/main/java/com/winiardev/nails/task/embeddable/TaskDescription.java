package com.winiardev.nails.task.embeddable;

/**
 * A value object representing the description of a task.
 * <p>
 * This class ensures that the description is not null and does not exceed 250
 * characters.
 * </p>
 * 
 * @param description the description of the task
 * @throws IllegalArgumentException if the description is null or exceeds 250
 *                                  characters
 */
public record TaskDescription(String description) {
    public TaskDescription(String description) {
        if (description == null)
            throw new IllegalArgumentException("Description cannot be null");
        if (description.length() > 250)
            throw new IllegalArgumentException("Description cannot be longer than 250 characters");

        this.description = description;
    }
}
