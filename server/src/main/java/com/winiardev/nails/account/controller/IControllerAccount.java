package com.winiardev.nails.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.winiardev.nails.account.controller.request.RequestChangeName;
import com.winiardev.nails.account.controller.request.RequestChangePassword;
import com.winiardev.nails.account.controller.request.RequestChangePhoneNumber;
import com.winiardev.nails.security.controller.request.RequestAuthentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * Interface defining endpoints for account management.
 * 
 * @author winiar.dev
 */
@Validated
public interface IControllerAccount {

        /**
         * Endpoint to update the user's name.
         *
         * @param request The request containing the new first and last name.
         * @param servlet The HTTP servlet request containing the JWT token.
         * @return A response entity with no content.
         */
        @Secured({ "CUSTOMER" })
        @PutMapping(path = "/account/update/name")
        public ResponseEntity<Void> updateName(@Valid @RequestBody RequestChangeName request,
                        HttpServletRequest servlet);

        /**
         * Endpoint to update the user's password.
         *
         * @param request The request containing the new password.
         * @param servlet The HTTP servlet request containing the JWT token.
         * @return A response entity with no content.
         */
        @Secured({ "CUSTOMER" })
        @PutMapping(path = "/account/update/password")
        public ResponseEntity<Void> updatePassword(@Valid @RequestBody RequestChangePassword request,
                        HttpServletRequest servlet);

        /**
         * Endpoint to update the user's phone number.
         *
         * @param request The request containing the new phone number.
         * @param servlet The HTTP servlet request containing the JWT token.
         * @return A response entity with no content.
         */
        @Secured({ "CUSTOMER" })
        @PutMapping(path = "/account/update/phone-number")
        public ResponseEntity<Void> updatePhoneNumber(@Valid @RequestBody RequestChangePhoneNumber request,
                        HttpServletRequest servlet);

        /**
         * Endpoint to delete the user's account.
         *
         * @param request The request containing the user's email and password for
         *                authentication.
         * @param servlet The HTTP servlet request containing the JWT token.
         * @return A response entity with no content.
         */
        @Secured({ "CUSTOMER" })
        @DeleteMapping(path = "/account/delete")
        public ResponseEntity<Void> deleteAccount(@Valid @RequestBody RequestAuthentication request,
                        HttpServletRequest servlet);
}
