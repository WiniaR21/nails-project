package com.winiardev.nails.account.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestChangePhoneNumber {
    @NotNull(message = "newPhoneNumber can not be empty")
    private String newPhoneNumber;
}
