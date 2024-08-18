package com.winiardev.nails.security.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestAuthentication {

    @NotNull(message = "email can not be empty")
    private String email;

    @NotNull(message = "password can not be empty")
    private String password;
}
