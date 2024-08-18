package com.winiardev.nails.account.embeddable;

import jakarta.persistence.Embeddable;

/**
 * Represents a phone number with validation.
 * The phone number must be exactly 9 digits long.
 * 
 * @author winiar.dev
 */
@Embeddable
public record PhoneNumber(String phoneNumber) {

    /**
     * Constructs a new PhoneNumber instance.
     * 
     * @param phoneNumber The phone number string.
     * @throws IllegalArgumentException if the phone number is null or not exactly 9
     *                                  digits long.
     */
    public PhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{9}")) {
            throw new IllegalArgumentException("Phone number must be exactly 9 digits");
        }
        this.phoneNumber = phoneNumber;
    }
}
