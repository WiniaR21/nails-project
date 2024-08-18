package com.winiardev.nails.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winiardev.nails.account.dto.DtoAccount;

import jakarta.validation.constraints.NotNull;

/**
 * Controller interface for managing old accounts.
 * Provides endpoints for fetching and deleting old accounts.
 * 
 * @author winiar.dev
 */
public interface IControllerOldAccount {

    /**
     * Fetches a list of all old accounts.
     * Accessible only by users with the "ADMIN" role.
     *
     * @return a response entity containing a list of all old accounts
     */
    @Secured({ "ADMIN" })
    @GetMapping(path = "/old-account/all")
    public ResponseEntity<List<DtoAccount>> fetchAllOldAccounts();

    /**
     * Deletes an old account based on the provided account ID.
     * Accessible only by users with the "ADMIN" role.
     *
     * @param accountId the ID of the account to be deleted
     * @return a response entity with no content
     */
    @Secured({ "ADMIN" })
    @DeleteMapping(path = "/old-account/delete")
    public ResponseEntity<Void> deleteOldAccount(@NotNull @RequestParam String accountId);
}
