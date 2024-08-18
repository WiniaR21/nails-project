package com.winiardev.nails.account.dto;

import com.winiardev.nails.account.entity.OldAccount;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DtoAccount {
    private String accountId;

    @NotNull(message = "email can not be null")
    private String email;

    @NotNull(message = "firstName can not be null")
    private String firstName;

    @NotNull(message = "lastName can not be null")
    private String lastName;

    @NotNull(message = "phoneNumber can not be null")
    private String phoneNumber;

    public DtoAccount(OldAccount oldAccount) {
        this.accountId = oldAccount.getOldAccountId();
        this.email = oldAccount.getEmail().email();
        this.firstName = oldAccount.getFullName().firstName();
        this.lastName = oldAccount.getFullName().lastName();
        this.phoneNumber = oldAccount.getPhoneNumber().phoneNumber();
    }
}
