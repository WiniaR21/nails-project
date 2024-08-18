package com.winiardev.nails.account.embeddable;

import jakarta.persistence.Embeddable;

/**
 * Represents a password with validation.
 * The password must be at least 8 characters long and contain at least one
 * digit.
 * 
 * @author winiar.dev
 */
@Embeddable
public record Password(String password) {

    /**
     * Constructs a new Password instance.
     * 
     * @param password The password string.
     * @throws IllegalArgumentException if the password does not meet the required
     *                                  length or digit criteria.
     */
    public Password(String password) {
        if (!password.matches("^(?=.*\\d).{8,}$")) {
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters long and contain at least one digit.");
        }
        this.password = password;
    }
}