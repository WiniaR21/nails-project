package com.winiardev.nails.account.embeddable;

import jakarta.persistence.Embeddable;

/**
 * Represents a full name with a first name and a last name.
 * Both names must be between 3 and 15 characters long and contain only letters.
 * 
 * @author winiar.dev
 */
@Embeddable
public record FullName(String firstName, String lastName) {

    /**
     * Constructs a new FullName instance.
     * 
     * @param firstName The first name.
     * @param lastName  The last name.
     * @throws IllegalArgumentException if the first name or last name is null, not
     *                                  within the specified length, or contains
     *                                  non-letter characters.
     */
    public FullName(String firstName, String lastName) {
        if (firstName == null || !firstName.matches("^[\\p{L}]{3,15}$")) {
            throw new IllegalArgumentException("First name must be at least 3 chars long and contains only letters");
        }

        if (lastName == null || !lastName.matches("^[\\p{L}]{3,15}$")) {
            throw new IllegalArgumentException("Last name must be at least 3 chars long and contains only letters");
        }

        this.firstName = capitalize(firstName);
        this.lastName = capitalize(lastName);
    }

    /**
     * Capitalizes the first letter of a string and converts the rest to lowercase.
     * 
     * @param str The string to capitalize.
     * @return The capitalized string.
     */
    private String capitalize(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }
}
