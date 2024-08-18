package com.winiardev.nails.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.winiardev.nails.security.controller.request.RequestAuthentication;
import com.winiardev.nails.security.controller.request.RequestRegister;
import com.winiardev.nails.security.controller.response.ResponseAuthentication;

import jakarta.validation.Valid;

/**
 * Interface defining the authentication-related API endpoints.
 * It includes methods for user registration and login.
 * 
 * @author winiar.dev
 */
@Validated
public interface IControllerAuth {

        /**
         * Registers a new user in the system.
         *
         * @param request the registration request containing user details
         * @return a response containing authentication information for the registered
         *         user
         */
        @PostMapping(path = "/register")
        ResponseEntity<ResponseAuthentication> register(
                        @RequestBody @Valid RequestRegister request);

        /**
         * Authenticates a user with the given credentials.
         *
         * @param request the login request containing user credentials
         * @return a response containing authentication information for the
         *         authenticated user
         */
        @PostMapping(path = "/login")
        ResponseEntity<ResponseAuthentication> login(
                        @RequestBody @Valid RequestAuthentication request);
}
