package com.winiardev.nails.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.winiardev.nails.account.dto.DtoAccount;

import com.winiardev.nails.account.repo.RepoOldAccount;
import com.winiardev.nails.account.service.IServiceOldAccount;
import com.winiardev.nails.exception.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplServiceOldAccount implements IServiceOldAccount {

    private final RepoOldAccount repoOldAccount;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoAccount> fetchAllOldAccounts() {
        List<DtoAccount> dtoAccountsList = new ArrayList<>();

        repoOldAccount.findAll().forEach(account -> {
            dtoAccountsList.add(new DtoAccount(account));
        });

        return dtoAccountsList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOldAccount(String accountId) {
        repoOldAccount.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("account", "accountId", accountId));
        repoOldAccount.deleteById(accountId);
    }

}
