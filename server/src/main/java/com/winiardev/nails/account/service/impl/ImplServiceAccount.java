package com.winiardev.nails.account.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.winiardev.nails.account.controller.request.RequestChangeName;
import com.winiardev.nails.account.controller.request.RequestChangePassword;
import com.winiardev.nails.account.controller.request.RequestChangePhoneNumber;
import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.account.embeddable.FullName;
import com.winiardev.nails.account.embeddable.Password;
import com.winiardev.nails.account.embeddable.PhoneNumber;
import com.winiardev.nails.account.entity.Account;
import com.winiardev.nails.account.entity.OldAccount;
import com.winiardev.nails.account.repo.RepoAccount;
import com.winiardev.nails.account.repo.RepoOldAccount;
import com.winiardev.nails.account.service.IServiceAccount;
import com.winiardev.nails.exception.exceptions.ResourceNotFoundException;
import com.winiardev.nails.security.controller.request.RequestAuthentication;
import com.winiardev.nails.security.service.IServiceJwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the {@link IServiceAccount} interface.
 * Provides methods to update account information such as {@link FullName},
 * {@link Password}, and
 * {@link PhoneNumber}.
 * 
 * @author winiar.dev
 */
@RequiredArgsConstructor
@Service
public class ImplServiceAccount implements IServiceAccount {

    private final RepoAccount repoAccount;
    private final RepoOldAccount repoOldAccount;
    private final IServiceJwt jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateName(RequestChangeName request, HttpServletRequest servlet) {
        FullName fullName = new FullName(request.getNewFirstName(), request.getNewLastName());
        Account account = findAccountByServletRequest(servlet);

        if (fullName.equals(account.getFullName()))
            throw new IllegalArgumentException("Request fullName equals current fullName. Nothing to update");

        repoOldAccount.save(new OldAccount(account));

        account.setFullName(fullName);
        account.setUpdatedAt(LocalDateTime.now());

        repoAccount.save(account);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePassword(RequestChangePassword request, HttpServletRequest servlet) {
        System.out.println("TU JESTEM");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtService.extractEmailFromServlet(servlet).email(),
                        request.getOldPassword()));

        // Validate password complecity
        new Password(request.getNewPassword());

        Password password = new Password(passwordEncoder.encode(request.getNewPassword()));
        Account account = findAccountByServletRequest(servlet);

        repoOldAccount.save(new OldAccount(account));

        account.setPassword(password);
        account.setUpdatedAt(LocalDateTime.now());

        repoAccount.save(account);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhoneNumber(RequestChangePhoneNumber request, HttpServletRequest servlet) {
        PhoneNumber phoneNumber = new PhoneNumber(request.getNewPhoneNumber());
        Account account = findAccountByServletRequest(servlet);

        if (phoneNumber.equals(account.getPhoneNumber()))
            throw new IllegalArgumentException("Request phoneNumber equals current phoneNumber. Nothing to update");

        repoOldAccount.save(new OldAccount(account));

        account.setPhoneNumber(phoneNumber);
        account.setUpdatedAt(LocalDateTime.now());

        repoAccount.save(account);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAccount(RequestAuthentication request, HttpServletRequest servlet) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        Account account = findAccountByServletRequest(servlet);
        repoOldAccount.save(new OldAccount(account));
        repoAccount.delete(account);
    }

    /**
     * Finds the account associated with the provided servlet request.
     * Extracts the email from the JWT token in the Authorization header.
     * 
     * @param servlet The servlet request containing the JWT token.
     * @return The account associated with the extracted email.
     * @throws IllegalArgumentException  If the servlet request or authorization
     *                                   header is null or empty.
     * @throws ResourceNotFoundException If the account is not found by email.
     */
    private Account findAccountByServletRequest(HttpServletRequest servlet) {
        Email email = jwtService.extractEmailFromServlet(servlet);

        return repoAccount.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Account", "'findAccountByServletReturn'", email.email()));
    }
}
