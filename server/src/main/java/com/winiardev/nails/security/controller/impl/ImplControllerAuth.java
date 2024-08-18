package com.winiardev.nails.security.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winiardev.nails.security.controller.IControllerAuth;
import com.winiardev.nails.security.controller.request.RequestAuthentication;
import com.winiardev.nails.security.controller.request.RequestRegister;
import com.winiardev.nails.security.controller.response.ResponseAuthentication;
import com.winiardev.nails.security.service.IServiceAuthentication;

import lombok.RequiredArgsConstructor;

/**
 * REST controller that implements authentication-related API endpoints.
 * This class handles user registration and login requests.
 * 
 * @author winiar.dev
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class ImplControllerAuth implements IControllerAuth {

    private final IServiceAuthentication iAuthentication;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ResponseAuthentication> register(
            RequestRegister request) {

        ResponseAuthentication response = iAuthentication.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ResponseAuthentication> login(
            RequestAuthentication request) {

        ResponseAuthentication response = iAuthentication.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
