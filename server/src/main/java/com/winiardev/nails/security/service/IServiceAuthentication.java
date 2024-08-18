package com.winiardev.nails.security.service;

import com.winiardev.nails.exception.exceptions.UniqueEmailException;
import com.winiardev.nails.security.controller.request.RequestAuthentication;
import com.winiardev.nails.security.controller.request.RequestRegister;
import com.winiardev.nails.security.controller.response.ResponseAuthentication;

public interface IServiceAuthentication {

    /**
     * Registers a new account with the given registration request.
     *
     * @param request the request containing registration details like first name,
     *                last name, email, password, and phone number.
     * @return an {@link ResponseAuthentication} containing the generated JWT token
     *         and its expiration date.
     * @throws UniqueEmailException if an account with the provided email already
     *                              exists.
     */
    ResponseAuthentication register(RequestRegister request) throws UniqueEmailException;

    /**
     * Authenticates a user with the given authentication request.
     *
     * @param request the request containing authentication details like email and
     *                password.
     * @return an {@link ResponseAuthentication} containing the generated JWT token
     *         and its expiration date.
     */
    ResponseAuthentication authenticate(RequestAuthentication request);

}