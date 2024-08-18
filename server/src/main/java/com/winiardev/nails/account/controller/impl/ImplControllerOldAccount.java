package com.winiardev.nails.account.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.winiardev.nails.account.controller.IControllerOldAccount;
import com.winiardev.nails.account.dto.DtoAccount;
import com.winiardev.nails.account.service.IServiceOldAccount;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class ImplControllerOldAccount implements IControllerOldAccount {

    private final IServiceOldAccount iOldAccount;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DtoAccount>> fetchAllOldAccounts() {
        List<DtoAccount> dtoAccounts = iOldAccount.fetchAllOldAccounts();

        return ResponseEntity.status(HttpStatus.OK).body(dtoAccounts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> deleteOldAccount(String accountId) {
        iOldAccount.deleteOldAccount(accountId);

        return ResponseEntity.noContent().build();
    }
}
