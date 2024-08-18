package com.winiardev.nails.account.embeddable;

import jakarta.persistence.Embeddable;

/**
 * Represents an Email with validation for specific domains.
 * The email must be at least 11 characters long and belong to either
 * the @gmail.com or @onet.pl domain.
 * 
 * @author winiar.dev
 */
@Embeddable
public record Email(String email) {

    /**
     * Constructs a new Email instance.
     * 
     * @param email The email address.
     * @throws IllegalArgumentException if the email is null, shorter than 11
     *                                  characters, or not in the specified domains.
     */
    public Email(String email) {
        if (email == null || email.length() < 11) {
            throw new IllegalArgumentException("Email must be at least 11 chars long");
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@(gmail\\.com|onet\\.pl)$")) {
            throw new IllegalArgumentException("Email must be in domain @gmail.com or @onet.pl");
        }

        this.email = email;
    }
}
