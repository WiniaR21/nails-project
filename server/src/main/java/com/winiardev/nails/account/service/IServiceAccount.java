package com.winiardev.nails.account.service;

import com.winiardev.nails.account.controller.request.RequestChangeName;
import com.winiardev.nails.account.controller.request.RequestChangePassword;
import com.winiardev.nails.account.controller.request.RequestChangePhoneNumber;
import com.winiardev.nails.security.controller.request.RequestAuthentication;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Service interface for managing account-related operations.
 * This includes updating user name, password, phone number and deleting
 * account.
 * 
 * @author winiar.dev
 */
public interface IServiceAccount {

        /**
         * Updates the name of the account associated with the provided servlet request.
         * 
         * @param request The request containing the new first and last name.
         * @param servlet The servlet request containing the JWT token.
         */
        void updateName(RequestChangeName request, HttpServletRequest servlet);

        /**
         * Updates the password of the account associated with the provided servlet
         * request.
         * 
         * @param request The request containing the new password.
         * @param servlet The servlet request containing the JWT token.
         */
        void updatePassword(RequestChangePassword request, HttpServletRequest servlet);

        /**
         * Updates the phone number of the account associated with the provided servlet
         * request.
         * 
         * @param request The request containing the new phone number.
         * @param servlet The servlet request containing the JWT token.
         */
        void updatePhoneNumber(RequestChangePhoneNumber request, HttpServletRequest servlet);

        /**
         * Deletes the account after verifying the provided credentials.
         *
         * @param request The request containing the user's email and password.
         * @param servlet The servlet request containing the JWT token.
         * @throws IllegalArgumentException if the account cannot be found.
         */
        void deleteAccount(RequestAuthentication request, HttpServletRequest servlet);
}
