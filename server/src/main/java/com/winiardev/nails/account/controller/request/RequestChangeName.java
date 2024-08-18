package com.winiardev.nails.account.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestChangeName {
    @NotNull(message = "newFirstName can not be empty")
    private String newFirstName;
    @NotNull(message = "newLastName can not be empty")
    private String newLastName;
}
