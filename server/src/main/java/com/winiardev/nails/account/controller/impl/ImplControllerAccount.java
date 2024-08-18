package com.winiardev.nails.account.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winiardev.nails.account.controller.IControllerAccount;
import com.winiardev.nails.account.controller.request.RequestChangeName;
import com.winiardev.nails.account.controller.request.RequestChangePassword;
import com.winiardev.nails.account.controller.request.RequestChangePhoneNumber;
import com.winiardev.nails.account.service.IServiceAccount;
import com.winiardev.nails.security.controller.request.RequestAuthentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the {@link IControllerAccount} interface, providing
 * endpoints for
 * account management.
 * 
 * @author winiar.dev
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class ImplControllerAccount implements IControllerAccount {

    private final IServiceAccount iAccount;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> updateName(
            RequestChangeName request,
            HttpServletRequest servlet) {

        iAccount.updateName(request, servlet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> updatePassword(
            RequestChangePassword request,
            HttpServletRequest servlet) {

        iAccount.updatePassword(request, servlet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> updatePhoneNumber(
            RequestChangePhoneNumber request,
            HttpServletRequest servlet) {

        iAccount.updatePhoneNumber(request, servlet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> deleteAccount(
            RequestAuthentication request,
            HttpServletRequest servlet) {

        iAccount.deleteAccount(request, servlet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
