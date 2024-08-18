package com.winiardev.nails.exception.exceptions;

public class UniqueEmailException extends RuntimeException {

    /**
     * Excepton used in case when,
     * durning creating new account program detect already existing account with
     * given email
     * 
     * @param email - Email in string format
     */
    public UniqueEmailException(String email) {
        super("Given email " + email + " is already registred.");
    }
}
