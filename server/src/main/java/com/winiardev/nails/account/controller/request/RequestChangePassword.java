package com.winiardev.nails.account.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestChangePassword {
    @NotNull(message = "oldPassword can not be empty")
    private String oldPassword;
    @NotNull(message = "newPassword can not be empty")
    private String newPassword;
}
