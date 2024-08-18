package com.winiardev.nails.task.embeddable;

import jakarta.persistence.Embeddable;

/**
 * A value object representing the duration of a task in minutes.
 * <p>
 * This class ensures that the duration is not null or negative.
 * </p>
 * 
 * @param minutes the duration in minutes
 * @throws IllegalArgumentException if the minutes are null or negative
 */
@Embeddable
public record DurationTime(Integer minutes) {
    public DurationTime(Integer minutes) {
        if (minutes == null)
            throw new IllegalArgumentException("Duration time cannot be null");
        if (minutes < 0)
            throw new IllegalArgumentException("Minutes cannot be a negative number");
        this.minutes = minutes;
    }
}
