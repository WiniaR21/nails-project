package com.winiardev.nails.account.service;

import java.util.List;

import com.winiardev.nails.account.dto.DtoAccount;

/**
 * Service interface for managing old accounts.
 * Provides methods to fetch and delete old accounts.
 * 
 * @author winiar.dev
 */
public interface IServiceOldAccount {

    /**
     * Retrieves a list of all old accounts.
     *
     * @return a list of all old accounts
     */
    public List<DtoAccount> fetchAllOldAccounts();

    /**
     * Deletes an old account identified by the given account ID.
     *
     * @param accountId the ID of the account to be deleted
     */
    public void deleteOldAccount(String accountId);
}
