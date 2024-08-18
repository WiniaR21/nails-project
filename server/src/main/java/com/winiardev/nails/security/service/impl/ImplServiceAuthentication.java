package com.winiardev.nails.security.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.account.embeddable.Password;
import com.winiardev.nails.account.embeddable.Role;
import com.winiardev.nails.account.entity.Account;
import com.winiardev.nails.account.repo.RepoAccount;
import com.winiardev.nails.exception.exceptions.UniqueEmailException;
import com.winiardev.nails.security.controller.request.RequestAuthentication;
import com.winiardev.nails.security.controller.request.RequestRegister;
import com.winiardev.nails.security.controller.response.ResponseAuthentication;
import com.winiardev.nails.security.service.IServiceAuthentication;
import com.winiardev.nails.security.service.IServiceJwt;

import lombok.RequiredArgsConstructor;

/**
 * Service implementation for handling authentication-related operations such as
 * registration and authentication.
 * This class implements the {@link IServiceAuthentication} interface.
 * 
 * @author winiar.dev
 */
@Service
@RequiredArgsConstructor
public class ImplServiceAuthentication implements IServiceAuthentication {

    private final RepoAccount repoAccount;
    private final PasswordEncoder passwordEncoder;
    private final IServiceJwt iJwt;
    private final AuthenticationManager authenticationManager;

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
    @Override
    public ResponseAuthentication register(RequestRegister request) {
        Email email = new Email(request.getEmail());

        // Check if an account with the provided email already exists, if so, throw
        // UniqueEmailException
        Optional<Account> checkAccount = repoAccount.findByEmail(email);
        if (checkAccount.isPresent()) {
            throw new UniqueEmailException(request.getEmail());
        }

        // Validate password complexity
        new Password(request.getPassword());

        // Build a new Account object based on the RequestRegister
        Account newAccount = new Account.AccountBuilder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(Role.ADMIN)
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .build();

        // Save the new account in the repository
        Account createdAccount = repoAccount.save(newAccount);

        // Return AuthenticationResponse with token and expiration date
        return generateAuthResponse(createdAccount);
    }

    /**
     * Authenticates a user with the given authentication request.
     *
     * @param request the request containing authentication details like email and
     *                password.
     * @return an {@link ResponseAuthentication} containing the generated JWT token
     *         and its expiration date.
     */
    @Override
    public ResponseAuthentication authenticate(RequestAuthentication request) {
        // Authenticate using the credentials provided in RequestAuthentication
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        // Find the account by the email provided in RequestAuthentication
        Email email = new Email(request.getEmail());
        Account account = repoAccount.findByEmail(email).orElseThrow();

        // Return AuthenticationResponse with token and expiration date
        return generateAuthResponse(account);
    }

    /**
     * Generates an {@link ResponseAuthentication} containing a JWT token and its
     * expiration date.
     *
     * @param account the account for which the JWT token is to be generated.
     * @return an {@link ResponseAuthentication} containing the generated JWT token
     *         and its expiration date.
     */
    private ResponseAuthentication generateAuthResponse(Account account) {
        String token = iJwt.generateToken(account);
        Date expirationDate = iJwt.extractExpirationDate(token);

        return new ResponseAuthentication(token, expirationDate);
    }
}