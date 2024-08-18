package com.winiardev.nails.task.embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Embeddable;

/**
 * Represents a monetary value with a specified precision.
 * The price is immutable and provides operations for addition and subtraction.
 */
@Embeddable
public record Price(BigDecimal amount) {

    /**
     * Constructs a new Price instance.
     * 
     * @param amount The monetary value represented as a BigDecimal.
     * @throws IllegalArgumentException if the amount is null or negative.
     */
    public Price(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Price can not be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price can not be negative");
        }

        this.amount = setScale(amount);
    }

    /**
     * Adds the specified price to this price and returns a new Price instance with
     * the result.
     * 
     * @param price The price to add.
     * @return A new Price instance representing the sum.
     */
    public Price add(Price price) {
        return new Price(this.amount.add(price.amount));
    }

    /**
     * Subtracts the specified price from this price and returns a new Price
     * instance with the result.
     * 
     * @param price The price to subtract.
     * @return A new Price instance representing the difference.
     */
    public Price substract(Price price) {
        return new Price(this.amount.subtract(price.amount));
    }

    /**
     * Sets the scale of the BigDecimal to 2 decimal places, rounding if necessary.
     * 
     * @param input The BigDecimal to be scaled.
     * @return A new BigDecimal with the scale set to 2 decimal places.
     */
    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}
